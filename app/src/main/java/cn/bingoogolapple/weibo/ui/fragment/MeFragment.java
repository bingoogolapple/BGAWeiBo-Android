package cn.bingoogolapple.weibo.ui.fragment;

import android.os.Bundle;

import cn.bingoogolapple.titlebar.BGATitlebar;
import cn.bingoogolapple.weibo.R;
import cn.bingoogolapple.weibo.util.Logger;
import cn.bingoogolapple.weibo.util.ToastUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 下午8:29
 * 描述:
 */
public class MeFragment extends BaseFragment {
    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_me);
        mTitlebar = getViewById(R.id.titlebar);
    }

    @Override
    protected void setListener() {
        mTitlebar.setDelegate(new BGATitlebar.BGATitlebarDelegate() {
            @Override
            public void onClickRightBtn() {
                ToastUtils.show("点击了设置");
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        Logger.i(TAG, "processLogic");
    }

    @Override
    protected void onUserVisible() {

    }
}
