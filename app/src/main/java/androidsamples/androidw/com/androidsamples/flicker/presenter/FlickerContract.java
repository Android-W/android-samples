package androidsamples.androidw.com.androidsamples.flicker.presenter;

import androidsamples.androidw.com.androidsamples.flicker.adapter.model.PhotoDataModel;
import androidsamples.androidw.com.androidsamples.base.presenter.BasePresenter;
import androidsamples.androidw.com.androidsamples.base.presenter.BaseView;
import androidsamples.androidw.com.androidsamples.network.bean.Photo;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public interface FlickerContract {

    interface View extends BaseView<Presenter> {

        void refresh();

        void showFailLoadImage();

        void showBottomSheet(Photo photo);
    }

    interface Presenter extends BasePresenter {

        void setDataModel(PhotoDataModel photoDataModel);

        void loadPhotos(int defaultPage);

        /**
         * RX Sample
         */
        void rxLoadPhotos(int defaultPage);

        void onPhotoItemClick(int position);
    }
}
