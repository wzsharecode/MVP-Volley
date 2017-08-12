package macpro.mvp_volley.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import lib.volley.base.BaseActivity;
import lib.volley.http.HttpRequestCode;
import macpro.mvp_volley.R;
import macpro.mvp_volley.presenter.UserPost;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mBtnGet;
    private TextView mTvResult;
    private Button   mBtnPost;
    private UserPost mUser;
    @Override
    public void onRequestSuccess(int requestCode) {
        super.onRequestSuccess(requestCode);
        Log.v(TAG, requestCode + "--start refresh UI ---" + requestCode);
        showProgress(false);
        switch (requestCode) {
            case HttpRequestCode.CODE_ZERO:
                mTvResult.setText(mUser.getResult());
                break;
            case HttpRequestCode.CODE_ONE:
                mTvResult.setText(mUser.getResult());
                break;
            default:
                break;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mBtnGet = findViewById(R.id.btn_get);
        mTvResult = findViewById(R.id.tv_result);
        mBtnPost = findViewById(R.id.btn_post);

        mUser = new UserPost(this,this);

        mBtnGet.setOnClickListener(this);
        mBtnPost.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        showProgress(true);

        switch (v.getId()) {
            case R.id.btn_get:
                mUser.getUser(HttpRequestCode.CODE_ZERO);
                break;
            case R.id.btn_post:
                mUser.postUser(HttpRequestCode.CODE_ONE);
                break;
        }
    }
}
