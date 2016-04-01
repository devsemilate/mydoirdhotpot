package core.codersaigon.axe.main.model;

/**
 * Created by coder saigon on 29-Mar-16.
 */
public class NewsDescription {
    String description;
    String imageLink;

    public NewsDescription(String description, String imageLink) {
        this.description = description;
        this.imageLink = imageLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
