package cn.bingoogolapple.weibo.engine;

import android.app.Activity;

import com.android.volley.VolleyError;

import cn.bingoogolapple.volley.GsonRespDelegate;
import cn.bingoogolapple.weibo.util.ToastUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/5 下午6:40
 * 描述:
 */
public abstract class BaseGsonRespDelegate<T> extends GsonRespDelegate<T> {

    public BaseGsonRespDelegate(Activity activity) {
        super(activity);
    }

    @Override
    protected void onJsonError(Exception e) {
        ToastUtils.show("解析JSON出错");
    }

    @Override
    protected void onNetError(VolleyError error) {
        ToastUtils.show("网络出错");
    }
}
