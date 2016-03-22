package core.codersaigon.axe.core.main;

import com.tinmegali.mvp.mvp.ActivityView;
import com.tinmegali.mvp.mvp.ModelOps;
import com.tinmegali.mvp.mvp.PresenterOps;

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
        void updateCurrentCount(int currentCount);
    }

    /*
        operations offered to view to communicate with presenter
        view to presenter
     */
    interface  ProvidedPresenterOps extends PresenterOps<RequireViewOps>
    {
        void getCurrentCount();
        void clickToIncreaseCount();
    }

    /*
        required presenter methods available to model
        model to presenter
    */
    interface  RequiredPresenterOps
    {
        void onGetCurrentCount(int currentCount);
        void onCountIncreased(int increasedCount);
    }

    /*
        operations offered to presenter to communicate with model
        presenter to model
    */
    interface ProvidedModelOps extends ModelOps<RequiredPresenterOps>
    {
        void getCount();
        void increaseCount();
    }

}
