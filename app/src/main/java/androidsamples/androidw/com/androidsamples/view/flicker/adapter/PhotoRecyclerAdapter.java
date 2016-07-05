package androidsamples.androidw.com.androidsamples.view.flicker.adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidsamples.androidw.com.androidsamples.base.adapter.BaseRecyclerAdapter;
import androidsamples.androidw.com.androidsamples.base.adapter.view.BaseRecyclerView;
import androidsamples.androidw.com.androidsamples.listener.OnRecyclerItemClickListener;
import androidsamples.androidw.com.androidsamples.network.bean.Photo;
import androidsamples.androidw.com.androidsamples.view.flicker.adapter.model.PhotoDataModel;
import androidsamples.androidw.com.androidsamples.view.flicker.adapter.view.PhotoRecyclerView;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoRecyclerAdapter extends BaseRecyclerAdapter<Photo> implements PhotoDataModel {

    public static final int VIEW_TYPE_PHOTO = 1;

    public static final int VIEW_TYPE_EMPTY = 999;
    public static final int VIEW_TYPE_LOADING = 1000;


    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public PhotoRecyclerAdapter(Context context) {
        super(context);
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public BaseRecyclerView onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_PHOTO:
                return new PhotoRecyclerView(parent, this, onRecyclerItemClickListener);

            default:
                break;
        }
        return null;
    }

    @Override
    public void add(Photo photo, boolean isNotify) {
        if (photo != null) {
            photo.viewType = VIEW_TYPE_PHOTO;
            addItem(photo, isNotify);
        }
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
