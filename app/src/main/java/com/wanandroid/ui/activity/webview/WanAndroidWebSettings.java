package com.wanandroid.ui.activity.webview;

import android.webkit.WebSettings;
import android.webkit.WebView;

import com.just.agentweb.AbsAgentWebSettings;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.IAgentWebSettings;

/**
 * @author :ChenYangYi
 * @date :2018/4/24
 * @description : 通用WebView设置
 */
public class WanAndroidWebSettings extends AbsAgentWebSettings {
    @Override
    protected void bindAgentWebSupport(AgentWeb agentWeb) {

    }

    @Override
    public IAgentWebSettings toSetting(WebView webView) {
        super.toSetting(webView);
        getWebSettings().setSupportZoom(true);
        getWebSettings().setBuiltInZoomControls(true);
        //不显示缩放按钮
        getWebSettings().setDisplayZoomControls(false);
        //设置自适应屏幕，两者合用
        //将图片调整到适合WebView的大小
        getWebSettings().setUseWideViewPort(true);
        //缩放至屏幕的大小
        getWebSettings().setLoadWithOverviewMode(true);
        //自适应屏幕
        getWebSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        return this;
    }
}
