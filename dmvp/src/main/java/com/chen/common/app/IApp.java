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
package com.chen.common.app;

import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;

import com.chen.common.di.component.AppComponent;


/**
 * ================================================
 * 框架要求框架中的每个 {@link android.app.Application} 都需要实现此类,以满足规范
 *
 * Created by JessYan on 25/04/2017 14:54
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public interface IApp {
    /**
     * 获取全局的AppComponent,用于Activity/Frament初始化
     * @return APPComponent
     */
    @NonNull
    AppComponent getAppComponent();

    /**
     * App主题色，主要用于沉浸式状态栏
     * @return 颜色
     */
    @ColorRes Integer getAppMainColor();
}
