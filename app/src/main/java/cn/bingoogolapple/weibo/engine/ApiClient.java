package cn.bingoogolapple.weibo.engine;

import cn.bingoogolapple.volley.ApiParams;
import cn.bingoogolapple.volley.BGAVolley;
import cn.bingoogolapple.volley.VolleyRespDelegate;
import cn.bingoogolapple.weibo.model.Config;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 上午12:32
 * 描述:
 */
public class ApiClient {

    public static void accessTokenWithCode(String code, VolleyRespDelegate delegate) {
        Config config = Config.getInstance();
        ApiParams params = new ApiParams();
        params.with("client_id", config.clientId);
        params.with("client_secret", config.clientSecret);
        params.with("grant_type", "authorization_code");
        params.with("redirect_uri", config.redirectUri);
        params.with("code", code);
        BGAVolley.post("https://api.weibo.com/oauth2/access_token", params, delegate);
    }

}