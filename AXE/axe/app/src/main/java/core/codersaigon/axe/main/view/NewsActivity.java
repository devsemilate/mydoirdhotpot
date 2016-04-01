package core.codersaigon.axe.main.view;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import core.codersaigon.axe.R;
import core.codersaigon.axe.main.model.News;
import core.codersaigon.axe.main.presenter.navigator.OpenNewsDetailNavigator;
import core.codersaigon.axe.service.main.FilterWebContentFactory;

/**
 * Created by coder saigon on 29-Mar-16.
 */
public class NewsActivity extends AppCompatActivity{
    News news;
    WebView wvNewsDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getIntent().getExtras();
        if(bundle != null) {
            try {
                news = bundle.getParcelable(OpenNewsDetailNavigator.EXTRA);
                Log.d("test", "news link : " + news.getLink());
            } catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }

        setContentView(R.layout.activity_news);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        wvNewsDetail = (WebView)findViewById(R.id.wvNewsDetail);
        wvNewsDetail.setHorizontalScrollBarEnabled(false);
        wvNewsDetail.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvNewsDetail.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return (event.getAction() == MotionEvent.ACTION_MOVE);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            this.onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Utf8StringRequest test = new Utf8StringRequest(StringRequest.Method.GET, news.getLink(),
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        filterWebContent(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("test", "get weblink error : " + error);
                    }
                }
        );
        Volley.newRequestQueue(this.getApplicationContext()).add(test);

    }

    private void filterWebContent(String response)
    {
        AsyncTask<String, String, String> filterAsynTask = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                String response = params[0];
                String content = "...";
                Pattern pattern = Pattern.compile(FilterWebContentFactory.getFilterWebContentRepo().getFilter(news.getLink()).getPatternCompile(), Pattern.DOTALL);
                Matcher matcher = pattern.matcher(response);
                if(matcher.find())
                {
                    content = matcher.group(0);
                    Log.d("test", "content  :  " + content);
                }
                return content;
            }

            @Override
            protected void onPostExecute(String content) {
                wvNewsDetail.loadData(content, "text/html; charset=UTF-8", null);
            }
        };
        filterAsynTask.execute(response);
    }

    private static class Utf8StringRequest extends StringRequest
    {
        public Utf8StringRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {
            String parsed;
            try {
                parsed = new String(response.data, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                parsed = new String(response.data);
            }
            return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
        }
    }
}
