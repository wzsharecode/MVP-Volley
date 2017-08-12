package macpro.mvp_volley.presenter;

import android.content.Context;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;

import lib.volley.http.HttpRequestCode;
import lib.volley.http.INetResultInteract;

public class UserPost extends BasePresenter {

    private String result;

    public String getResult() {
        return result;
    }

    public UserPost(INetResultInteract inetresult, Context context) {
        super(inetresult, context);
    }

    public void getUser(int requestCode) {

        getRequest(url, null, requestCode);
    }

    public void postUser(int requestCode) {

        postRequest(url, null, requestCode);
    }

    @Override
    public void onRequestSuccess(JSONObject result, int requestCode) throws IOException {
        Log.v(TAG, requestCode + "--onRequestSuccess---" + result);

        switch (requestCode) {
            case HttpRequestCode.CODE_ZERO:
                this.result = result.toString() + "--onRequestSuccess---" + 0;
                break;
            case HttpRequestCode.CODE_ONE:
                this.result = result.toString() + "--onRequestSuccess---" + 1;
                break;
            default:
                break;
        }
    }
}
