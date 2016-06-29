package androidsamples.androidw.com.androidsamples.flicker.presenter;

import androidsamples.androidw.com.androidsamples.base.presenter.AbstractPresenter;
import androidsamples.androidw.com.androidsamples.flicker.adapter.model.PhotoDataModel;
import androidsamples.androidw.com.androidsamples.network.RetrofitPhoto;
import androidsamples.androidw.com.androidsamples.network.bean.Photo;
import androidsamples.androidw.com.androidsamples.network.bean.PhotosPageInfo;
import androidsamples.androidw.com.androidsamples.network.bean.RecentPhotoResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public class FlickerPresenter extends AbstractPresenter<FlickerContract.View> implements FlickerContract.Presenter {

    private RetrofitPhoto retrofitPhoto;
    private PhotoDataModel photoDataModel;

    public FlickerPresenter(FlickerContract.View view, RetrofitPhoto retrofitPhoto) {
        super(view);

        this.retrofitPhoto = retrofitPhoto;
    }

    @Override
    public void setDataModel(PhotoDataModel photoDataModel) {
        this.photoDataModel = photoDataModel;
    }

    @Override
    public void onPhotoItemClick(int position) {
        Photo photo = photoDataModel.getPhotoItem(position);
        getView().showBottomSheet(photo);
    }

    @Override
    public void loadPhotos(int page) {
        Call<RecentPhotoResponse> photos = retrofitPhoto.getRecentPhoto(page);
        photos.enqueue(new Callback<RecentPhotoResponse>() {
            @Override
            public void onResponse(Call<RecentPhotoResponse> call, Response<RecentPhotoResponse> response) {
                if (!response.isSuccessful()) {
                    getView().showFailLoadImage();
                    return;
                }

                RecentPhotoResponse recentPhoto = response.body();
                if (recentPhoto.photos != null) {
                    PhotosPageInfo photoList = recentPhoto.photos;
                    if (photoList.photo != null) {
                        for (Photo photo : photoList.photo) {
                            photoDataModel.add(photo, false);
                        }
                    }
                }

                getView().refresh();
            }

            @Override
            public void onFailure(Call<RecentPhotoResponse> call, Throwable t) {
                getView().showFailLoadImage();
            }
        });
    }

    // 참고 - http://reactivex.io/documentation/operators/filter.html
    @Override
    public void rxLoadPhotos(int page) {
        Observable<RecentPhotoResponse> photoList = retrofitPhoto.getObservableRecentPhoto(page);
        photoList
                .subscribeOn(Schedulers.io())
                .map(new Func1<RecentPhotoResponse, PhotosPageInfo>() {
                    @Override
                    public PhotosPageInfo call(RecentPhotoResponse recentPhotoResponse) {
                        return recentPhotoResponse.photos;
                    }
                })
                .filter(new Func1<PhotosPageInfo, Boolean>() {
                    @Override
                    public Boolean call(PhotosPageInfo photosPageInfo) {
                        return photosPageInfo != null && photosPageInfo.photo != null && photosPageInfo.photo.size() > 0;
                    }
                })
                .flatMap(new Func1<PhotosPageInfo, Observable<Photo>>() {
                    @Override
                    public Observable<Photo> call(PhotosPageInfo photosPageInfo) {
                        return Observable.from(photosPageInfo.photo);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Photo>() {
                    @Override
                    public void onCompleted() {
                        getView().refresh();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getView().showFailLoadImage();
                    }

                    @Override
                    public void onNext(Photo photo) {
                        photoDataModel.add(photo, false);
                    }
                });
    }

    // 참고 - http://reactivex.io/documentation/operators/filter.html
    @Override
    public void rxLambdaLoadPhotos(int page) {
        Observable<RecentPhotoResponse> photoList = retrofitPhoto.getObservableRecentPhoto(page);
        photoList
                .subscribeOn(Schedulers.io())
                .map(recentPhotoResponse -> recentPhotoResponse.photos)
                .filter(photosPageInfo -> photosPageInfo != null && photosPageInfo.photo != null && photosPageInfo.photo.size() > 0)
                .flatMap(photosPageInfo -> Observable.from(photosPageInfo.photo))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(photo -> photoDataModel.add(photo, false), // onNext
                        throwable -> getView().showFailLoadImage(), // onError
                        () -> getView().refresh()); // onCompleted
    }
}
