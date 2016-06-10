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
    public void start() {

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
}
