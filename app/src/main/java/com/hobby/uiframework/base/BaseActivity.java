package com.hobby.uiframework.base;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.umeng.analytics.MobclickAgent;

/**
 * @author EdisonChang
 */
public abstract class BaseActivity extends FragmentActivity {

    protected abstract String getPageId();

    protected abstract boolean composeByFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!composeByFragment()) {
            MobclickAgent.onPageStart(getPageId());
        }

        MobclickAgent.onResume(this);
    }

    @Override
    public void onPause() {
        if (!composeByFragment()) {
            MobclickAgent.onPageEnd(getPageId());
        }

        MobclickAgent.onPause(this);
        super.onPause();
    }
}
