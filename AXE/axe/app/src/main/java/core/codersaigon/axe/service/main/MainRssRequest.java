package core.codersaigon.axe.service.main;

import android.content.Context;

import core.codersaigon.axe.service.core.RssParser;
import core.codersaigon.axe.service.core.RssRequest;

/**
 * Created by coder saigon on 30-Mar-16.
 */
public class MainRssRequest extends RssRequest{

    public MainRssRequest(Context mContext, String rssLink, MainRssParser rssParser) {
        super(mContext, rssLink, rssParser);
        rssParser.setRssParseType(rssLink);
    }

}
