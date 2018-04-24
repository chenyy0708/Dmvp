/**
 * Copyright 2017 JessYan
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wanandroid.di.component;


import com.chen.common.di.ActivityScope;
import com.chen.common.di.component.AppComponent;
import com.wanandroid.di.module.ArticleModule;
import com.wanandroid.ui.fragment.ArticleFragment;

import dagger.Component;

/**
 * @author :ChenYangYi
 * @date  :2018/4/18
 * @description : 首页文章Fragment Component
 */
@ActivityScope
@Component(modules = ArticleModule.class, dependencies = AppComponent.class)
public interface ArticleComponent {
    void inject(ArticleFragment activity);
}
