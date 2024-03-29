package com.example.uploadimagetoserver;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by sanat on 4/29/2018.
 */

public class MySingleton {

    private static MySingleton mySingleton;
    private static Context mCtx;
    private RequestQueue requestQueue;

    private MySingleton(Context context)
    {
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    private RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }


    public static synchronized MySingleton getmySingleton(Context context)
    {
        if (mySingleton == null)
        {
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }

    public<T> void addToRequestque(Request<T> request)
    {
        getRequestQueue().add(request);
    }

}








