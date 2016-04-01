package core.codersaigon.axe.service.core;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

/**
 * Created by coder saigon on 31-Mar-16.
 */
public class ImagePool {
    private static ImagePool mInstance;
    private static Context mContext;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;

    private ImagePool(Context mContext) {
        this.mContext = mContext;
        requestQueue = this.getRequestQueue();
        imageLoader = new ImageLoader(requestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static synchronized ImagePool getmInstance(Context mContext)
    {
        if(mInstance == null)
        {
            mInstance = new ImagePool(mContext);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(this.mContext.getApplicationContext());
        }
        return requestQueue;
    }

    public ImageLoader getImageLoader()
    {
        return imageLoader;
    }
}
