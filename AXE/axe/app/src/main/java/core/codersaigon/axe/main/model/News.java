package core.codersaigon.axe.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by coder saigon on 28-Mar-16.
 */
public class News implements Parcelable{
    String title;
    String description;
    String image;
    String pubDate;
    String link;

    public News()
    {
        super();
        this.title = "";
        this.description = "";
        this.image = "";
        this.pubDate = "";
        this.link = "";
    }

    public News(String title, String description, String image, String pubDate, String link) {
        super();
        this.title = title;
        this.description = description;
        this.image = image;
        this.pubDate = pubDate;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @SuppressWarnings("unused")
    public News(Parcel in)
    {
        this();
        this.readFromParcel(in);
    }

    public void readFromParcel(Parcel in)
    {
        this.title = in.readString();
        this.description = in.readString();
        this.image = in.readString();
        this.pubDate = in.readString();
        this.link = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel source) {
            return new News(source);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getTitle());
        dest.writeString(this.getDescription());
        dest.writeString(this.getImage());
        dest.writeString(this.getPubDate());
        dest.writeString(this.getLink());
    }
}
