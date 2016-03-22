package core.codersaigon.axe.branchone;

import com.tinmegali.mvp.mvp.ActivityView;
import com.tinmegali.mvp.mvp.ModelOps;
import com.tinmegali.mvp.mvp.PresenterOps;

/**
 * Created by coder saigon on 22-Mar-16.
 */
public interface MVP_BranchOne {
    /*
    required view method available to presenter
    main - presenter to view
 */
    interface RequireViewOps extends ActivityView
    {

    }

    /*
        operations offered to view to communicate with presenter
        view to presenter
     */
    interface  ProvidedPresenterOps extends PresenterOps<RequireViewOps>
    {

    }

    /*
        required presenter methods available to model
        model to presenter
    */
    interface  RequiredPresenterOps
    {

    }

    /*
        operations offered to presenter to communicate with model
        presenter to model
    */
    interface ProvidedModelOps extends ModelOps<RequiredPresenterOps>
    {

    }
}
