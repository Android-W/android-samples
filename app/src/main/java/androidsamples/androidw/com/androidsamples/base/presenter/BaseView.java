package androidsamples.androidw.com.androidsamples.base.presenter;

import android.support.annotation.NonNull;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public interface BaseView<T extends BasePresenter> {

    void setPresenter(@NonNull T presenter);

    void showProgress();

    void hideProgress();
}
