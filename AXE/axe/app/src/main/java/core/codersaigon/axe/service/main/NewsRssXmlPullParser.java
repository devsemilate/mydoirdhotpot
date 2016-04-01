package core.codersaigon.axe.service.main;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.regex.Pattern;

import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.service.core.RssParser;

/**
 * Created by coder saigon on 30-Mar-16.
 */
public class NewsRssXmlPullParser {
    ICanParseNewsRss specParser;
    public void setRssParseType(String rssLink)
    {
        specParser = NewsRssParserFactory.getNewsRssParserByType(rssLink);
    }
    public ArrayList<News> parseNews(String rssInput)
    {
        ArrayList<News> newsList = new ArrayList<News>();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(rssInput));

            int eventType = xpp.getEventType();
            RssParser.RSSXMLTAG currentTag = RssParser.RSSXMLTAG.IGNORE;
            News news = null;

            while (eventType != XmlPullParser.END_DOCUMENT)
            {
                if(eventType == XmlPullParser.START_DOCUMENT) {

                } else if(eventType == XmlPullParser.START_TAG){
                    if(xpp.getName().equals("item")){
                        news = new News();
                        currentTag = RssParser.RSSXMLTAG.ITEM;
                    } else if(xpp.getName().equals("title")){
                        currentTag = RssParser.RSSXMLTAG.TITLE;
                    } else if(xpp.getName().equals("description")){
                        currentTag = RssParser.RSSXMLTAG.DESCRIPTION;
                    } else if(xpp.getName().equals("image")){
                        currentTag = RssParser.RSSXMLTAG.IMAGE;
                    } else if(xpp.getName().equals("link")){
                        currentTag = RssParser.RSSXMLTAG.LINK;
                    } else if(xpp.getName().equals("pubDate")){
                        currentTag = RssParser.RSSXMLTAG.PUBDATE;
                    }

                } else if(eventType == XmlPullParser.END_TAG){
                    if(xpp.getName().equals("item")){
                        newsList.add(news);
                    } else {
                        currentTag = RssParser.RSSXMLTAG.IGNORE;
                    }
                } else if(eventType == XmlPullParser.TEXT){
                    String content = xpp.getText();
                    content = content.trim();
                    if(news != null) {
                        news = specParser.parse(news, content, currentTag);
                    }
                }

                eventType = xpp.next();
            }

        } catch (Exception ex) {

        }
        return newsList;
    }
}
