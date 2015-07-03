package cn.bingoogolapple.weibo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import cn.bingoogolapple.weibo.R;
import cn.bingoogolapple.weibo.model.Account;
import cn.bingoogolapple.weibo.util.Logger;
import cn.bingoogolapple.weibo.util.SPUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 上午1:25
 * 描述:
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (Account.isLogged()) {
                    if (mApp.getCurrentVersionName().equals(SPUtils.getLatestVersionName())) {
                        Logger.i(TAG, "已登录-没有新版本");
                        startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    } else {
                        Logger.i(TAG, "已登录-有新版本");
                        startActivity(new Intent(SplashActivity.this, NewFeatureActivity.class));
                    }
                } else {
                    Logger.i(TAG, "未登录");
                    startActivity(new Intent(SplashActivity.this, AuthActivity.class));
                }
                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
                finish();
            }
        }, 1500);
    }
}