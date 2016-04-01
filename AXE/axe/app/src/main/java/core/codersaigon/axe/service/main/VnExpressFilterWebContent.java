package core.codersaigon.axe.service.main;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public class VnExpressFilterWebContent extends FilterWebContent{
    public VnExpressFilterWebContent() {
        this.type = "http://vnexpress.net";
        this.patternCompile = "<div class=\"fck_detail width_common\">(.*?)</div>";
    }
}
