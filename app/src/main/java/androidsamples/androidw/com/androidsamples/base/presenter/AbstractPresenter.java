package androidsamples.androidw.com.androidsamples.base.presenter;

/**
 * Created by Tae-hwan on 6/2/16.
 * <p/>
 * Presenter의 View를 강제하기 위한 추상 클래스
 */
public abstract class AbstractPresenter<T extends BaseView> implements BasePresenter {

    private T view;

    public AbstractPresenter(T view) {
        this.view = view;

        view.setPresenter(this);
    }

    protected T getView() {
        return view;
    }

    /**
     * View Attached 상태 확인
     */
    protected boolean isAttached() {
        return view != null;
    }

    @Override
    public void onDestroy() {
        view = null;
    }
}
