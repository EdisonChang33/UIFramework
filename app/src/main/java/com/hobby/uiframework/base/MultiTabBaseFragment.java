package com.hobby.uiframework.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hobby.uiframework.R;
import com.hobby.uiframework.widget.PagerSlidingTab;

/**
 * @author EdisonChang
 */
public abstract class MultiTabBaseFragment extends Fragment {

    protected ViewPager mViewPager;
    protected PagerSlidingTab mPagerSlidingTab;
    protected View mRoot;

    protected abstract PagerAdapter getAdapter();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.common_multi_tab_wrapper, null);
        initView();
        return mRoot;
    }

    protected void initView() {
        initViewpager();
        initPageSlidingTab();
    }

    private void initPageSlidingTab() {
        if (mPagerSlidingTab == null) {
            mPagerSlidingTab = (PagerSlidingTab) mRoot.findViewById(R.id.tabs);
            mPagerSlidingTab.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
            mPagerSlidingTab.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
            initPagerSlidingTab(mPagerSlidingTab);
            mPagerSlidingTab.setViewPager(mViewPager);
        }
    }

    private void initPagerSlidingTab(PagerSlidingTab pagerSlidingTab) {
        pagerSlidingTab.setSelectedTextColor(Color.BLACK);
        pagerSlidingTab.setTextColor(Color.parseColor("#999999"));
    }

    private void initViewpager() {
        mViewPager = (ViewPager) mRoot.findViewById(R.id.viewpager);
        PagerAdapter adapter = getAdapter();
        if (adapter != null) {
            mViewPager.setAdapter(adapter);
        }
    }
}
