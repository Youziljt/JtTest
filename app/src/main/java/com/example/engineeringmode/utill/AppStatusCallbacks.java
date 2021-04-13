package com.example.engineeringmode.utill;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.engineeringmode.MainActivity;

/**
 * @author ljt
 * Date: 3/18/21
 * Time: 2:07 PM
 * Description: test app点击home键，app后台切换前台页面工具：
 */
public class AppStatusCallbacks implements Application.ActivityLifecycleCallbacks {


    private boolean mMainOnStop = false;
    private boolean mMainOnResumed = false;
    private int activityCount = 0;

    @Override
    public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle bundle) {

    }

    @Override
    public void onActivityStarted(@NonNull Activity activity) {
        activityCount++;
    }

    @Override
    public void onActivityResumed(@NonNull Activity activity) {

        mMainOnResumed = (activity instanceof MainActivity);
        if (mMainOnResumed && mMainOnStop && activityCount == 1) {
            //TODO
        }
    }

    @Override
    public void onActivityPaused(@NonNull Activity activity) {
    }

    @Override
    public void onActivityStopped(@NonNull Activity activity) {
        mMainOnStop = (activity instanceof MainActivity);
        activityCount--;
    }

    @Override
    public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(@NonNull Activity activity) {

    }
}
