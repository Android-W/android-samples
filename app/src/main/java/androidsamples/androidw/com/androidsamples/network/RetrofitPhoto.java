package androidsamples.androidw.com.androidsamples.network;

import androidsamples.androidw.com.androidsamples.network.bean.RecentPhotoResponse;
import retrofit2.Call;
import retrofit2.Retrofit;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public class RetrofitPhoto {

    private Retrofit retrofit;

    private static RetrofitPhoto retrofitPhoto;

    public static RetrofitPhoto getRetrofitPhoto() {
        if (retrofitPhoto == null) {
            synchronized (RetrofitPhoto.class) {
                if (retrofitPhoto == null) {
                    retrofitPhoto = new RetrofitPhoto();
                }
            }
        }
        return retrofitPhoto;
    }

    private RetrofitPhoto() {
        retrofit = RetrofitCreator.createRetrofit();
    }

    public Call<RecentPhotoResponse> getRecentPhoto(int page) {
        return retrofit.create(PhotoServiceInterface.class).getFlickrPhotos(page);
    }
}
