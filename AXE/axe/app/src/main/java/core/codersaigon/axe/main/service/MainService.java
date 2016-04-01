package core.codersaigon.axe.main.service;

import android.content.Context;

import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.service.main.MainRssService;

/**
 * Created by coder saigon on 30-Mar-16.
 */
public class MainService {
    private MainRssService mainRssService;

    public MainService(Context mContext)
    {
        mainRssService = new MainRssService(mContext);
    }

    public MainRssService getMainRssService() {
        return mainRssService;
    }

    public void setMainRssService(MainRssService mainRssService) {
        this.mainRssService = mainRssService;
    }
}
