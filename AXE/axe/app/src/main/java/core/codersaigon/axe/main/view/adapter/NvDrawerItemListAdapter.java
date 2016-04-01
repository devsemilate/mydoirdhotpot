package core.codersaigon.axe.main.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import core.codersaigon.axe.R;
import core.codersaigon.axe.main.model.Category;

/**
 * Created by coder saigon on 28-Mar-16.
 */
public class NvDrawerItemListAdapter extends ArrayAdapter<Category>{
    private Context mContext;
    private ArrayList<Category> categories = new ArrayList<>();
    private int currentSelectedPos = 0;

    public NvDrawerItemListAdapter(Context context, ArrayList<Category> _categories) {
        super(context, -1, _categories);
        this.mContext = context;
        this.categories = _categories;
    }

    public void setCurrentSelectedPos(int currentPos)
    {
        this.currentSelectedPos = currentPos;
    }

    public int getCurrentSelectedPos()
    {
        return this.currentSelectedPos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = layoutInflater.inflate(R.layout.nav_item, parent, false);
        TextView tvItemTitle = (TextView) itemView.findViewById(R.id.nav_item_title);
        tvItemTitle.setText(categories.get(position).getTitle());
        if(currentSelectedPos == position)
        {
            itemView.setBackgroundColor(Color.GRAY);
        } else {
            itemView.setBackgroundColor(Color.WHITE);
        }
        currentSelectedPos = position;
        return itemView;
    }
}
