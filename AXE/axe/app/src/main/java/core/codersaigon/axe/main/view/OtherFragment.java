package core.codersaigon.axe.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import core.codersaigon.axe.R;

/**
 * Created by coder saigon on 28-Mar-16.
 */
public class OtherFragment extends Fragment{

    public static OtherFragment newInstance() {
        
        Bundle args = new Bundle();
        
        OtherFragment fragment = new OtherFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.weather_fragment, container, false);
    }
}
