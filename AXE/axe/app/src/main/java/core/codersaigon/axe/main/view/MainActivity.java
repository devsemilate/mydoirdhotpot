package core.codersaigon.axe.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.tinmegali.mvp.mvp.GenericMVPActivity;

import core.codersaigon.axe.R;
import core.codersaigon.axe.main.MVP_Main;
import core.codersaigon.axe.main.presenter.MainPresenter;

public class MainActivity extends GenericMVPActivity<MVP_Main.RequireViewOps,
        MVP_Main.ProvidedPresenterOps,
        MainPresenter>
        implements
        MVP_Main.RequireViewOps
{

    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(MainPresenter.class, this);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                increaseCount();
            }
        });

        tvCount = (TextView)findViewById(R.id.tvCount);
        tvCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoSubMain();
            }
        });

        getCurrentCount();
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void getCurrentCount()
    {
        this.getPresenter().getCurrentCount();
    }

    private void increaseCount()
    {
        this.getPresenter().clickToIncreaseCount();
    }

    private void gotoSubMain()
    {
        Intent i = new Intent(this, SubMainOneActivity.class);
        i.putExtra("currentCount", 10);
        startActivity(i);
    }

    @Override
    public void updateCurrentCount(int currentCount) {
        tvCount.setText(""+currentCount);
//        Snackbar.make(view, "current count is : " + currentCount, Snackbar.LENGTH_LONG)
//        .setAction("Action", null).show();
    }
}
