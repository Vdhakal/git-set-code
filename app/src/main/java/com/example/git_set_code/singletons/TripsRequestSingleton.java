package com.example.git_set_code.singletons;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 *
 */
public class TripsRequestSingleton {
    private static TripsRequestSingleton instance;
    private RequestQueue requestQueue;
    private static Context ctx;

    /**
     * @param context
     */
    private TripsRequestSingleton(Context context) {
        ctx = context;
        requestQueue = getRequestQueue();
    }

    /**
     * @param context
     * @return
     */
    public static synchronized TripsRequestSingleton getInstance(Context context) {
        if (instance == null) {
            instance = new TripsRequestSingleton(context);
        }
        return instance;
    }

    /**
     * @return
     */
    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            requestQueue = Volley.newRequestQueue(ctx.getApplicationContext());
        }
        return requestQueue;
    }

    /**
     * @param req
     * @param <T>
     */
    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }
}
