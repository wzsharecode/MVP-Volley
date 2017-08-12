package lib.volley.base;

import android.app.Activity;

import lib.volley.R;
import lib.volley.http.INetResultInteract;
import lib.volley.util.MessageUtils;
import lib.volley.view.ProgressHUD;


public class BaseActivity extends Activity implements INetResultInteract {
    public static final String TAG = "MyLog BaseActivity";

    private ProgressHUD mProgressHUD;

    @Override
    public void onRequestSuccess(int requestCode) {
    	
    }

    @Override
    public void onRequestFaild(String errorNo, String errorMessage) {
        showProgress(false);
        MessageUtils.showShortToast(this, errorMessage);
    }

    @Override
    public void onNoConnect() {
        showProgress(false);
        MessageUtils.showShortToast(this, getString(R.string.loading));
    }

    public void showProgress(boolean show) {
        showProgressWithText(show, getString(R.string.loading));
    }

    public void showProgressWithText(boolean show, String message) {
        if (show) {
            mProgressHUD = ProgressHUD.show(this, message, true, null);
        } else {
            if (mProgressHUD != null) {
                mProgressHUD.dismiss();
            }
        }
    }
}
