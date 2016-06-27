package androidsamples.androidw.com.androidsamples.base.presenter;

import android.support.annotation.NonNull;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public interface BaseView<T extends BasePresenter> {

    /**
     * Presenter 셋팅
     */
    void setPresenter(@NonNull T presenter);

    /**
     * 데이터 불러오는 부분 Dialog 보이기
     */
    void showProgress();

    /**
     * 데이터 불러오는 부분 Dialog 숨기기
     */
    void hideProgress();
}
