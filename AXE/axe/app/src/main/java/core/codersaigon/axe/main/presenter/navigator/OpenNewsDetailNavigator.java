package core.codersaigon.axe.main.presenter.navigator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.main.view.NewsActivity;

/**
 * Created by coder saigon on 29-Mar-16.
 */
public class OpenNewsDetailNavigator {
    private final Context activityContext;
    public static final String EXTRA = "NEWS";
    public OpenNewsDetailNavigator(Context activityContext) {
        this.activityContext = activityContext;
    }

    public void navigateToNewsDetail(News news, View viewToShare)
    {
        Intent intent = new Intent(activityContext, NewsActivity.class);
        intent.putExtra(OpenNewsDetailNavigator.EXTRA,news);
        ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity)activityContext,
                viewToShare, "newsView");
        ActivityCompat.startActivity((Activity)activityContext,intent, optionsCompat.toBundle());
    }
}
