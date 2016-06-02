package androidsamples.androidw.com.androidsamples.base.presenter;

import androidsamples.androidw.com.androidsamples.base.presenter.view.BaseView;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public abstract class BasePresenter<T extends BaseView> {

    private T view;

    public BasePresenter(T view) {
        this.view = view;

        view.setPresenter(this);
    }

    public T getView() {
        return view;
    }
}
