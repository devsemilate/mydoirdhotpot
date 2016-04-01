package core.codersaigon.axe.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by coder saigon on 28-Mar-16.
 */
public class CategoryContent implements Parcelable{
    String id;
    String rssLink;

    public CategoryContent(String id, String rssLink) {
        super();
        this.id = id;
        this.rssLink = rssLink;
    }

    public CategoryContent() {
        super();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRssLink() {
        return rssLink;
    }

    public void setRssLink(String rssLink) {
        this.rssLink = rssLink;
    }

    @SuppressWarnings("unused")
    public CategoryContent(Parcel in)
    {
        this();
        readFromParcel(in);
    }

    public void readFromParcel(Parcel in)
    {
        this.id = in.readString();
        this.rssLink = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public final Parcelable.Creator<CategoryContent> CREATOR = new Creator<CategoryContent>() {
        @Override
        public CategoryContent createFromParcel(Parcel source) {
            return new CategoryContent(source);
        }

        @Override
        public CategoryContent[] newArray(int size) {
            return new CategoryContent[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.getId());
        dest.writeString(this.getRssLink());
    }
}
