package core.codersaigon.axe.main.model;

import com.tinmegali.mvp.mvp.GenericModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.codersaigon.axe.main.MVP_Main;

/**
 * Created by coder saigon on 22-Mar-16.
 */
public class MainModel extends GenericModel<MVP_Main.RequiredPresenterOps> implements MVP_Main.ProvidedModelOps{
    List<Category> categories = new ArrayList<>();
    Map<String, List<CategoryContent>> categoryContents = new HashMap<>();

    @Override
    public void onCreate(MVP_Main.RequiredPresenterOps requiredPresenterOps) {
        super.onCreate(requiredPresenterOps);

        /**
         init category
         * */
        Category bongda = new Category("bongda", "Bóng đá","");
            CategoryContent bongdaAnh = new CategoryContent("bongdaAnh", "http://thethao247.vn/bong-da-anh-c8.rss");
        Category thoisu = new Category("thoisu", "Thời sự","");
            CategoryContent thoisuVnExpress = new CategoryContent("thoisuVnExpress", "http://vnexpress.net/rss/thoi-su.rss");
        /* init category list of main */
        categories.add(bongda);
        categoryContents.put("bongda",
                Arrays.asList(
                 bongdaAnh
                )
        );

        categories.add(thoisu);

        categoryContents.put("thoisu",
                Arrays.asList(
                        thoisuVnExpress
                )
        );

    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }

    @Override
    public void getCategories() {
        this.getPresenter().onGetCategoriesData(this.categories);
    }

    @Override
    public void getCategoryContent(String id) {
        List<CategoryContent> result = new ArrayList<>();
        result = categoryContents.get(id);
        this.getPresenter().onGetCategoryContent(result);
    }
}
