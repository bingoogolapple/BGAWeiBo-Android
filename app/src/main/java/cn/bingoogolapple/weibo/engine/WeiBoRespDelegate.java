package cn.bingoogolapple.weibo.engine;

import android.app.Activity;

import com.android.volley.VolleyError;

import cn.bingoogolapple.volley.GsonRespDelegate;
import cn.bingoogolapple.weibo.R;
import cn.bingoogolapple.weibo.util.ToastUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/7 下午11:11
 * 描述:
 */
public abstract class WeiBoRespDelegate<T> extends GsonRespDelegate<T> {
    public WeiBoRespDelegate(Activity activity) {
        super(activity);
    }

    @Override
    protected void onJsonError(Exception e) {
        ToastUtils.show(R.string.server_error_tip);
    }

    @Override
    protected void onNetError(VolleyError error) {
        ToastUtils.show(R.string.network_error_tip);
    }
}
