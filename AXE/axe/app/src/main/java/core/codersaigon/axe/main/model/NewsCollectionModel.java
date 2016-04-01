package core.codersaigon.axe.main.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import com.tinmegali.mvp.mvp.GenericModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import core.codersaigon.axe.main.MVP_NewsCollection;
import core.codersaigon.axe.main.presenter.NewsCollectionPresenter;
import core.codersaigon.axe.main.service.MainService;
import core.codersaigon.axe.service.core.RssRequest;
import core.codersaigon.axe.service.core.RssRequestListener;
import core.codersaigon.axe.service.main.MainRssRequestListener;

/**
 * Created by coder saigon on 28-Mar-16.
 */
public class NewsCollectionModel extends GenericModel<MVP_NewsCollection.RequiredPresenterOps> implements MVP_NewsCollection.ProvidedModelOps
{
    ArrayList<CategoryContent> categoryContentCollection = new ArrayList<>();
    Map<String, ArrayList<News>> newsCollection = new HashMap<>();
    MainService mainService;

    @Override
    public void onCreate(MVP_NewsCollection.RequiredPresenterOps requiredPresenterOps) {
        super.onCreate(requiredPresenterOps);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }

    @Override
    public void setCategoryContent(ArrayList<CategoryContent> _categoryContents) {

        if(mainService == null)
            mainService = new MainService(this.getPresenter().getContext());

        if(_categoryContents != null) {
            this.newsCollection.clear();
            this.categoryContentCollection.clear();
            for (CategoryContent catContent : _categoryContents) {
                Log.d("test","add content : " + catContent.getRssLink());
                this.categoryContentCollection.add(catContent);
                this.newsCollection.put(catContent.getRssLink(), new ArrayList<News>());
                mainService.getMainRssService().init(catContent.getRssLink());
            }
        }
    }

    @Override
    public ArrayList<CategoryContent> getCategoryContentList() {
        return new ArrayList<CategoryContent>(this.categoryContentCollection);
    }

    @Override
    public void getNewsCollectionAtFirst() {
        this.getNewsCollectionAtPage(0);
    }

    @Override
    public void loadMoreNewsCollection() {
        
    }

    @Override
    public void refreshNewsCollection() {

    }
    
    private void getNewsCollectionAtPage(int page)
    {
        final MVP_NewsCollection.RequiredPresenterOps presenter = this.getPresenter();
        if(page == 0) {
            mainService.getMainRssService().start(this.categoryContentCollection.get(0).getRssLink(),
                    new MainRssRequestListener(){
                        @Override
                        public void onSuccess(ArrayList<? extends Parcelable> result) {
                            ArrayList<News> newsList = new ArrayList<News>();
                            for(Parcelable p : result)
                            {
                                if(p instanceof News)
                                {
                                    newsList.add((News)p);
                                }
                            }
                            presenter.onGetNewsCollectionAtFirst(newsList);
                        }
                    }
            );
        } else {

        }
    }


}
