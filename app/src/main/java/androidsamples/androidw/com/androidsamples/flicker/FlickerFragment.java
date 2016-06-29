package androidsamples.androidw.com.androidsamples.flicker;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidsamples.androidw.com.androidsamples.R;
import androidsamples.androidw.com.androidsamples.base.view.BaseFragment;
import androidsamples.androidw.com.androidsamples.flicker.adapter.PhotoRecyclerAdapter;
import androidsamples.androidw.com.androidsamples.flicker.presenter.FlickerContract;
import androidsamples.androidw.com.androidsamples.network.bean.Photo;
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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_flicker, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        photoRecyclerAdapter = new PhotoRecyclerAdapter(getContext());
        photoRecyclerAdapter.setOnRecyclerItemClickListener(position -> presenter.onPhotoItemClick(position));
        recyclerView.setAdapter(photoRecyclerAdapter);

        presenter.setDataModel(photoRecyclerAdapter);
//        presenter.loadPhotos(DEFAULT_PAGE);
//        presenter.rxLoadPhotos(DEFAULT_PAGE);
        presenter.rxLambdaLoadPhotos(DEFAULT_PAGE);
    }

    @Override
    public void setPresenter(@NonNull FlickerContract.Presenter presenter) {
        this.presenter = checkNotNull(presenter);
    }

    @Override
    public void showFailLoadImage() {
        Toast.makeText(getContext(), "Network fail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void refresh() {
        photoRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void showBottomSheet(Photo photo) {
        Toast.makeText(getContext(), "Show blur Dialog?", Toast.LENGTH_SHORT).show();
    }
}
