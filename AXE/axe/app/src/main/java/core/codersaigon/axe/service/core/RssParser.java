package core.codersaigon.axe.service.core;

import android.os.AsyncTask;
import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

/**
 * Created by coder saigon on 30-Mar-16.
 */
public abstract class RssParser {
    public static enum RSSXMLTAG {
        IGNORE,ITEM, TITLE, DESCRIPTION, IMAGE, LINK, PUBDATE
    }
    private RssParserListener rssParserListener;

    public RssParser() {
    }

    public void setRssParserListener(RssParserListener rssParserListener)
    {
        this.rssParserListener = rssParserListener;
    }

    public abstract ArrayList<? extends Parcelable> parseRss(String rssInput);

    public void executeParseRss(String rssInput)
    {
        final RssParserListener parseListener = this.rssParserListener;
        AsyncTask<String,String,ArrayList<? extends Parcelable>> parseAsyncTask = new AsyncTask<String, String, ArrayList<? extends Parcelable>>() {
            @Override
            protected ArrayList<? extends Parcelable> doInBackground(String... params) {
                String rssInput = params[0];
                return parseRss(rssInput);
            }

            @Override
            protected void onPostExecute(ArrayList<? extends Parcelable> ts) {
                if(ts != null){
                    parseListener.onParseSuccess(ts);
                } else {
                    parseListener.onParseError();
                }
            }
        };
        parseAsyncTask.execute(rssInput);
    }
}
