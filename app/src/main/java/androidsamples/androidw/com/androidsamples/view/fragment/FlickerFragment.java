package androidsamples.androidw.com.androidsamples.view.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidsamples.androidw.com.androidsamples.R;
import androidsamples.androidw.com.androidsamples.adapter.PhotoRecyclerAdapter;
import androidsamples.androidw.com.androidsamples.base.view.BaseFragment;
import androidsamples.androidw.com.androidsamples.presenter.contract.FlickerContract;
import butterknife.BindView;
import butterknife.ButterKnife;

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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_flicker, container, false);
        ButterKnife.bind(this, root);
        onCreate();
        return root;
    }

    private void onCreate() {
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
