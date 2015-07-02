package cn.bingoogolapple.weibo.model;

import cn.bingoogolapple.weibo.util.SPUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 上午4:21
 * 描述:
 */
public class Account {
    private static Account sInstance;
    public String access_token;
    public String uid;
    public long expires_in;
    public long created_time;
    public String username;

    private Account() {
    }

    public void save() {
        sInstance = this;
        created_time = System.currentTimeMillis();
        SPUtils.putString("access_token", access_token);
        SPUtils.putString("uid", uid);
        SPUtils.putLong("expires_in", expires_in);
        SPUtils.putLong("created_time", created_time);
        SPUtils.putString("username", username);
    }

    public static Account getInstance() {
        if (sInstance == null) {
            sInstance = new Account();
            sInstance.access_token = SPUtils.getString("access_token");
            sInstance.uid = SPUtils.getString("uid");
            sInstance.expires_in = SPUtils.getLong("expires_in");
            sInstance.created_time = SPUtils.getLong("created_time");
            sInstance.username = SPUtils.getString("username");
        }
        return sInstance;
    }

    public static boolean isLogged() {
        return System.currentTimeMillis() < getInstance().created_time + getInstance().expires_in;
    }
}