package androidsamples.androidw.com.androidsamples.base.adapter.view;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidsamples.androidw.com.androidsamples.base.BaseItem;
import androidsamples.androidw.com.androidsamples.base.adapter.BaseRecyclerAdapter;
import butterknife.ButterKnife;

/**
 * Created by Tae-hwan on 4/26/16.
 */
public abstract class BaseRecyclerView<T extends BaseItem> extends RecyclerView.ViewHolder {

    private BaseRecyclerAdapter adapter;

    public BaseRecyclerView(BaseRecyclerAdapter adapter, View itemView) {
        super(itemView);

        this.adapter = adapter;

        ButterKnife.bind(this, itemView);
    }

    public BaseRecyclerView(@LayoutRes int layoutRes, ViewGroup parent, BaseRecyclerAdapter adapter) {
        this(adapter, LayoutInflater.from(adapter.getContext()).inflate(layoutRes, parent, false));
    }

    public abstract void onViewHolder(final @Nullable T item, int position);

    protected BaseRecyclerAdapter getAdapter() {
        return adapter;
    }

    protected Context getContext() {
        return adapter.getContext();
    }
}
