package cn.bingoogolapple.weibo.util;

import android.util.Log;

import cn.bingoogolapple.weibo.BuildConfig;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/2 10:21
 * 描述:
 */
public class Logger {
    public static final boolean IS_DEVELOP_MODE = BuildConfig.IS_DEVELOP_MODE;

    private Logger() {
    }

    // 日志记录级别，开发阶段根据需求设置成大于0的数，项目正式发布后设置成0
    private static int LOGLEVEL = 6;
    private static int ERROR = 1;
    private static int WARN = 2;
    private static int INFO = 3;
    private static int DEBUG = 4;
    private static int VERBOSE = 5;

    public static void v(String tag, String msg) {
        if (IS_DEVELOP_MODE && LOGLEVEL > VERBOSE && msg != null) {
            Log.v(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (IS_DEVELOP_MODE && LOGLEVEL > DEBUG && msg != null) {
            Log.d(tag, msg);
        }
    }

    public static void i(String tag, String msg) {
        if (IS_DEVELOP_MODE && LOGLEVEL > INFO && msg != null) {
            Log.i(tag, msg);
        }
    }

    public static void w(String tag, String msg) {
        if (IS_DEVELOP_MODE && LOGLEVEL > WARN && msg != null) {
            Log.w(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (IS_DEVELOP_MODE && LOGLEVEL > ERROR && msg != null) {
            Log.e(tag, msg);
        }
    }
    
}