package androidsamples.androidw.com.androidsamples.network;

import androidsamples.androidw.com.androidsamples.BuildConfig;
import androidsamples.androidw.com.androidsamples.network.bean.RecentPhotoResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public interface PhotoServiceInterface {

    @GET("?format=json&nojsoncallback=1&method=flickr.interestingness.getList&api_key=" + BuildConfig.FLICKER_API_KEY)
    Call<RecentPhotoResponse> getFlickrPhotos(@Query("page") int page);

    @GET("?format=json&nojsoncallback=1&method=flickr.interestingness.getList&api_key=" + BuildConfig.FLICKER_API_KEY)
    Observable<RecentPhotoResponse> getObservableFlickrPhotos(@Query("page") int page);
}
