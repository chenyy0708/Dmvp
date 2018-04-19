package com.wanandroid.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chen.common.base.BaseActivity;
import com.chen.common.di.component.AppComponent;
import com.jaeger.library.StatusBarUtil;
import com.wanandroid.chen.R;

import butterknife.BindView;
import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;

/**
 * @author :ChenYangYi
 * @time :2018/4/19
 * @desc : 主页面
 */
public class MainActivity extends BaseActivity {
    @BindView(R.id.common_toolbar_title_tv)
    TextView commonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;
    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.pnv_tab)
    PageNavigationView pnvTab;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    NavigationController mNavigationController;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mNavigationController = pnvTab.material()
                .addItem(R.drawable.icon_home_pager_selected, "首页")
                .addItem(R.drawable.icon_knowledge_hierarchy_selected, "知识体系")
                .addItem(R.drawable.icon_navigation_selected, "导航")
                .addItem(R.drawable.icon_project_selected, "项目")
                //这里可以设置样式模式，总共可以组合出4种效果
                .setMode(MaterialMode.HIDE_TEXT)
                .build();
        initToolbar(commonToolbar);
        initDrawLayout();
    }

    /**
     * 初始化DrawLayout侧边栏
     */
    private void initDrawLayout() {
        //创建返回键，并实现打开关/闭监听
        mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, commonToolbar, R.string.open, R.string.close);
        // 实现箭头和三条杠图案切换和抽屉拉合的同步
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setColorForDrawerLayout(this, drawerLayout, ContextCompat.getColor(this, R.color.colorPrimary), 122);
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
    }

    @Override
    protected void initToolbar(@NonNull Toolbar toolbar) {
        commonToolbarTitleTv.setText("首页");
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }
}
