package core.codersaigon.axe.core.main.model;

import com.tinmegali.mvp.mvp.GenericModel;

import core.codersaigon.axe.core.main.MVP_Main;

/**
 * Created by coder saigon on 22-Mar-16.
 */
public class MainModel extends GenericModel<MVP_Main.RequiredPresenterOps> implements MVP_Main.ProvidedModelOps{

    @Override
    public void onCreate(MVP_Main.RequiredPresenterOps requiredPresenterOps) {
        super.onCreate(requiredPresenterOps);
    }

    @Override
    public void onDestroy(boolean isChangingConfiguration) {

    }

    int count = 0;

    @Override
    public void getCount() {
        this.getPresenter().onGetCurrentCount(count);
    }

    @Override
    public void increaseCount() {
        count++;
        this.getPresenter().onCountIncreased(count);
    }
}
