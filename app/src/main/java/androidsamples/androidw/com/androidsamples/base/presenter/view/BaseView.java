package androidsamples.androidw.com.androidsamples.base.presenter.view;

import android.support.annotation.NonNull;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public interface BaseView<T> {

    void setPresenter(@NonNull T presenter);
}
