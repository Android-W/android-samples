package androidsamples.androidw.com.androidsamples.base.presenter;

/**
 * Created by Tae-hwan on 6/2/16.
 *
 * Presenter의 View를 강제하기 위한 추상 클래스
 */
public abstract class AbstractPresenter<T extends BaseView> implements BasePresenter {

    private T view;

    public AbstractPresenter(T view) {
        this.view = view;

        view.setPresenter(this);
    }

    public T getView() {
        return view;
    }
}
