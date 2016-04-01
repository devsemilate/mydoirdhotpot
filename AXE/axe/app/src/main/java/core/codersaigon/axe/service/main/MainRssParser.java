package core.codersaigon.axe.service.main;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.service.core.RssParser;

/**
 * Created by coder saigon on 30-Mar-16.
 */
class MainRssParser extends RssParser{

    private NewsRssXmlPullParser parser;

    public MainRssParser() {
        super();
        parser = new NewsRssXmlPullParser();
    }

    public void setRssParseType(String rsslink)
    {
        if(parser != null)
        {
            parser.setRssParseType(rsslink);
        }
    }

    public ArrayList<? extends Parcelable> parseRss(String rssInput)
    {
        ArrayList<News> result = parser.parseNews(rssInput);
        return result;
    }
}
