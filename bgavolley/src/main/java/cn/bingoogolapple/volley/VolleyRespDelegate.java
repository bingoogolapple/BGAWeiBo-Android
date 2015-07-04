package cn.bingoogolapple.volley;

import android.app.Activity;
import android.app.ProgressDialog;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/2 10:14
 * 描述:
 */
public abstract class VolleyRespDelegate<T> implements Response.Listener<String> {
    private static final String TAG = VolleyRespDelegate.class.getSimpleName();
    private static boolean sIsDebug = false;
    private static String sLoadingMessage = "数据加载中，请稍候";
    protected ProgressDialog mLoadingDialog;
    private Activity mActivity;

    protected VolleyRespDelegate(Activity activity) {
        mActivity = activity;
        if (mActivity != null) {
            mLoadingDialog = new ProgressDialog(activity);
            mLoadingDialog.setCancelable(false);
            mLoadingDialog.setMessage(sLoadingMessage);
            mLoadingDialog.show();
        }
    }

    public static void setIsDebug(boolean isDebug) {
        sIsDebug = isDebug;
    }

    public static void setLoadingMessage(String loadingMessage) {
        sLoadingMessage = loadingMessage;
    }

    public Activity getActivity() {
        return mActivity;
    }

    @Override
    public void onResponse(String response) {
        if (sIsDebug && !TextUtils.isEmpty(response)) {
            Log.d(TAG, "response\n-------------------- START --------------------\n" + response + "\n-------------------- END --------------------");
        }
        onFinish();
        handleResponse(response);
    }

    protected void handleResponse(String response) {
        onSucess((T) response);
    }

    public Response.ErrorListener getErrorListener() {
        return new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                onFinish();
                onNetError(error);
            }
        };
    }

    public void onFinish() {
        if (mLoadingDialog != null && mLoadingDialog.isShowing()) {
            mLoadingDialog.dismiss();
        }
    }

    protected abstract void onSucess(T content);

    protected abstract void onNetError(VolleyError error);
}