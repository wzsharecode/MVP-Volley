package lib.volley.base;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import lib.volley.VolleyApplication;
import lib.volley.http.HttpRequestCode;
import lib.volley.http.INetResultInteract;

public abstract class BaseRequest {

    public static final String TAG = "MyLog BaseRequest";

    protected INetResultInteract mResult;
    private   Context mContext;
    protected Gson mGson;

    public BaseRequest(INetResultInteract iNetResultInteract, Context context) {

        this.mResult = iNetResultInteract;
        this.mContext = context;
        this.mGson = new Gson();
    }
    
    public abstract void onRequestSuccess(JSONObject result, int requestCode) throws IOException;


    protected void getRequest(String url, Map<String, String> params, int requestCode) {
        getRequestData(url, params, requestCode);
    }


    protected void getRequestForCode(String url, Map<String, String> params, int requestCode) {
        getRequestData(url, params, requestCode);
    }

    protected void getRequestData(String url, Map<String, String> params, final int requestCode) {
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject result) {
                try {
                    if (HttpRequestCode.CODE_RESULT == result.getInt("code")) {
                        if (TextUtils.isEmpty(result.getString("data"))) {
                            try {
                                onRequestSuccess(result, requestCode);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        mResult.onRequestSuccess(requestCode);
                    } else {
                        mResult.onRequestFaild("", "");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mResult.onRequestFaild("", error.getMessage());
            }
        }
        );
        request.setTag("volley_getjson");
        VolleyApplication.getHttpQueues().add(request);
    }

    public void postRequest(String url, JSONObject jsonParams, final int requestCode) {
        Log.v(TAG, requestCode + "-post----" + url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonParams, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject result) {
                Log.v(TAG, requestCode + "-post----" + result);
                try {
                    if (HttpRequestCode.CODE_RESULT == result.getInt("code")) {
                        if (TextUtils.isEmpty(result.getString("data"))) {
                            try {
                                onRequestSuccess(result, requestCode);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        mResult.onRequestSuccess(requestCode);
                    } else {
                        mResult.onRequestFaild("", "");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v(TAG, requestCode + "" + error);
                mResult.onRequestFaild("", error.getMessage());
            }
        }
        ) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<String, String>();
                headers.put("Accept", "application/json");
                headers.put("Content-Type", "application/json; charset=UTF-8");
                return headers;
            }
        };
        request.setTag("volley_postjson");
        VolleyApplication.getHttpQueues().add(request);
    }

}
