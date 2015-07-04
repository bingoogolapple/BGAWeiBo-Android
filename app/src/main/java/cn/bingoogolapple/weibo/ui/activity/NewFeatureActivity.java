package cn.bingoogolapple.weibo.ui.activity;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.weibo.R;
import cn.bingoogolapple.weibo.util.SPUtils;
import cn.bingoogolapple.weibo.util.ToastUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 上午1:25
 * 描述:
 */
public class NewFeatureActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_feature);

        BGABanner banner = (BGABanner) findViewById(R.id.banner);

        List<View> views = new ArrayList<>();
        views.add(getPageView(R.mipmap.new_feature_1));
        views.add(getPageView(R.mipmap.new_feature_2));
        views.add(getPageView(R.mipmap.new_feature_3));

        View lastPageView = View.inflate(this, R.layout.view_new_feature_last, null);
        lastPageView.findViewById(R.id.btn_new_feature_start).setOnClickListener(this);
        views.add(lastPageView);

        banner.setViews(views);
    }

    private View getPageView(@DrawableRes int resid) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_new_feature_start) {
            SPUtils.setLatestVersionName();
            CheckBox shareCb = getViewById(R.id.cb_new_feature_share);
            if (shareCb.isChecked()) {
                ToastUtils.show("勾选了分享给大家");
            }
            forwardAndFinish(MainActivity.class);
        }
    }

    @Override
    public void onBackPressed() {
        mApp.exitWithDoubleClick();
    }
}