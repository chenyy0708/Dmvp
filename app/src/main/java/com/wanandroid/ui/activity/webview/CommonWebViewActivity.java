package com.wanandroid.ui.activity.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chen.common.base.BaseActivity;
import com.chen.common.di.component.AppComponent;
import com.just.agentweb.AgentWeb;
import com.wanandroid.chen.R;

import butterknife.BindView;

public class CommonWebViewActivity extends BaseActivity {
    @BindView(R.id.common_toolbar_title_tv)
    TextView commonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    public static final String URL = "web_Url";

    /**
     * h5地址
     */
    private String web_Url = "";
    /**
     * webview
     */
    private AgentWeb mAgentWeb;

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            commonToolbarTitleTv.setText(view.getTitle());
        }
    };

    /**
     * 打开WebView 带参数
     *
     * @param context 上下文
     * @param web_Url h5链接
     */
    public static void startAction(Context context, String web_Url) {
        Intent intent = new Intent();
        intent.setClass(context, CommonWebViewActivity.class);
        intent.putExtra(URL, web_Url);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_webview;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initToolbar(commonToolbar);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llContainer, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()
                .ready()
                .go(getIntent().getStringExtra(URL));

    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }

}
