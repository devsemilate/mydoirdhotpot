package core.codersaigon.axe.main.presenter;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.tinmegali.mvp.mvp.GenericPresenter;

import java.util.ArrayList;
import java.util.List;

import core.codersaigon.axe.main.MVP_NewsCollection;
import core.codersaigon.axe.main.model.CategoryContent;
import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.main.model.NewsCollectionModel;
import core.codersaigon.axe.main.presenter.navigator.OpenNewsDetailNavigator;

/**
 * Created by coder saigon on 28-Mar-16.
 */
public class NewsCollectionPresenter extends GenericPresenter<MVP_NewsCollection.RequiredPresenterOps,
        MVP_NewsCollection.ProvidedModelOps,
        MVP_NewsCollection.RequiredViewOps,
        NewsCollectionModel>
        implements
        MVP_NewsCollection.RequiredPresenterOps,
        MVP_NewsCollection.ProvidedPresenterOps
{
    private OpenNewsDetailNavigator navigator;
    private int currentPage = 0;
    private int numberOfPage = 0;

    @Override
    public void onCreate(MVP_NewsCollection.RequiredViewOps view) {
        super.onCreate(NewsCollectionModel.class, this);
        setView(view);

        navigator = new OpenNewsDetailNavigator(view.getActivityContext());
    }

    @Override
    public void onConfigurationChanged(MVP_NewsCollection.RequiredViewOps view) {
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void setCategoryContent(ArrayList<CategoryContent> categoryContents) {
        this.numberOfPage = categoryContents.size();
        this.getModel().setCategoryContent(categoryContents);
    }

    @Override
    public void getNewsCollectionAtFirst() {
        this.getModel().getNewsCollectionAtFirst();
    }

    @Override
    public void onGetNewsCollectionAtFirst(List<News> news) {
        Log.d("test", this.getView().toString());
        this.getView().updateNewsCollection(news);
    }

    @Override
    public ArrayList<CategoryContent> getCategoryContentList() {
        return this.getModel().getCategoryContentList();
    }

    @Override
    public void loadMoreNewsCollection() {

    }

    @Override
    public void refreshNewsCollection() {

    }

    @Override
    public void onLoadMoreNewsCollection(List<News> moreNews) {

    }

    @Override
    public void onRefreshNewsCollection(List<News> refreshNews) {

    }

    @Override
    public void navigateToNewsDetail(News news, View viewToShare) {
        navigator.navigateToNewsDetail(news, viewToShare);
    }

    @Override
    public Context getContext() {
        return this.getView().getActivityContext();
    }
}
