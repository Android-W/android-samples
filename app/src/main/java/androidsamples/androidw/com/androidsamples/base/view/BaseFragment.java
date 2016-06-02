package androidsamples.androidw.com.androidsamples.base.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        ButterKnife.bind(this, view);
    }
}
