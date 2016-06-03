package androidsamples.androidw.com.androidsamples.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import androidsamples.androidw.com.androidsamples.R;
import androidsamples.androidw.com.androidsamples.adapter.PhotoRecyclerAdapter;
import androidsamples.androidw.com.androidsamples.base.view.BaseFragment;
import androidsamples.androidw.com.androidsamples.presenter.contract.FlickerContract;
import butterknife.BindView;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by Tae-hwan on 6/2/16.
 */
public class FlickerFragment extends BaseFragment implements FlickerContract.View {

    private static final int DEFAULT_PAGE = 1;

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private FlickerContract.Presenter presenter;
    private PhotoRecyclerAdapter photoRecyclerAdapter;

    public static FlickerFragment newInstance() {
        return new FlickerFragment();
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_flicker;
    }

    @Override
    protected void onCreateView(View rootView, @Nullable Bundle savedInstanceState) {
        photoRecyclerAdapter = new PhotoRecyclerAdapter(getContext());
        recyclerView.setAdapter(photoRecyclerAdapter);

        presenter.setDataModel(photoRecyclerAdapter);
        presenter.loadPhotos(DEFAULT_PAGE);
    }

    @Override
    public void setPresenter(@NonNull FlickerContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void refresh() {
        photoRecyclerAdapter.notifyDataSetChanged();
    }
}
