package androidsamples.androidw.com.androidsamples.network.bean;

import androidsamples.androidw.com.androidsamples.base.BaseItem;

public class Photo implements BaseItem {

    public String id;
    public String owner;
    public String secret;
    public String server;
    public long farm;
    public String title;
    public long ispublic;
    public long isfriend;
    public long isfamily;
    public int viewType;

    @Override
    public int getViewType() {
        return viewType;
    }
}
