package core.codersaigon.axe.service.main;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public class Thethao247FilterWebContent extends FilterWebContent{
    public Thethao247FilterWebContent() {
        this.type = "http://thethao247.vn/";
        this.patternCompile = "<div id=\"main-detail\">(.*?)</div>";
    }
}
