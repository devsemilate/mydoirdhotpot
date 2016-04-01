package core.codersaigon.axe.main.view.CustomView;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by coder saigon on 29-Mar-16.
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration{
    private int mSpace;

    public SpacesItemDecoration(int mSpace) {
        this.mSpace = mSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = this.mSpace;
        outRect.right = this.mSpace;
        outRect.bottom = this.mSpace;

        if(parent.getChildAdapterPosition(view) == 0){
            outRect.top = this.mSpace;
        }
    }
}
