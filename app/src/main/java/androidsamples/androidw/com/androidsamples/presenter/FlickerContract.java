package androidsamples.androidw.com.androidsamples.presenter;

import androidsamples.androidw.com.androidsamples.base.presenter.IBasePresenter;
import androidsamples.androidw.com.androidsamples.base.presenter.view.BaseView;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public class FlickerContract {

    public interface View extends BaseView<Presenter> {

    }

    public interface Presenter extends IBasePresenter {

    }
}