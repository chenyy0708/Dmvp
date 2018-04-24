package com.wanandroid.ui.activity.webview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chen.common.base.BaseActivity;
import com.chen.common.di.component.AppComponent;
import com.just.agentweb.AgentWeb;
import com.wanandroid.chen.R;

import butterknife.BindView;

/**
 * @author ChenYangYi
 * @date 2018/4/23
 */
public class CommonWebViewActivity extends BaseActivity {
    @BindView(R.id.common_toolbar_title_tv)
    TextView commonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.ll_container)
    LinearLayout llContainer;

    /**
     * url
     */
    public static final String URL = "web_Url";
    /**
     * 标题
     */
    public static final String TITLE = "title";

    /**
     * webView
     */
    private AgentWeb mAgentWeb;

    /**
     * 监听网页加载进度
     */
    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if (commonToolbarTitleTv != null) {
                commonToolbarTitleTv.setText(view.getTitle());
            }
        }
    };

    /**
     * 打开WebView 带参数
     *
     * @param context 上下文
     * @param webUrl  h5链接
     */
    public static void startAction(Context context, String webUrl, String title) {
        Intent intent = new Intent();
        intent.setClass(context, CommonWebViewActivity.class);
        intent.putExtra(URL, webUrl);
        intent.putExtra(TITLE, title);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_common_webview;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        initToolbar(commonToolbar);
        commonToolbarTitleTv.setText(getIntent().getStringExtra(TITLE));
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llContainer, new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT))
                // 进度条颜色
                .useDefaultIndicator(ContextCompat.getColor(mContext, R.color.blue_dark_btn))
                .setWebViewClient(mWebViewClient)
                .setAgentWebWebSettings(new wanAndroidWebSettings())
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

    /**
     * 返回上一页
     *
     * @param keyCode keyCode
     * @param event   event
     * @return return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mAgentWeb.handleKeyEvent(keyCode, event) || super.onKeyDown(keyCode, event);
    }

}
