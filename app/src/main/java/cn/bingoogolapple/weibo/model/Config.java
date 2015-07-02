package cn.bingoogolapple.weibo.model;

import com.google.gson.Gson;

import cn.bingoogolapple.weibo.util.AssetsUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 上午3:01
 * 描述:
 */
public class Config {
    private static final String FILE_NAME = "config.json";
    public String clientId;
    public String clientSecret;
    public String redirectUri;
    private static Config sInstance;

    // 这里设置成私有的gson也能实例化
    private Config() {
    }

    public static Config getInstance() {
        if (sInstance == null) {
            String configStr = AssetsUtils.getString(FILE_NAME);
            sInstance = new Gson().fromJson(configStr, Config.class);
        }
        return sInstance;
    }
}