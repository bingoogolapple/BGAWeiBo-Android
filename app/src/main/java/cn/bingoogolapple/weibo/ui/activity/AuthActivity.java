package cn.bingoogolapple.weibo.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.android.volley.VolleyError;

import cn.bingoogolapple.volley.ApiParams;
import cn.bingoogolapple.volley.GsonRespDelegate;
import cn.bingoogolapple.volley.RequestManager;
import cn.bingoogolapple.weibo.R;
import cn.bingoogolapple.weibo.model.Account;
import cn.bingoogolapple.weibo.model.Config;
import cn.bingoogolapple.weibo.util.Logger;
import cn.bingoogolapple.weibo.util.SPUtils;
import cn.bingoogolapple.weibo.util.ToastUtils;

public class AuthActivity extends BaseActivity {
    private WebView mContentWv;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_auth);
        mContentWv = getViewById(R.id.wv_auth_content);
    }

    @Override
    protected void setListener() {
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {
        mContentWv.getSettings().setJavaScriptEnabled(true);
        mContentWv.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                showLoadingDialog(R.string.loading_data_tip);
                //  http://www.bingoogolapple.cn/?code=xxxxxxxxx
                Logger.i(TAG, "url = " + url);
                String codeFlag = "code=";
                if (url.contains(codeFlag)) {
                    view.stopLoading();
                    String code = url.substring(url.indexOf(codeFlag) + codeFlag.length());
                    accessTokenWithCode(code);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                dismissLoadingDialog();
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                dismissLoadingDialog();
                ToastUtils.show(R.string.network_error_tip);
            }
        });
        // 回调地址默认是http://
        Config config = Config.getInstance();
        String urlStr = "https://api.weibo.com/oauth2/authorize?client_id=" + config.clientId + "&redirect_uri=" + config.redirectUri;
        mContentWv.loadUrl(urlStr);
    }

    private void accessTokenWithCode(String code) {
        Logger.i(TAG, "code = " + code);
        Config config = Config.getInstance();
        ApiParams params = new ApiParams();
        params.with("client_id", config.clientId);
        params.with("client_secret", config.clientSecret);
        params.with("grant_type", "authorization_code");
        params.with("redirect_uri", config.redirectUri);
        params.with("code", code);
        RequestManager.post("https://api.weibo.com/oauth2/access_token", params, new GsonRespDelegate<Account>(this) {
            @Override
            protected void onJsonError(Exception e) {
                ToastUtils.show("解析JSON出错");
            }

            @Override
            protected void onSucess(Account account) {
                account.save();

                if (mApp.getCurrentVersionName().equals(SPUtils.getLatestVersionName())) {
                    startActivity(new Intent(AuthActivity.this, MainActivity.class));
                } else {
                    startActivity(new Intent(AuthActivity.this, NewFeatureActivity.class));
                }
                overridePendingTransition(R.anim.tran_next_in, R.anim.tran_next_out);
                finish();
            }

            @Override
            protected void onNetError(VolleyError error) {
                ToastUtils.show("网络出错");
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (mContentWv.canGoBack()) {
            mContentWv.goBack();
        } else {
            mApp.exitWithDoubleClick();
        }
    }
}