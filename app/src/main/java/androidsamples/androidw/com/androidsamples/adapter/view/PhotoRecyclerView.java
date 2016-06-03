package androidsamples.androidw.com.androidsamples.adapter.view;

import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import androidsamples.androidw.com.androidsamples.R;
import androidsamples.androidw.com.androidsamples.adapter.PhotoRecyclerAdapter;
import androidsamples.androidw.com.androidsamples.base.adapter.view.BaseRecyclerView;
import androidsamples.androidw.com.androidsamples.listener.OnRecyclerItemClickListener;
import androidsamples.androidw.com.androidsamples.network.bean.Photo;
import butterknife.BindView;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public class PhotoRecyclerView extends BaseRecyclerView<PhotoRecyclerAdapter, Photo> {

    @BindView(R.id.image)
    ImageView imageView;

    private OnRecyclerItemClickListener onRecyclerItemClickListener;

    public PhotoRecyclerView(ViewGroup parent, PhotoRecyclerAdapter adapter, OnRecyclerItemClickListener onRecyclerItemClickListener) {
        super(R.layout.item_photo_view, parent, adapter);

        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    @Override
    public void onViewHolder(@Nullable Photo item, final int position) {
        if (item != null) {

            Glide.with(getContext())
                    .load(String.format("https://farm%s.staticflickr.com/%s/%s_%s.jpg", String.valueOf(item.farm), item.server, item.id, item.secret))
                    .centerCrop()
                    .crossFade()
                    .into(imageView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onRecyclerItemClickListener != null) {
                        onRecyclerItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}
