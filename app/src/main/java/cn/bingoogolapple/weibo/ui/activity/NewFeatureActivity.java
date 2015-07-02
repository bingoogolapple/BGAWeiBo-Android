package cn.bingoogolapple.weibo.ui.activity;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;
import cn.bingoogolapple.weibo.R;
import cn.bingoogolapple.weibo.util.SPUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 上午1:25
 * 描述:
 */
public class NewFeatureActivity extends BaseActivity {

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_new_feature);
        BGABanner banner = (BGABanner)findViewById(R.id.banner);
        List<View> views = new ArrayList<>();
        views.add(getPageView(R.mipmap.new_feature_1));
        views.add(getPageView(R.mipmap.new_feature_2));
        views.add(getPageView(R.mipmap.new_feature_3));
        views.add(getPageView(R.mipmap.new_feature_4));
        banner.setViews(views);
    }

    private View getPageView(@DrawableRes int resid) {
        ImageView imageView = new ImageView(this);
        imageView.setImageResource(resid);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        return imageView;
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        SPUtils.setLatestVersionName();
    }

    @Override
    public void onBackPressed() {
        mApp.exitWithDoubleClick();
    }
}