package cn.bingoogolapple.volley;

import android.app.Activity;

import org.json.JSONObject;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/2 10:20
 * 描述:
 */
public abstract class ApiRespDelegate<T> extends JsonRespDelegate<T> {
    /*
    {
        "error_code": 0,
        "error_description": "successful",
        "content": {
            "property1": "xxxxx",
            "property2": "xxxxx"
        }
    }
    */
    /**
     * 返回错误码的键
     */
    private static String sErrorCodeKeyName = "error_code";
    /**
     * 返回错误消息的键
     */
    private static String sErrorDescriptionKeyName = "error_description";
    /**
     * 返回数据的内容数据的键
     */
    private static String sContentKeyName = "content";
    /**
     * 需要跳转到登录界面的结果码
     */
    private static int sJumpToLoginCode = -1;
    /**
     * 请求数据成功的结果码
     */
    private static int sSuccessCode = 0;

    public ApiRespDelegate(Activity activity) {
        super(activity);
    }

    public static void init(String errorCodeKeyName, String errorDescriptionKeyName, String contentKeyName, int jumpToLoginCode, int successCode) {
        sErrorCodeKeyName = errorCodeKeyName;
        sErrorDescriptionKeyName = errorDescriptionKeyName;
        sContentKeyName = contentKeyName;
        sJumpToLoginCode = jumpToLoginCode;
        sSuccessCode = successCode;
    }

    @Override
    protected void handleResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            int resultCode = jsonObject.getInt(sErrorCodeKeyName);
            if (resultCode == sJumpToLoginCode) {
                jumpToLogin();
            } else if (resultCode == sSuccessCode) {
                onSucess(sGson.fromJson(jsonObject.getString(sContentKeyName), getTClass()));
            } else {
                onFailure(resultCode, jsonObject.getString(sErrorDescriptionKeyName));
            }
        } catch (Exception e) {
            onJsonError(e);
        }
    }

    protected abstract void jumpToLogin();

    protected abstract void onFailure(int errorCode, String errorDescription);

}