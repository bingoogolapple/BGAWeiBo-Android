package cn.bingoogolapple.weibo.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioGroup;

import cn.bingoogolapple.bgabanner.BGAViewPager;
import cn.bingoogolapple.weibo.R;
import cn.bingoogolapple.weibo.ui.fragment.DiscoverFragment;
import cn.bingoogolapple.weibo.ui.fragment.HomeFragment;
import cn.bingoogolapple.weibo.ui.fragment.MeFragment;
import cn.bingoogolapple.weibo.ui.fragment.MessageFragment;
import cn.bingoogolapple.weibo.util.ToastUtils;

public class MainActivity extends BaseActivity {
    private BGAViewPager mContentVp;
    private RadioGroup mTabRg;
    private ImageButton mPlusIb;

    private HomeFragment mHomeFragment;
    private MessageFragment mMessageFragment;
    private DiscoverFragment mDiscoverFragment;
    private MeFragment mMeFragment;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        mContentVp = getViewById(R.id.vp_main_content);
        mTabRg = getViewById(R.id.rg_main_tab);
        mPlusIb = getViewById(R.id.ib_main_plus);
    }

    @Override
    protected void setListener() {
        mPlusIb.setOnClickListener(this);
        mTabRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_main_home:
                        mContentVp.setCurrentItem(0, false);
                        break;
                    case R.id.rb_main_message:
                        mContentVp.setCurrentItem(1, false);
                        break;
                    case R.id.rb_main_discover:
                        mContentVp.setCurrentItem(2, false);
                        break;
                    case R.id.rb_main_me:
                        mContentVp.setCurrentItem(3, false);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mContentVp.setAllowUserScrollable(false);
        mContentVp.setAdapter(new ContentAdapter(getSupportFragmentManager()));
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ib_main_plus) {
            ToastUtils.show("点击了加号按钮");
        }
    }

    @Override
    public void onBackPressed() {
        mApp.exitWithDoubleClick();
    }

    private class ContentAdapter extends FragmentPagerAdapter {

        public ContentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    if (mHomeFragment == null) {
                        mHomeFragment = new HomeFragment();
                    }
                    return mHomeFragment;
                case 1:
                    if (mMessageFragment == null) {
                        mMessageFragment = new MessageFragment();
                    }
                    return mMessageFragment;
                case 2:
                    if (mDiscoverFragment == null) {
                        mDiscoverFragment = new DiscoverFragment();
                    }
                    return mDiscoverFragment;
                case 3:
                    if (mMeFragment == null) {
                        mMeFragment = new MeFragment();
                    }
                    return mMeFragment;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }

}