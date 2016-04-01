package core.codersaigon.axe.main.view.adapter;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import core.codersaigon.axe.R;
import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.service.core.ImagePool;

/**
 * Created by coder saigon on 29-Mar-16.
 */
public class NewsCollectionAdapter extends RecyclerView.Adapter<NewsCollectionAdapter.ViewHolder> implements View.OnClickListener{
    private Context mContext;
    private OnItemClickListener onItemClickListener;

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView textTitle;
        public NetworkImageView imageDesctiprion;
        public TextView textDescription;

        public ViewHolder(View itemView) {
            super(itemView);

            textTitle = (TextView) itemView.findViewById(R.id.tvNewsTitle);
            imageDesctiprion = (NetworkImageView) itemView.findViewById(R.id.imgVNewsImageDescription);
            textDescription = (TextView) itemView.findViewById(R.id.tvNewsTextDescription);
        }
    }

    private List<News> newsList;

    public NewsCollectionAdapter(Context mContext, List<News> _newsList) {
        this.mContext = mContext;
        if(_newsList != null) {
            this.newsList = _newsList;
        } else {
            this.newsList = new ArrayList<>();
        }
    }

    public void setOnItemClickListener(OnItemClickListener _onItemClickListener)
    {
        this.onItemClickListener = _onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View newsView = layoutInflater.inflate(R.layout.news_collection_item, parent, false);
        newsView.setOnClickListener(this);
        ViewHolder viewHolder = new ViewHolder(newsView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        News news = this.newsList.get(position);

        TextView textNewsTitle = holder.textTitle;
        textNewsTitle.setText(news.getTitle());

        NetworkImageView imgDescription = holder.imageDesctiprion;
        imgDescription.setBackgroundColor(Color.LTGRAY);
        imgDescription.setImageUrl(news.getImage(), ImagePool.getmInstance(mContext).getImageLoader());

        TextView textDescription = holder.textDescription;
        textDescription.setText(news.getDescription());

        holder.itemView.setTag(news);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    @Override
    public void onClick(final View v) {
        if(onItemClickListener != null){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick((News) v.getTag(), v);
                }
            }, 200);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(News news, View view);
    }
}
