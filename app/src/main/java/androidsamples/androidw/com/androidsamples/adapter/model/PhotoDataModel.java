package androidsamples.androidw.com.androidsamples.adapter.model;

import androidsamples.androidw.com.androidsamples.network.bean.Photo;

/**
 * Created by Tae-hwan on 5/3/16.
 */
public interface PhotoDataModel {

    void add(Photo photo);

    void add(Photo photo, boolean isNotify);

    void remove(int position);

    Photo getPhotoItem(int position);

    int getSize();
}
