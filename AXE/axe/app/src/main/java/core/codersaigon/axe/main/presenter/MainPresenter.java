package core.codersaigon.axe.main.presenter;

import com.tinmegali.mvp.mvp.GenericPresenter;

import java.util.List;

import core.codersaigon.axe.main.MVP_Main;
import core.codersaigon.axe.main.model.Category;
import core.codersaigon.axe.main.model.CategoryContent;
import core.codersaigon.axe.main.model.MainModel;

/**
 * Created by coder saigon on 22-Mar-16.
 */
public class MainPresenter extends GenericPresenter<MVP_Main.RequiredPresenterOps,
        MVP_Main.ProvidedModelOps,
        MVP_Main.RequireViewOps,
        MainModel>
        implements
        MVP_Main.RequiredPresenterOps,
        MVP_Main.ProvidedPresenterOps
{

    @Override
    public void onCreate(MVP_Main.RequireViewOps view) {
        super.onCreate(MainModel.class, this);
        setView(view);
    }

    @Override
    public void onConfigurationChanged(MVP_Main.RequireViewOps view) {
        setView(view);
    }

    @Override
    public void onBackPressed() {

    }

    /* view required presenter*/

    @Override
    public void getCategories() {
        this.getModel().getCategories();
    }

    @Override
    public void selectCategory(String id) {
        this.getModel().getCategoryContent(id);
    }

    /* presenter offer to model */
    @Override
    public void onGetCategoriesData(List<Category> categories)
    {
        this.getView().updateCategories(categories);
    }

    @Override
    public void onGetCategoryContent(List<CategoryContent> categoryContents)
    {
        this.getView().onCategorySelected(categoryContents);
    }
}
