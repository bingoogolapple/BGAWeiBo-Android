package cn.bingoogolapple.volley;

import android.app.Activity;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/2 10:15
 * 描述:
 */
public abstract class GsonRespDelegate<T> extends JsonRespDelegate<T> {

    public GsonRespDelegate(Activity activity) {
        super(activity);
    }

    @Override
    protected void handleResponse(String response) {
        try {
            onSucess(sGson.fromJson(response, getTClass()));
        } catch (Exception e) {
            onJsonError(e);
        }
    }
}