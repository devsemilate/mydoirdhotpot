package core.codersaigon.axe.main.view;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.tinmegali.mvp.mvp.GenericMVPActivity;

import java.util.ArrayList;
import java.util.List;

import core.codersaigon.axe.R;
import core.codersaigon.axe.main.MVP_Main;
import core.codersaigon.axe.main.MVP_NewsCollection;
import core.codersaigon.axe.main.model.Category;
import core.codersaigon.axe.main.model.CategoryContent;
import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.main.presenter.MainPresenter;
import core.codersaigon.axe.main.view.adapter.NvDrawerItemListAdapter;

public class MainActivity extends GenericMVPActivity<MVP_Main.RequireViewOps,
        MVP_Main.ProvidedPresenterOps,
        MainPresenter>
        implements
        MVP_Main.RequireViewOps,
        MVP_NewsCollection.RequiredActivityOps
{

    Toolbar toolbar;
    DrawerLayout mDrawer;
    ActionBarDrawerToggle drawerToggle;
    ListView nvItemListView;
    ArrayList<Category> categories = new ArrayList<>();
    NvDrawerItemListAdapter navItemListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(MainPresenter.class, this);

        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDrawer = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, mDrawer, R.string.drawer_open, R.string.drawer_close);
        mDrawer.addDrawerListener(drawerToggle);
        nvItemListView = (ListView) findViewById(R.id.nv_list);
        setUpNavigationDrawerContent();

        this.getPresenter().getCategories();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void setUpNavigationDrawerContent()
    {
        navItemListAdapter = new NvDrawerItemListAdapter(this, categories);
        nvItemListView.setAdapter(navItemListAdapter);
        nvItemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("test", "click on item : " + position);
                setlectDrawerItem(position);
            }
        });
        navItemListAdapter.notifyDataSetChanged();
    }

    private void setlectDrawerItem(int position)
    {
        navItemListAdapter.setCurrentSelectedPos(position);
        this.getPresenter().selectCategory(categories.get(position).getId());
    }

    public void replaceCategoryContentFragment(List<CategoryContent> categoryContents)
    {
        int currentPosition = navItemListAdapter.getCurrentSelectedPos();
        Fragment fragment = null;

        switch (categories.get(currentPosition).getKind())
        {
            case NewsCollection:
                try
                {
                    fragment = NewsCollectionFragment.newInstance(new ArrayList<CategoryContent>(categoryContents));
                }
                catch (Exception ex)
                {
                }
                break;
            case Other:
                try
                {
                    fragment = OtherFragment.newInstance();
                }
                catch (Exception ex)
                {
                }
                break;
            default:
                try
                {
                    fragment = NewsCollectionFragment.newInstance(new ArrayList<CategoryContent>(categoryContents));
                }
                catch (Exception ex)
                {
                }
        }

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

        mDrawer.closeDrawers();
    }

    @Override
    public void updateCategories(List<Category> _categories) {
        this.categories.clear();
        this.categories.addAll(_categories);
        navItemListAdapter.notifyDataSetChanged();
    }

    @Override
    public void onCategorySelected(List<CategoryContent> categoryContents) {
        replaceCategoryContentFragment(categoryContents);
    }

    @Override
    public void gotoNewsActivity(News news, View viewToShare) {

    }
}
