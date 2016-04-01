package core.codersaigon.axe.main.view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tinmegali.mvp.mvp.GenericMVPFragment;

import java.util.ArrayList;
import java.util.List;

import core.codersaigon.axe.R;
import core.codersaigon.axe.main.MVP_NewsCollection;
import core.codersaigon.axe.main.model.CategoryContent;
import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.main.presenter.NewsCollectionPresenter;
import core.codersaigon.axe.main.view.CustomView.SpacesItemDecoration;
import core.codersaigon.axe.main.view.adapter.NewsCollectionAdapter;

/**
 * Created by coder saigon on 28-Mar-16.
 */
public class NewsCollectionFragment extends GenericMVPFragment<MVP_NewsCollection.RequiredActivityOps,
        MVP_NewsCollection.RequiredViewOps,
        MVP_NewsCollection.ProvidedPresenterOps,
        NewsCollectionPresenter>
        implements
        MVP_NewsCollection.RequiredViewOps,
        NewsCollectionAdapter.OnItemClickListener

{
    private static String CategoryContentList = "CategoryContentList";
    ArrayList<CategoryContent> categoryContents = new ArrayList<>();
    private RecyclerView rvNewsCollection;
    private NewsCollectionAdapter newsCollectionAdapter;
    private ArrayList<News> newsList = new ArrayList<>();

    public static NewsCollectionFragment newInstance(ArrayList<CategoryContent> _categoryContents) {
        Log.d("fragment","newInstace");
        Bundle args = new Bundle();
        args.putParcelableArrayList(CategoryContentList, _categoryContents);
        NewsCollectionFragment fragment = new NewsCollectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("fragment","on create");
        super.onCreate(savedInstanceState);
        super.onCreate(NewsCollectionPresenter.class, this);

        if(getArguments() != null) {
            try {
                ArrayList<CategoryContent> categoryContents = getArguments().getParcelableArrayList(CategoryContentList);
                setCategoryContent(categoryContents);
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("fragment","on create view");
        View v = inflater.inflate(R.layout.news_collection_fragment, container, false);

        rvNewsCollection = (RecyclerView)v.findViewById(R.id.rvNewsCollection);
        newsCollectionAdapter = new NewsCollectionAdapter(this.getContext(),this.newsList);
        newsCollectionAdapter.setOnItemClickListener(this);
        rvNewsCollection.setAdapter(newsCollectionAdapter);
        rvNewsCollection.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        rvNewsCollection.addItemDecoration(new SpacesItemDecoration(30));

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        this.getPresenter().onCreate(this);
        this.getPresenter().setCategoryContent(this.categoryContents);
        getNewsCollectionOfContentAtFirst();
    }

    @Override
    public void onStart() {
        Log.d("fragment", "on start");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("fragment", "on resume");
        super.onResume();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("fragment","newsaveinstance");
        super.onSaveInstanceState(outState);

        outState.putParcelableArrayList(CategoryContentList, this.getPresenter().getCategoryContentList());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        Log.d("fragment","on view state restore");
        super.onViewStateRestored(savedInstanceState);

        if(savedInstanceState != null)
        {
            ArrayList<CategoryContent> _categoryContents = savedInstanceState.getParcelableArrayList(CategoryContentList);
            if(_categoryContents != null)
                setCategoryContent(_categoryContents);
        }
    }

    private void getNewsCollectionOfContentAtFirst()
    {
        this.getPresenter().getNewsCollectionAtFirst();
    }

    public void setCategoryContent(ArrayList<CategoryContent> categoryContents) {
        this.categoryContents = categoryContents;
    }

    @Override
    public void updateNewsCollection(List<News> news) {
        Log.d("test", "update news collection ..." + news.size());
        this.newsList.clear();
        this.newsList.addAll(news);
        newsCollectionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(News news, View view) {
        this.getPresenter().navigateToNewsDetail(news, view.findViewById(R.id.newsView));
    }

    @Override
    public void appendNewsCollection(List<News> moreNews) {

    }

    @Override
    public void refreshNewsCollection(List<News> refreshNews) {

    }

    @Override
    public void onShowToast(String msg) {

    }

    @Override
    public void onShowToast(String msg, int duration) {

    }

    @Override
    public void onShowSnackbar(String msg, View parentView) {

    }

    @Override
    public void onShowSnackbar(String msg, View parentView, int duration) {

    }

    @Override
    public void onShowSnackbar(Snackbar snackbar) {

    }

    @Override
    public void onPause() {
        Log.d("fragment","on pause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("fragment","on stop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("fragment","new destroy view");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("fragment","on destroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("fragment","on detach");
        super.onDetach();
    }
}
