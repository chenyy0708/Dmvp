package com.chen.common.base;


import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import com.chen.common.app.AppManager;
import com.chen.common.app.IApp;
import com.chen.common.di.component.AppComponent;
import com.chen.common.rx.RxManager;
import com.chen.common.utils.CUtils;
import com.chen.common.utils.ToastUitl;
import com.chen.common.widget.LoadingDialog;
import com.jaeger.library.StatusBarUtil;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Activity基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity {
    /**
     * 注入得到presenter的实例对象，需要setupActivityComponent注入，否则P为null
     */
    @Inject
    public T mPresenter;
    /**
     * RxJava2内存泄露管理类，需要注入得到
     */
    @Inject
    public RxManager mRxManager;
    public Context mContext;
    private Unbinder unbinder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        doBeforeSetcontentView();
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mContext = this;
        // dagger2注入
        setupActivityComponent(CUtils.obtainAppComponentFromContext(mContext));
        this.initData(savedInstanceState);
    }

    /**
     * 设置layout前配置
     */
    private void doBeforeSetcontentView() {
        // 把actvity放到application栈中管理
        AppManager.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 沉浸式状态栏
        setStatusBar();
    }

    /**
     * 默认Application中定义的沉浸式颜色
     */
    private void setStatusBar() {
        setStatusBar(ContextCompat.getColor(this, ((IApp) getApplicationContext()).getAppMainColor()));
    }

    /**
     * 设置状态栏颜色
     */
    private void setStatusBar(@ColorInt int color) {
        StatusBarUtil.setColor(this, color, 122);
    }

    /*********************子类实现*****************************/
    /**
     * 获取布局文件
     * @return 布局文件
     */
    public abstract int getLayoutId();

    /**
     * 初始化view
     * @param savedInstanceState Bundle
     */
    public abstract void initData(Bundle savedInstanceState);

    /**
     * 提供 AppComponent 对象给 BaseActivity 的子类, 用于 Dagger2 的依赖注入
     * @param appComponent 全局Appcomponet
     */
    public abstract void setupActivityComponent(AppComponent appComponent);

    /**
     * 通过Class跳转界面
     **/
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivityForResult(Class<?> cls, Bundle bundle,
                                       int requestCode) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * 含有Bundle通过Class跳转界面
     **/
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 带图片的toast
     *
     * @param text
     * @param res
     */
    public void showToastWithImg(String text, int res) {
        ToastUitl.showToastWithImg(text, res);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        if (unbinder != null && unbinder != Unbinder.EMPTY)
            unbinder.unbind();
        this.unbinder = null;
        if (mRxManager != null)
            mRxManager.clear();
        AppManager.getAppManager().finishActivity(this);
    }
}
