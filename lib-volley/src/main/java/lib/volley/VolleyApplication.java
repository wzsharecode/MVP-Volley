package lib.volley;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleyApplication extends Application {

    public static int code;

    public static void setCode(int code) {
        VolleyApplication.code = code;
    }

    public static int getCode() {
        return code;
    }

    public static RequestQueue queues;

    public static RequestQueue getHttpQueues() {
        return queues;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        queues = Volley.newRequestQueue(getApplicationContext());

    }

}