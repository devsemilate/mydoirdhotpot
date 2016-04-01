package core.codersaigon.axe.service.main;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public class FilterWebContentFactory {
    public static ICanFilterWebContent getOriginFilterWebContent()
    {
        return new FilterWebContent();
    }

    public static ICanFilterWebContent getFilterWebContentRepo()
    {
        return getOriginFilterWebContent().buildNext(new VnExpressFilterWebContent())
                .buildNext(new Thethao247FilterWebContent());
    }
}
