package macpro.mvp_volley.presenter;

import android.content.Context;

import lib.volley.base.BaseRequest;
import lib.volley.http.INetResultInteract;

/**
 * Created by macpro on 2017/8/12.
 */

public abstract class BasePresenter extends BaseRequest {

    String url = "http://ip.taobao.com/service/getIpInfo.php?ip=202.96.128.166";


    public BasePresenter(INetResultInteract activity, Context context) {
        super(activity, context);
    }
}
