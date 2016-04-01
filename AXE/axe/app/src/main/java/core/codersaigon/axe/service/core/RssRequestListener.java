package core.codersaigon.axe.service.core;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by coder saigon on 30-Mar-16.
 */
public interface RssRequestListener {
    void onPrepare();
    void onSuccess(ArrayList<? extends Parcelable> result);
    void onError();
}
