# DMvp

## 基于mvp + retrofit + rxjava1 + dagger2的一个简单开发框架 (我的菜比  我是菜比 我是菜比，重要的事情说三遍！)

[![](https://jitpack.io/v/chenyy0708/DMvp.svg)](https://jitpack.io/#chenyy0708/DMvp)


## 导入地址
`compile 'com.github.chenyy0708:DMvp:lastVersion'`

> 在app的build.grdle文件中添加,[dagger2最新版本号](https://github.com/google/dagger) <br>

` annotationProcessor 'com.google.dagger:dagger-compiler:2.x' // 编译代码使用`


# 使用方式

## 1.1 配置Application，设置url地址

> Application需要实现IAPP接口，在BaseActivity中会根据IAPP获取到全局的Application，用于初始化，最后记得在AndroidManifest文件中声明。

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
## 1.2 Dagger2配置  

### 1.2.1 Module配置
> 需要配置Module类，主要提供一些对象以供使用,这里Module构造方法中传入View，在Presenter中可以直接通过Module获取到View的实例对象，用于一些ui操作。

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

### 1.2.2 Component配置
> Component的作用主要是为了使module提供的对象和inject产生关联。

```
@ActivityScope
@Component(modules = TestModule.class, dependencies = AppComponent.class)
public interface TestComponent {
    void inject(TestActivity activity);
    void inject(TestFragment testFragment);
}
```


## 1.3 在Activity/Fragment中初始化

> 通过testModule可以获取到一系列需要初始化的对象，省去了在各个地方New的操作。

```
@Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTestComponent.builder()
                .appComponent(appComponent)
                .testModule(new TestModule(this))
                .build()
                .inject(this);
    }
```

## 1.4 Mvp和Dagger2的使用


### 1.4.1 创建一个Contract，统一管理Model和View接口。

```
/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public interface TestContract {


    //对于经常使用的关于UI的方法可以定义到IView中,如显示隐藏进度条,和显示文字消息
    interface View extends BaseView {
        void setData(List<Meizhi> mData);

        Activity getVActivity();
    }

    //Model层定义接口,外部只需关心Model返回的数据,无需关心内部细节,如是否使用缓存
    interface Model extends BaseModel {
        Observable<MeizhiData> getMeizhi(int page);
    }

}
```

### 1.4.2 创建Model，实现Contract中定义的Model接口


> 这里构造方法中的IRetrofitManager是在NetModule中provide的，并且是全局单例模式，通过IRetrofitManager获取到业务Service中的接口，请求网络数据。


```
/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */
@ActivityScope
public class TestModel implements TestContract.Model {

    private IRetrofitManager retrofitManager;

    @Inject
    public TestModel(IRetrofitManager retrofitManager) {
        this.retrofitManager = retrofitManager;
    }

    @Override
    public Observable<MeizhiData> getMeizhi(int page) {
        return retrofitManager.obtainRetrofitService(ApiService.class)
                .getMeizhiData(page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}

```


### 1.4.3 创建一个Presenter，用于处理业务逻辑。


> 首先看下Basepresenter中的处理,通过构造方法保存V和M的实例对象，用于处理获取网络数据，和UI更新，RxManager用于取消订阅，防止Activity内存泄露。

```
public abstract class BasePresenter<T, E> {
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param rootView
     */
    public BasePresenter(E model, T rootView) {
        Preconditions.checkNotNull(model, "%s cannot be null", BaseModel.class.getName());
        Preconditions.checkNotNull(rootView, "%s cannot be null", BaseView.class.getName());
        this.mModel = model;
        this.mView = rootView;
        onStart();
    }

    public void onStart() {
    }

    public void onDestroy() {
        mRxManage.clear();
    }
}
```

> 创建一个Presenter继承Basepresnter，通过@Inject得到我们上面自己创建的TestModule中提供的Adapter、List、View、LayoutManager等。在获取到了网络数据之后，直接使用全局的Adapter更新Activity中的UI，达到了解耦的效果。

```
/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class TestPresenter extends BasePresenter<TestContract.View, TestContract.Model> {

    private BaseQuickAdapter mAdapter;


    private List<Meizhi> mData;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param model
     * @param rootView
     */
    @Inject
    public TestPresenter(TestContract.Model model, TestContract.View rootView, BaseQuickAdapter mAdapter, List<Meizhi> mData) {
        super(model, rootView);
        this.mAdapter = mAdapter;
        this.mData = mData;
        this.mContext = rootView.getVActivity();
    }

    public void getData(int page) {
        mRxManage.add(mModel.getMeizhi(page)
                .subscribe(new RxSubscriber<MeizhiData>(mContext, true) {
                    @Override
                    protected void _onNext(MeizhiData meizhiData) {
                        mData.addAll(meizhiData.results);
                        mAdapter.notifyDataSetChanged();
                    }

                    @Override
                    protected void _onError(String message) {
                        mView.showErrorTip(message);
                    }
                }));
    }
}

```

## 1.5 最后贴一下Activity中的完整代码

> 所有的代码编写完，点击Build --> Make Project 自动生成ComPonent代码。

```

/**
 * @author :ChenYangYi
 * @time :2018/4/2
 * @desc :
 */

public class TestActivity extends BaseActivity<TestPresenter> implements TestContract.View {
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Inject
    BaseQuickAdapter mAdapter;
    @Inject
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    public int getLayoutId() {
        return R.layout.activity_user_detail;
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        recyclerView.setLayoutManager(mLayoutManager);
        //如果可以确定每个item的高度是固定的，设置这个选项可以提高性能
        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        mPresenter.getData(10);
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {
        DaggerTestComponent.builder()
                .appComponent(appComponent)
                .testModule(new TestModule(this))
                .build()
                .inject(this);
    }

    @Override
    public void showErrorTip(String msg) {
        showShortToast(msg);
    }

    @Override
    public Activity getVActivity() {
        return this;
    }

}
```
# Thanks

[MVPArms](https://github.com/JessYanCoding/MVPArms) <br>
[AndroidFire](https://github.com/jaydenxiao2016/AndroidFire)<br>
[retrofit2.0](https://github.com/square/retrofit) <br>
[Rxjava](https://github.com/ReactiveX/RxJava) <br>
[dagger2](https://github.com/google/dagger) <br>

# License

```
Copyright 2018, ChenYangYi       
  
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at 
 
       http://www.apache.org/licenses/LICENSE-2.0 

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```






