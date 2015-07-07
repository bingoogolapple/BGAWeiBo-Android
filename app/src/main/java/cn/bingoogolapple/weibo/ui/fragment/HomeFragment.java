package cn.bingoogolapple.weibo.ui.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.PopupWindow;

import java.util.List;

import cn.bingoogolapple.titlebar.BGATitlebar;
import cn.bingoogolapple.weibo.R;
import cn.bingoogolapple.weibo.engine.ApiClient;
import cn.bingoogolapple.weibo.engine.WeiBoRespDelegate;
import cn.bingoogolapple.weibo.model.Account;
import cn.bingoogolapple.weibo.model.HomeCategory;
import cn.bingoogolapple.weibo.model.User;
import cn.bingoogolapple.weibo.ui.pupupwindow.HomeCategoryPopupWindow;
import cn.bingoogolapple.weibo.util.ToastUtils;

/**
 * 作者:王浩 邮件:bingoogolapple@gmail.com
 * 创建时间:15/7/3 下午8:29
 * 描述:
 */
public class HomeFragment extends BaseFragment {
    private HomeCategoryPopupWindow mCategoryPw;
    private List<HomeCategory> mHomeCategorys;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_home);
        mTitlebar = getViewById(R.id.titlebar);
    }

    @Override
    protected void setListener() {
        mTitlebar.setDelegate(new BGATitlebar.BGATitlebarDelegate() {
            @Override
            public void onClickLeftCtv() {
                ToastUtils.show("点击了加关注");
            }

            @Override
            public void onClickTitleCtv() {
                showCategoryPw();
            }

            @Override
            public void onClickRightCtv() {
                ToastUtils.show("点击了雷达");
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        String name = Account.getInstance().username;
        if (!TextUtils.isEmpty(name)) {
            mTitlebar.setTitleText(name);
        }
    }

    @Override
    protected void onUserVisible() {
        loadUserInfo();
    }

    private void friendsTimeline() {

    }

    private void loadUserInfo() {
        ApiClient.loadUserInfo(new WeiBoRespDelegate<User>(null) {
            @Override
            protected void onSucess(User user) {
                mTitlebar.setTitleText(user.name);
                Account.getInstance().username = user.name;
                Account.getInstance().save();
            }
        });
    }

    private void showCategoryPw() {
        if (mCategoryPw == null) {
            mCategoryPw = new HomeCategoryPopupWindow(getActivity(), mTitlebar.getTitleCtv());
            mCategoryPw.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    mTitlebar.setTitleCtvChecked(false);
                }
            });
            mCategoryPw.setDelegate(new HomeCategoryPopupWindow.HomeCategoryPopupWindowDelegate() {
                @Override
                public void onSelectCategory(HomeCategory category) {
                    ToastUtils.show("选择了分类：" + category.title);
                }
            });
        }

        if (mHomeCategorys == null) {
            mHomeCategorys = HomeCategory.getTestDatas();
        }
        mCategoryPw.setCategorys(mHomeCategorys);
        mCategoryPw.show();
        mTitlebar.setTitleCtvChecked(true);
    }

}