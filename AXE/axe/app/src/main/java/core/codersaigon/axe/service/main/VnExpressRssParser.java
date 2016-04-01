package core.codersaigon.axe.service.main;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.service.core.RssParser;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public class VnExpressRssParser extends NewsRssParser{

    public VnExpressRssParser() {
        super();
        this.setType("http://vnexpress.net/rss/");
    }

    @Override
    public News parse(News news, String content, RssParser.RSSXMLTAG tag) {
        switch (tag)
        {
            case TITLE:
                if(content.length() != 0)
                {
                    news.setTitle(content);
                }
                break;
            case DESCRIPTION:
                if(content.length() != 0)
                {
                    Pattern pattern = Pattern.compile("<img(.*?)>");
                    Matcher matcher = pattern.matcher(content);
                    if(matcher.find())
                    {
                        String imgTag = matcher.group(0);
                        Log.d("test", "parse imgTag : " + imgTag);
                        Pattern pattern1 = Pattern.compile("src=\"(.*?)\"");
                        Matcher matcher1 = pattern1.matcher(imgTag);
                        if(matcher1.find())
                        {
                            String imgLink = matcher1.group(0);
                            Log.d("test", "parse imglink : " + imgLink);
                            imgLink = imgLink.replace("src=\"","");
                            imgLink = imgLink.replace("\"","");

                            news.setImage(imgLink);
                        }

                    }
                    // remove image if it exist
                    content = content.replaceAll("<img(.*?)>"," ");
                    content = content.replaceAll("<img(.*?)/>"," ");
                    // remove htmltag
                    content = content.replaceAll("</([A-Za-z][A-Za-z0-9]*)(.*?)>","");
                    content = content.replaceAll("<([A-Za-z][A-Za-z0-9]*)(.*?)/>","");
                    content = content.replaceAll("<([A-Za-z][A-Za-z0-9]*)(.*?)>","");
                    content = content.replaceAll("<([A-Za-z][A-Za-z0-9]*)\\b[^>]*>(.*?)</\\1>","");

                    news.setDescription(content);
                }
                break;
            case IMAGE:
                if(content.length() != 0)
                {
                    news.setImage(content);
                }
                break;
            case PUBDATE:
                if(content.length() != 0)
                {
                    news.setPubDate(content);
                }
                break;
            case LINK:
                if(content.length() != 0)
                {
                    news.setLink(content);
                }
                break;
            default:
                break;
        }

        return news;
    }
}
