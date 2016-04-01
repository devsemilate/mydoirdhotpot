package core.codersaigon.axe.service.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by coder saigon on 30-Mar-16.
 */
public abstract class RssService {
    private String serviceId;
    private Map<String,RssRequest> rssRequestMap;

    public RssService() {
        rssRequestMap = new HashMap<>();
    }

    public RssService(String serviceId) {
        super();
        this.serviceId = serviceId;
    }

    public void addRssRequest(RssRequest rssRequest)
    {
        if(!this.rssRequestMap.containsKey(rssRequest.getRssLink()))
            this.rssRequestMap.put(rssRequest.getRssLink(), rssRequest);
    }

    public void addRssRequests(ArrayList<RssRequest> rssRequests)
    {
        for (RssRequest rssRequest:rssRequests) {
            this.addRssRequest(rssRequest);
        }
    }

    public RssRequest getRssRequest(String rssLink)
    {
        if(this.rssRequestMap.containsKey(rssLink))
        {
            return this.rssRequestMap.get(rssLink);
        }

        return null;
    }

    public void start(String rssLink, RssRequestListener listenner)
    {
         if(this.rssRequestMap.containsKey(rssLink))
         {
             RssRequest request = this.rssRequestMap.get(rssLink);
             request.setRssRequestListener(listenner);
             request.start();
         }
    }

    public void startAll(RssRequestListener listenner)
    {
        Set<String> rssLinkSet = this.rssRequestMap.keySet();
        for (String rsslink: rssLinkSet) {
            this.start(rsslink, listenner);
        }
    }
}
