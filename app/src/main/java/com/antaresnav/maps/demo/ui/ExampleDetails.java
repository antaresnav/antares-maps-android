/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.antaresnav.maps.demo.ui;

import androidx.annotation.IntDef;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class ExampleDetails {

    @IntDef( {SDK_TYPE_ANTARES, SDK_TYPE_GOOGLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface SdkType {
    }
    public static final int SDK_TYPE_ANTARES = 0;
    public static final int SDK_TYPE_GOOGLE = 1;

    private final int titleId;
    private final int descriptionId;
    private final int sdkType;
    private final Class<? extends AppCompatActivity> activityClass;

    public ExampleDetails(
            int titleId, int descriptionId, @SdkType int sdkType, Class<? extends AppCompatActivity> activityClass) {
        this.titleId = titleId;
        this.descriptionId = descriptionId;
        this.sdkType = sdkType;
        this.activityClass = activityClass;
    }

    public int getTitleId() {
        return titleId;
    }

    public int getDescriptionId() {
        return descriptionId;
    }

    public int getSdkType() {
        return sdkType;
    }

    public Class<? extends AppCompatActivity> getActivityClass() {
        return activityClass;
    }
}
