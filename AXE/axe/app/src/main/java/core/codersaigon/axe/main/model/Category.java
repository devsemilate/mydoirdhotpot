package core.codersaigon.axe.main.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by coder saigon on 28-Mar-16.
 */
public class Category {
    public static enum KINDOFCATEGORY {
        NewsCollection, Categoty, Other
    };
    String id;
    String title;
    String iconImagePath;

    public KINDOFCATEGORY getKind() {
        return kind;
    }

    public void setKind(KINDOFCATEGORY kind) {
        this.kind = kind;
    }

    KINDOFCATEGORY kind;

    public  Category()
    {

    }

    public Category(String id, String title, String iconImagePath) {
        this.id = id;
        this.title = title;
        this.iconImagePath = iconImagePath;
        this.kind = KINDOFCATEGORY.NewsCollection;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIconImagePath() {
        return iconImagePath;
    }

    public void setIconImagePath(String iconImagePath) {
        this.iconImagePath = iconImagePath;
    }
}
