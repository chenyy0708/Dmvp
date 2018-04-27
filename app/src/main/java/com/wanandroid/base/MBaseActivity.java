package com.wanandroid.base;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chen.common.app.BaseApplication;
import com.chen.common.base.BaseActivity;
import com.chen.common.base.BasePresenter;
import com.chen.common.base.BaseView;
import com.chen.common.utils.NetWorkUtils;
import com.wanandroid.chen.R;
import com.wanandroid.utils.SnackbarUtils;
import com.wanandroid.widget.LoadingLayout;

/**
 * @author :ChenYangYi
 * @date :2018/04/26/10:26
 * @description : 封装一些进度条到activity中，便于使用
 * @github :https://github.com/chenyy0708
 */
public abstract class MBaseActivity<T extends BasePresenter> extends BaseActivity<T> implements LoadingLayout.OnReloadListener, BaseView {

    private Toolbar mToolbar;
    private TextView mTitleTv;
    private LoadingLayout mLoadingLayout;
    private FrameLayout flBaseContent;

    @Override
    public void doBeforeSetcontentView() {
        super.doBeforeSetcontentView();
        getDelegate().setContentView(R.layout.activity_base_content);
        initFindViewById();
    }

    private void initFindViewById() {
        mToolbar = (Toolbar) findViewById(R.id.common_toolbar);
        mTitleTv = (TextView) findViewById(R.id.common_toolbar_title_tv);
        mLoadingLayout = (LoadingLayout) findViewById(R.id.loading_base);
        flBaseContent = (FrameLayout) findViewById(R.id.fl_base_content);
        mLoadingLayout.setOnReloadListener(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        flBaseContent.removeAllViews();
        getLayoutInflater().inflate(layoutResID, flBaseContent, true);
    }

    /**
     * 使用基类同一标题，暂时只有返回键和标题栏（后期扩展）
     *
     * @param title 标题
     */
    public void showTitle(String title) {
        mToolbar.setVisibility(View.VISIBLE);
        initToolbar(mToolbar);
        setTitle(title);
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        mTitleTv.setText(title);
    }

    /**
     * 多状态布局
     *
     * @return
     */
    public LoadingLayout getmLoadingLayout() {
        return mLoadingLayout;
    }

    /**
     * 设置子类activity的多状态布局，替换base，子类检查网络
     *
     * @return
     */
    public void setLoadingLayout(LoadingLayout loadingLayout) {
        this.mLoadingLayout = loadingLayout;
        mLoadingLayout.setOnReloadListener(this);
        showSuccess();
    }

    /**
     * 重新加载按钮
     *
     * @param v
     */
    @Override
    public void onReload(View v) {
        if (NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) { // 网络已经链接
            showSuccess();
            reLoadData();
        } else {
            showShortToast(getString(R.string.error_please_check_network));
        }
    }

    /**
     * 用于子页面重新加载数据
     */
    protected void reLoadData() {
    }

    /**
     * 数据加载中
     */
//    @Override
    public void showLoading() {
        mLoadingLayout.setStatus(LoadingLayout.Loading);
    }

    /**
     * 数据加载完成
     */
//    @Override
    public void stopLoading() {
        showSuccess();
    }

    @Override
    public void showErrorTip(String msg) {
        SnackbarUtils.showSnackMessage(this, msg);
    }

    /**
     * 数据加载成功
     */
    public void showSuccess() {
        mLoadingLayout.setStatus(LoadingLayout.Success);
    }

    /**
     * 数据为空
     */
    public void showEmpty() {
        mLoadingLayout.setStatus(LoadingLayout.Empty);
    }


    public TextView getmTitleTv() {
        return mTitleTv;
    }

}
