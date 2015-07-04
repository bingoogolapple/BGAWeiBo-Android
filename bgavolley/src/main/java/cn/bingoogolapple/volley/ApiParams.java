package cn.bingoogolapple.volley;

import java.util.HashMap;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/2 10:30
 * 描述:
 */
public class ApiParams extends HashMap<String, String> {

    public ApiParams with(String key, String value) {
        put(key, value);
        return this;
    }

}