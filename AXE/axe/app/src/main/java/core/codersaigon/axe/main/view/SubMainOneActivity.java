package core.codersaigon.axe.main.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.tinmegali.mvp.mvp.GenericMVPActivity;

import core.codersaigon.axe.R;
import core.codersaigon.axe.main.MVP_Main;
import core.codersaigon.axe.main.presenter.MainPresenter;

/**
 * Created by coder saigon on 22-Mar-16.
 */
public class SubMainOneActivity extends GenericMVPActivity<MVP_Main.RequireViewOps,
        MVP_Main.ProvidedPresenterOps,
        MainPresenter>
        implements
        MVP_Main.RequireViewOps {

    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onCreate(MainPresenter.class, this);

        Intent i = getIntent();
        int currentCount = i.getIntExtra("currentCount", 0);

        setContentView(R.layout.activity_submain);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "test", Snackbar.LENGTH_SHORT).setAction("action",null).show();
            }
        });

        tvCount = (TextView) findViewById(R.id.tvCount);
        tvCount.setText(""+currentCount);
    }

    @Override
    public void updateCurrentCount(int currentCount) {

    }
}
