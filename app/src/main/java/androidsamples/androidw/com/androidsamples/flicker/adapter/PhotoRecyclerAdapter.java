package androidsamples.androidw.com.androidsamples.flicker.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidsamples.androidw.com.androidsamples.flicker.adapter.model.PhotoDataModel;
import androidsamples.androidw.com.androidsamples.flicker.adapter.view.PhotoRecyclerView;
import androidsamples.androidw.com.androidsamples.base.adapter.BaseRecyclerAdapter;
import androidsamples.androidw.com.androidsamples.base.adapter.view.BaseRecyclerView;
import androidsamples.androidw.com.androidsamples.listener.OnRecyclerItemClickListener;
import androidsamples.androidw.com.androidsamples.network.bean.Photo;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoRecyclerAdapter extends BaseRecyclerAdapter<Photo> implements PhotoDataModel {

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public PhotoRecyclerAdapter(Context context) {
        super(context);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public BaseRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PhotoRecyclerView(parent, this, onRecyclerItemClickListener);
    }

    @Override
    public void add(Photo photo) {
        addItem(photo);
    }

    @Override
    public void add(Photo photo, boolean isNotify) {
        addItem(photo, isNotify);
    }

    @Override
    public void remove(int position) {
        removeItem(position);
    }

    @Override
    public Photo getPhotoItem(int position) {
        return getItem(position);
    }

    @Override
    public int getSize() {
        return getItemCount();
    }
}
