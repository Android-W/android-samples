package androidsamples.androidw.com.androidsamples.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import androidsamples.androidw.com.androidsamples.R;
import androidsamples.androidw.com.androidsamples.base.view.BaseActivity;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public class FlickerActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flicker);
    }
}
