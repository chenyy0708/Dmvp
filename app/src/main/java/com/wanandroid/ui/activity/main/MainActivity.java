package com.wanandroid.ui.activity.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.chen.common.base.BaseActivity;
import com.chen.common.di.component.AppComponent;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.jaeger.library.StatusBarUtil;
import com.wanandroid.chen.R;
import com.wanandroid.glide.GlideUtil;
import com.wanandroid.ui.fragment.ArticleFragment;
import com.wanandroid.ui.fragment.IFragment;
import com.wanandroid.ui.fragment.main.CollectFragment;
import com.wanandroid.ui.fragment.main.NavigationFragment;
import com.wanandroid.utils.SnackbarUtils;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.majiajie.pagerbottomtabstrip.MaterialMode;
import me.majiajie.pagerbottomtabstrip.NavigationController;
import me.majiajie.pagerbottomtabstrip.PageNavigationView;
import me.majiajie.pagerbottomtabstrip.listener.OnTabItemSelectedListener;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * @author :ChenYangYi
 * @date :2018/4/19
 * @description : 主页面
 */
public class MainActivity extends BaseActivity implements ISupportActivity, NavigationView.OnNavigationItemSelectedListener {
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
    @BindView(R.id.main_floating_action_btn)
    FloatingActionButton mainFloatingActionBtn;
    @BindView(R.id.fl_bottom_tab)
    FrameLayout flBottomTab;
    /**
     * 不需要继承SupportActivity，用于处理Fragment内存回收各种情况
     */
    final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);
    private SupportFragment[] mFragments = new SupportFragment[6];
    public static final int FIRST = 0;
    public static final int SECOND = 1;
    public static final int THIRD = 2;
    public static final int FOUR = 3;
    public static final int FIVE = 4;
    public static final int SIX = 5;
    private String[] mTitles = {"首页", "知识体系", "导航", "项目", "收藏", "设置"};
    private Integer[] mIcons = {R.drawable.icon_home_pager_selected
            , R.drawable.icon_knowledge_hierarchy_selected
            , R.drawable.icon_navigation_selected
            , R.drawable.icon_project_selected};
    private KenBurnsView kenBurnsView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        mNavigationController = pnvTab.material()
                .addItem(mIcons[0], mTitles[0])
                .addItem(mIcons[1], mTitles[1])
                .addItem(mIcons[2], mTitles[2])
                .addItem(mIcons[3], mTitles[3])
                //这里可以设置样式模式，总共可以组合出4种效果
                .setMode(MaterialMode.HIDE_TEXT)
                .build();
        initToolbar(commonToolbar);
        initDrawLayout();
        initNavigationTab();

    }

    /**
     * 监听导航tab
     */
    private void initNavigationTab() {
        mNavigationController.addTabItemSelectedListener(new OnTabItemSelectedListener() {
            @Override
            public void onSelected(int index, int old) {
                showHideFragment(mFragments[index]);
                commonToolbarTitleTv.setText(mTitles[index]);
            }

            @Override
            public void onRepeat(int index) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (kenBurnsView != null) {
            kenBurnsView.resume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (kenBurnsView != null) {
            kenBurnsView.pause();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (kenBurnsView != null) {
            kenBurnsView.restart();
        }
    }

    /**
     * 初始化DrawLayout侧边栏
     */
    private void initDrawLayout() {
        kenBurnsView = navView.getHeaderView(0).findViewById(R.id.header_kbv);
        navView.setNavigationItemSelectedListener(this);
        CircleImageView userCiv = navView.getHeaderView(0).findViewById(R.id.header_civ);
        GlideUtil.loadUrl(this, "http://img3.imgtn.bdimg.com/it/u=1791785373,4260210401&fm=27&gp=0.jpg", userCiv);
        //创建返回键，并实现打开关/闭监听
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, commonToolbar, R.string.open, R.string.close) {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                //获取抽屉的view
                View mContent = drawerLayout.getChildAt(0);
                float scale = 1 - slideOffset;
                //在滑动时内容界面的宽度为 屏幕宽度减去菜单界面所占宽度
                mContent.setTranslationX(drawerView.getMeasuredWidth() * (1 - scale));
                //设置内容界面操作无效（比如有button就会点击无效）
                mContent.invalidate();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                kenBurnsView.pause(); // 停止滚动
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                kenBurnsView.resume(); // 开始滚动
            }
        };
        // 实现箭头和三条杠图案切换和抽屉拉合的同步
        mDrawerToggle.syncState();
        drawerLayout.addDrawerListener(mDrawerToggle);
    }

    @Override
    public void setStatusBar() {
        StatusBarUtil.setColorForDrawerLayout(this, drawerLayout, ContextCompat.getColor(this, R.color.colorPrimary), 40);
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

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            mFragments[FIRST] = ArticleFragment.newInstance();
            mFragments[SECOND] = ArticleFragment.newInstance();
            mFragments[THIRD] = NavigationFragment.newInstance();
            mFragments[FOUR] = ArticleFragment.newInstance();
            // 侧边菜单fragment
            mFragments[FIVE] = CollectFragment.newInstance("收藏");
            mFragments[SIX] = CollectFragment.newInstance("设置");
            loadMultipleRootFragment(R.id.fragment_container, FIRST, mFragments[FIRST],
                    mFragments[SECOND], mFragments[THIRD], mFragments[FOUR], mFragments[FIVE], mFragments[SIX]);
        } else {
            // 这里库已经做了Fragment恢复,所有不需要额外的处理了, 不会出现重叠问题
            mFragments[FIRST] = findFragment(ArticleFragment.class);
            mFragments[SECOND] = findFragment(ArticleFragment.class);
            mFragments[THIRD] = findFragment(NavigationFragment.class);
            mFragments[FOUR] = findFragment(ArticleFragment.class);
            // 侧边菜单fragment
            mFragments[FIVE] = findFragment(ArticleFragment.class);
            mFragments[SIX] = findFragment(ArticleFragment.class);
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        mDelegate.onDestroy();
        super.onDestroy();
    }


    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    /**
     * Set all fragments animation.
     * 构建Fragment转场动画
     * <p/>
     * 如果是在Activity内实现,则构建的是Activity内所有Fragment的转场动画,
     * 如果是在Fragment内实现,则构建的是该Fragment的转场动画,此时优先级 > Activity的onCreateFragmentAnimator()
     *
     * @return FragmentAnimator对象
     */
    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    @Override
    public void post(Runnable runnable) {
        mDelegate.post(runnable);
    }

    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }


    private long clickTime;

    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        long time = 2000;
        if ((currentTime - clickTime) > time) {
            SnackbarUtils.showSnackMessage(this, "再按一次后退键退出CWanAndroid");
            clickTime = System.currentTimeMillis();
        } else {
            finish();
        }
    }

    /**
     * 加载多个同级根Fragment,类似WeChat, QQ主页的场景
     */
    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }

    /**
     * show一个Fragment,hide其他同栈所有Fragment
     * 使用该方法时，要确保同级栈内无多余的Fragment,(只有通过loadMultipleRootFragment()载入的Fragment)
     * <p>
     * 建议使用更明确的{@link # showHideFragment(ISupportFragment, ISupportFragment)}
     *
     * @param showFragment 需要show的Fragment
     */
    public void showHideFragment(ISupportFragment showFragment) {
        mDelegate.showHideFragment(showFragment);
    }

    @OnClick(R.id.main_floating_action_btn)
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_floating_action_btn:
                // 通过接口，统一调用
                IFragment mFragment = (IFragment) mFragments[mNavigationController.getSelected()];
                mFragment.jumpToRVTop();
                break;
            default:

                break;
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_item_wan_android:
                changeFragmentAndTabFAB(FIRST);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_item_my_collect:
                changeFragmentAndTabFAB(FIVE);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_item_setting:
                changeFragmentAndTabFAB(SIX);
                drawerLayout.closeDrawers();
                break;
            case R.id.nav_item_about_us:
                SnackbarUtils.showSnackMessage(this, "关于我们");
                return false;
            case R.id.nav_item_logout:
                SnackbarUtils.showSnackMessage(this, "退出登录");
                return false;
            default:
                changeFragmentAndTabFAB(FIRST);
                drawerLayout.closeDrawers();
                break;
        }
        return true;
    }

    /**
     * 改变显示的Fragment和顶部导航栏
     */
    private void changeFragmentAndTabFAB(int position) {
        showHideFragment(mFragments[position]);
        commonToolbarTitleTv.setText(mTitles[position]);
        // 只有第一个Fragment 首页 才设置tab的位置
        if (position == 0) {
            mNavigationController.setSelect(position);
            flBottomTab.setVisibility(View.VISIBLE);
            mainFloatingActionBtn.setVisibility(View.VISIBLE);
        } else {
            flBottomTab.setVisibility(View.GONE);
            mainFloatingActionBtn.setVisibility(View.GONE);
        }
    }
}
