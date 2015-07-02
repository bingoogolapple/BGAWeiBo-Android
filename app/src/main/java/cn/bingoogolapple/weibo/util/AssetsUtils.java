package cn.bingoogolapple.weibo.util;

import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import cn.bingoogolapple.weibo.App;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 上午2:58
 * 描述:
 */
public class AssetsUtils {

    private AssetsUtils() {
    }

    public static String getString(String fileName) {
        if (TextUtils.isEmpty(fileName)) {
            return null;
        }

        StringBuilder sb = new StringBuilder("");
        try {
            InputStreamReader in = new InputStreamReader(App.getInstance().getAssets().open(fileName));
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}