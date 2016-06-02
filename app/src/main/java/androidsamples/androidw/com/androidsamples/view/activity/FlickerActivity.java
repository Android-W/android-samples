package androidsamples.androidw.com.androidsamples.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import androidsamples.androidw.com.androidsamples.R;
import androidsamples.androidw.com.androidsamples.base.view.BaseActivity;
import androidsamples.androidw.com.androidsamples.network.RetrofitPhoto;
import androidsamples.androidw.com.androidsamples.presenter.FlickerPresenter;
import androidsamples.androidw.com.androidsamples.presenter.contract.FlickerContract;
import androidsamples.androidw.com.androidsamples.util.ActivityUtil;
import androidsamples.androidw.com.androidsamples.view.fragment.FlickerFragment;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public class FlickerActivity extends BaseActivity {

    private FlickerContract.Presenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flicker);

        FlickerFragment flickerFragment =  (FlickerFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
        if (flickerFragment == null) {
            flickerFragment = FlickerFragment.newInstance();
            ActivityUtil.addFragmentToActivity(getSupportFragmentManager(), flickerFragment, R.id.contentFrame);
        }

        presenter = new FlickerPresenter(flickerFragment, RetrofitPhoto.getRetrofitPhoto());
    }
}
