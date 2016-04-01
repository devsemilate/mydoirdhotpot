package core.codersaigon.axe.service.main;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public interface ICanFilterWebContent {
    ICanFilterWebContent buildNext(ICanFilterWebContent next);
    ICanFilterWebContent next();
    ICanFilterWebContent getFilter(String type);
    String getPatternCompile();
}
