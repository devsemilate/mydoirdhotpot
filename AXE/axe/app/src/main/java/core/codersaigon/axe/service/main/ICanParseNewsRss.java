package core.codersaigon.axe.service.main;

import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.service.core.RssParser;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public interface ICanParseNewsRss {
    ICanParseNewsRss buildNext(ICanParseNewsRss next);
    ICanParseNewsRss next();
    ICanParseNewsRss getParserByType(String type);
    News parse(News news, String content, RssParser.RSSXMLTAG tag);
}
