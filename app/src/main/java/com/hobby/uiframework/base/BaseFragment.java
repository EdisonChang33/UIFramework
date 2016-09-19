package com.hobby.uiframework.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.umeng.analytics.MobclickAgent;

/**
 * @author EdisonChang
 */
public abstract class BaseFragment extends Fragment {

    protected boolean isCreated = false;
    protected boolean isVisible = false;
    protected boolean isEnter = false;

    protected String getPageId() {
        return this.getClass().getSimpleName();
    }

    protected abstract boolean isViewPagerChild();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isCreated = true;
    }

    public void onResume() {
        super.onResume();
        if (isViewPagerChild() && isVisible || !isViewPagerChild())
            onEnter();
    }

    public void onPause() {

        if (isViewPagerChild() && isVisible || !isViewPagerChild())
            onLeave();

        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isCreated) {
            return;
        }

        isVisible = isVisibleToUser;
        if (isVisibleToUser)
            onEnter();
        else
            onLeave();
    }

    /**
     * 进入这个页面，代替onResume
     */
    protected void onEnter() {
        if (!isEnter) {

            //MobclickAgent.onPageStart(getPageId());
            isEnter = true;
        }
    }

    /**
     * 退出这个页面，代替onPause
     */
    protected void onLeave() {
        if (isEnter) {
            //MobclickAgent.onPageEnd(getPageId());
            isEnter = false;
        }
    }
}
