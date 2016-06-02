package androidsamples.androidw.com.androidsamples.presenter.contract;

import androidsamples.androidw.com.androidsamples.adapter.model.PhotoDataModel;
import androidsamples.androidw.com.androidsamples.base.presenter.IBasePresenter;
import androidsamples.androidw.com.androidsamples.base.presenter.view.BaseView;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public class FlickerContract {

    public interface View extends BaseView<Presenter> {

        void refresh();
    }

    public interface Presenter extends IBasePresenter {

        void setDataModel(PhotoDataModel photoDataModel);

        void loadPhotos(int defaultPage);
    }
}
