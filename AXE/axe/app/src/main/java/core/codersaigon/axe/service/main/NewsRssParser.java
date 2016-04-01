package core.codersaigon.axe.service.main;

import android.util.Log;

import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.service.core.RssParser;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public class NewsRssParser implements ICanParseNewsRss{
    String type = "generic-normal-defaul";
    ICanParseNewsRss next;

    public NewsRssParser() {
    }

    @Override
    public ICanParseNewsRss buildNext(ICanParseNewsRss _next) {
        ((NewsRssParser)_next).setNext(this);
        return _next;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ICanParseNewsRss getNext() {
        return next;
    }

    public void setNext(ICanParseNewsRss next) {
        this.next = next;
    }

    @Override
    public ICanParseNewsRss next() {
        return next;
    }

    @Override
    public ICanParseNewsRss getParserByType(String _type) {
        if(_type.contains(this.type)){
            return this;
        } else {
            if(this.next() != null) {
                return this.next().getParserByType(_type);
            } else {
                return NewsRssParserFactory.getNormalNewsRssParser(); // return normal parser itseft
            }
        }
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
