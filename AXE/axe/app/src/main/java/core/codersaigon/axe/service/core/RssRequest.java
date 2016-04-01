package core.codersaigon.axe.service.core;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

/**
 * Created by coder saigon on 30-Mar-16.
 */
public abstract class RssRequest implements RssParserListener{
    private Context mContext;
    private String rssLink;
    private RssRequestListener rssRequestListener;
    private final RssParser rssParser;

    public RssRequest(Context mContext, String rssLink, RssParser rssParser) {
        this.mContext = mContext;
        this.rssLink = rssLink;
        this.rssParser = rssParser;
        this.rssParser.setRssParserListener(this);
    }

    public RssRequest setRssRequestListener(RssRequestListener rssRequestListener)
    {
        this.rssRequestListener = rssRequestListener;
        return this;
    }

    public void start()
    {
        final RssRequestListener finalRssRequestListener = this.rssRequestListener;

        StringRequest stringRequest = new StringRequest(StringRequest.Method.GET, this.rssLink,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        rssParser.executeParseRss(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(finalRssRequestListener != null){
                            finalRssRequestListener.onError();
                        }
                    }
                }
        );

        Volley.newRequestQueue(this.mContext.getApplicationContext()).add(stringRequest);
    }

    public String getRssLink() {
        return rssLink;
    }

    public void setRssLink(String rssLink) {
        this.rssLink = rssLink;
    }

    @Override
    public void onParseSuccess(ArrayList<? extends Parcelable> result) {
        if(this.rssRequestListener != null){
            this.rssRequestListener.onSuccess(result);
        }
    }

    @Override
    public void onParseError() {
        if(this.rssRequestListener != null)
            this.rssRequestListener.onError();
    }
}
