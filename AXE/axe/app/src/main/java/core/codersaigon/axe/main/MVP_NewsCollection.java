package core.codersaigon.axe.main;

import android.content.Context;
import android.view.View;

import com.tinmegali.mvp.mvp.ActivityView;
import com.tinmegali.mvp.mvp.ModelOps;
import com.tinmegali.mvp.mvp.PresenterOps;

import java.util.ArrayList;
import java.util.List;

import core.codersaigon.axe.main.model.CategoryContent;
import core.codersaigon.axe.main.model.News;

/**
 * Created by coder saigon on 28-Mar-16.
 */
public interface MVP_NewsCollection {

    interface RequiredActivityOps {
        void gotoNewsActivity(News news, View viewToShare);
    }

    interface RequiredViewOps extends ActivityView{
        void updateNewsCollection(List<News> news);
        void appendNewsCollection(List<News> moreNews);
        void refreshNewsCollection(List<News> refreshNews);
    }

    interface ProvidedPresenterOps extends PresenterOps<RequiredViewOps>{
//        void onResume(RequiredViewOps view);
        void setCategoryContent(ArrayList<CategoryContent> categoryContents);
        ArrayList<CategoryContent> getCategoryContentList();
        void getNewsCollectionAtFirst();
        void loadMoreNewsCollection();
        void refreshNewsCollection();
        void navigateToNewsDetail(News news, View viewToShare);
    }

    interface RequiredPresenterOps {
        Context getContext();
        void onGetNewsCollectionAtFirst(List<News> news);
        void onLoadMoreNewsCollection(List<News> moreNews);
        void onRefreshNewsCollection(List<News> refreshNews);
    }

    interface ProvidedModelOps extends ModelOps<RequiredPresenterOps> {
        void setCategoryContent(ArrayList<CategoryContent> categoryContents);
        ArrayList<CategoryContent> getCategoryContentList();
        void getNewsCollectionAtFirst();
        void loadMoreNewsCollection();
        void refreshNewsCollection();
    }
}
