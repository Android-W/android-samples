package androidsamples.androidw.com.androidsamples.view.flicker;

import android.os.Bundle;
import android.support.annotation.Nullable;

import androidsamples.androidw.com.androidsamples.R;
import androidsamples.androidw.com.androidsamples.base.view.BaseActivity;
import androidsamples.androidw.com.androidsamples.view.flicker.presenter.FlickerPresenter;
import androidsamples.androidw.com.androidsamples.network.RetrofitPhoto;
import androidsamples.androidw.com.androidsamples.util.ActivityUtil;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public class FlickerActivity extends BaseActivity {

    private FlickerPresenter flickerPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flicker);

        FlickerFragment flickerFragment = (FlickerFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (flickerFragment == null) {
            flickerFragment = FlickerFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), flickerFragment, R.id.contentFrame);
        }

        flickerPresenter = new FlickerPresenter(flickerFragment, RetrofitPhoto.getRetrofitPhoto());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (flickerPresenter != null) {
            flickerPresenter.onDestroy();
        }
    }
}
