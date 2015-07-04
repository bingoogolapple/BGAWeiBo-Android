package cn.bingoogolapple.volley;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/2 10:14
 * 描述:
 */
public class BGAVolley {

    private static RequestQueue sRequestQueue;

    private BGAVolley() {
    }

    public static void init(Context context) {
        sRequestQueue = Volley.newRequestQueue(context);
    }

    public static RequestQueue getRequestQueue() {
        if (sRequestQueue != null) {
            return sRequestQueue;
        } else {
            throw new IllegalStateException("RequestQueue not initialized");
        }
    }

    public static void addRequest(Object tag, Request<?> request) {
        if (tag != null) {
            request.setTag(tag);
        }
        sRequestQueue.add(request);
    }

    public static void cancelAll(Object tag) {
        sRequestQueue.cancelAll(tag);
    }

    public static void post(String url, final Map<String, String> params, VolleyRespDelegate responseListener) {
        addRequest(responseListener.getActivity(), new StringRequest(Request.Method.POST, url, responseListener, responseListener.getErrorListener()) {
            protected Map<String, String> getParams() {
                return params;
            }
        });
    }

    public static void get(String url, VolleyRespDelegate responseListener) {
        addRequest(responseListener.getActivity(), new StringRequest(Request.Method.GET, url, responseListener, responseListener.getErrorListener()));
    }
}