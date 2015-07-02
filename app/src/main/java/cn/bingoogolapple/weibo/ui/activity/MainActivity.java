package cn.bingoogolapple.weibo.ui.activity;

import android.os.Bundle;

import cn.bingoogolapple.weibo.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onBackPressed() {
        mApp.exitWithDoubleClick();
    }
}