# DMvp
基于mvp + retrofit + rxjava1 + dagger2的一个简单开发框架

[![](https://jitpack.io/v/chenyy0708/DMvp.svg)](https://jitpack.io/#chenyy0708/DMvp)


##导入地址
`compile 'com.github.chenyy0708:DMvp:vlastVersion'`

#使用方式

##1.1 配置Application，设置url地址

    Application需要实现IAPP接口，在BaseActivity中会根据IAPP获取到全局的APPComponent，用于初始化，最后记得在AndroidManifest文件中声明。

```
public class App extends BaseApplication implements IApp {
    private AppComponent appComponent;
    public static final String SERVICE_URL = "服务器地址";

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(SERVICE_URL))
                .build();
    }

    @NonNull
    @Override
    public AppComponent getAppComponent() {
        return appComponent;
    }
}
```
##1.2 Dagger2配置  

    需要配置Module类，主要提供一些对象以供使用

```
@Module
public class TestModule {

    private TestContract.View view;

    /**
     * 构建 UserModule 时,将 View 的实现类传进来,这样就可以提供 View 的实现类给 Presenter
     *
     * @param view
     */
    public TestModule(TestContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    TestContract.View provideTestView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    TestContract.Model provideTestModel(TestModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    BaseQuickAdapter provideUserDetailAdapter(List<Meizhi> list) {
        return new UserDetailAdapter(R.layout.item_user_detail, list);
    }

    @ActivityScope
    @Provides
    List<Meizhi> provideUserDetailList() {
        return new ArrayList<>();
    }

    @ActivityScope
    @Provides
    RecyclerView.LayoutManager provideLayoutManager() {
        return new LinearLayoutManager(view.getVActivity());
    }
}
```





