package androidsamples.androidw.com.androidsamples.presenter;

import android.util.Log;

import androidsamples.androidw.com.androidsamples.adapter.model.PhotoDataModel;
import androidsamples.androidw.com.androidsamples.base.presenter.BasePresenter;
import androidsamples.androidw.com.androidsamples.network.RetrofitPhoto;
import androidsamples.androidw.com.androidsamples.network.bean.Photo;
import androidsamples.androidw.com.androidsamples.network.bean.PhotosPageInfo;
import androidsamples.androidw.com.androidsamples.network.bean.RecentPhotoResponse;
import androidsamples.androidw.com.androidsamples.presenter.contract.FlickerContract;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public class FlickerPresenter extends BasePresenter<FlickerContract.View> implements FlickerContract.Presenter {

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
    public void loadPhotos(int page) {
        Call<RecentPhotoResponse> photos = retrofitPhoto.getRecentPhoto(page);
        photos.enqueue(new Callback<RecentPhotoResponse>() {
            @Override
            public void onResponse(Call<RecentPhotoResponse> call, Response<RecentPhotoResponse> response) {
                loadImage(response);
            }

            @Override
            public void onFailure(Call<RecentPhotoResponse> call, Throwable t) {
                failLoadImage();
            }
        });
    }

    private void loadImage(Response<RecentPhotoResponse> response) {
        if (!response.isSuccessful()) {
            Log.e("TAG", "Fail onResponse");
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

    private void failLoadImage() {
        Log.e("TAG", "onFailure");
    }
}
