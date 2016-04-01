package core.codersaigon.axe.main;

import com.tinmegali.mvp.mvp.ActivityView;
import com.tinmegali.mvp.mvp.ModelOps;
import com.tinmegali.mvp.mvp.PresenterOps;

import java.util.List;

import core.codersaigon.axe.main.model.Category;
import core.codersaigon.axe.main.model.CategoryContent;

/**
 * Created by coder saigon on 22-Mar-16.
 */

public interface MVP_Main {

    /*
        required view method available to presenter
        main - presenter to view
     */
    interface RequireViewOps extends ActivityView
    {
        void updateCategories(List<Category> categories);
        void onCategorySelected(List<CategoryContent> categoryContents);
    }

    /*
        operations offered to view to communicate with presenter
        view to presenter
     */
    interface  ProvidedPresenterOps extends PresenterOps<RequireViewOps>
    {
        void getCategories();
        void selectCategory(String id);
    }

    /*
        required presenter methods available to model
        model to presenter
    */
    interface  RequiredPresenterOps
    {
        void onGetCategoriesData(List<Category> categories);
        void onGetCategoryContent(List<CategoryContent> categoryContents);
    }

    /*
        operations offered to presenter to communicate with model
        presenter to model
    */
    interface ProvidedModelOps extends ModelOps<RequiredPresenterOps>
    {
        void getCategories();
        void getCategoryContent(String id);
    }

}
