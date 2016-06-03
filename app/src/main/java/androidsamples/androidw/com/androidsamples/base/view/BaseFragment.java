package androidsamples.androidw.com.androidsamples.base.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(getLayoutResource(), container, false);

        ButterKnife.bind(this, rootView);

        onCreateView(rootView, savedInstanceState);

        return rootView;
    }

    @LayoutRes
    protected abstract int getLayoutResource();

    protected abstract void onCreateView(View rootView, @Nullable Bundle savedInstanceState);
}
