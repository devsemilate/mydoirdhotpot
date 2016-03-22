package core.codersaigon.axe.main.presenter;

import com.tinmegali.mvp.mvp.GenericPresenter;

import core.codersaigon.axe.main.MVP_Main;
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
    public void getCurrentCount() {
        this.getModel().getCount();
    }

    @Override
    public void clickToIncreaseCount() {
        this.getModel().increaseCount();
    }

    /* presenter offer to model */
    @Override
    public void onGetCurrentCount(int currentCount) {
        this.getView().updateCurrentCount(currentCount);
    }

    @Override
    public void onCountIncreased(int increasedCount) {
        this.getView().updateCurrentCount(increasedCount);
    }
}
