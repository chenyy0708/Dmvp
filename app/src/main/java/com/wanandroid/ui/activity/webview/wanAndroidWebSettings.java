package com.wanandroid.ui.activity.webview;

import android.webkit.WebView;

import com.just.agentweb.AbsAgentWebSettings;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.IAgentWebSettings;

/**
 * @author :ChenYangYi
 * @time :2018/4/24
 * @desc :
 */
public class wanAndroidWebSettings extends AbsAgentWebSettings {
    @Override
    protected void bindAgentWebSupport(AgentWeb agentWeb) {

    }

    @Override
    public IAgentWebSettings toSetting(WebView webView) {
        super.toSetting(webView);
        getWebSettings().setUseWideViewPort(true);
        getWebSettings().setLoadWithOverviewMode(true);
        return this;
    }
}
