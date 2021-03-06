package androidsamples.androidw.com.androidsamples.view.flicker.presenter;

import androidsamples.androidw.com.androidsamples.view.flicker.adapter.model.PhotoDataModel;
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

        void loadPhotos(int page);

        /**
         * RxJava Sample
         */
        void rxLoadPhotos(int page);

        /**
         * RxJava, Lambda
         */
        void rxLambdaLoadPhotos(int page);

        void onPhotoItemClick(int position);
    }
}
