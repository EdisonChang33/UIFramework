package com.hobby.uiframework.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;

import com.hobby.uiframework.R;
import com.hobby.uiframework.widget.PagerSlidingTab;


/**
 * @author EdisonChang
 */
public abstract class MultiTabBaseActivity extends BaseActivity {

    protected ViewPager mViewPager;
    protected PagerSlidingTab mPagerSlidingTab;

    protected abstract PagerAdapter getAdapter();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.common_multi_tab_wrapper);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerSlidingTab = (PagerSlidingTab) findViewById(R.id.tabs);

        initTabsValue();
        loadAdapter();
    }

    private void initTabsValue() {
        mPagerSlidingTab.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mPagerSlidingTab.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
        mPagerSlidingTab.setSelectedTextColor(Color.BLACK);
        mPagerSlidingTab.setTextColor(Color.parseColor("#999999"));
    }

    private void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mPagerSlidingTab.setViewPager(mViewPager);
        mPagerSlidingTab.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    protected void loadAdapter() {
        setAdapter(getAdapter());
    }

    @Override
    protected boolean composeByFragment() {
        return true;
    }

}
