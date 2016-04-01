package core.codersaigon.axe.service.main;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

import core.codersaigon.axe.service.core.RssService;

/**
 * Created by coder saigon on 30-Mar-16.
 */
public class MainRssService extends RssService{
    private Context mContext;

    public MainRssService(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public MainRssService(String serviceId, Context mContext) {
        super(serviceId);
        this.mContext = mContext;
    }

    public void init(String rssLink)
    {
        this.addRssRequest(new MainRssRequest(this.mContext, rssLink, new MainRssParser()));
    }

    public void init(ArrayList<String> arrayListRssLink)
    {
        for(String rssLink : arrayListRssLink)
        {
            init(rssLink);
        }
    }
}
