package core.codersaigon.axe.service.main;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public class NewsRssParserFactory {
    public static ICanParseNewsRss getNormalNewsRssParser()
    {
        return new NewsRssParser();
    }

    public static ICanParseNewsRss getICanParseNewsRssRepo()
    {
        return getNormalNewsRssParser().buildNext(new VnExpressRssParser());
    }

    public static ICanParseNewsRss getNewsRssParserByType(String type)
    {
        return getICanParseNewsRssRepo().getParserByType(type);
    }
}
