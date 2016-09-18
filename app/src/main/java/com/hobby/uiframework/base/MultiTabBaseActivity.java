package com.hobby.uiframework.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qihoo.appstore.R;
import com.qihoo.appstore.search.SearchActivity;
import com.qihoo.appstore.widget.PagerSlidingTab;
import com.qihoo.appstore.widget.SecondaryToolbar;
import com.qihoo.appstore.widget.support.AppSkinHelper;

/**
 * @author EdisonChang
 */
public abstract class MultiTabBaseActivity extends BaseActivity {

    protected ViewPager mViewPager;
    protected PagerSlidingTab mPagerSlidingTab;
    protected RelativeLayout mDetailbodyLayout;
    protected View mFrameRoot;

    protected String mOriginalUrl;
    protected String mCurrentTag;
    protected String mTilte;
    protected SecondaryToolbar mToolbar;
    private int mPageType = 0;

    protected abstract PagerAdapter getAdapter(int item);

    protected abstract int getTagItem();

    protected abstract boolean hasData();

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);

        setContentView(R.layout.common_multitab_activity_wrapper_root);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mPagerSlidingTab = (PagerSlidingTab) findViewById(R.id.tabs);
        mDetailbodyLayout = (RelativeLayout) findViewById(R.id.detail_body);
        mFrameRoot = findViewById(R.id.refresh);

        initTabsValue();
        initToolbar();
        if (hasData()) {
            loadAdapter();
        }
    }

    private void initToolbar() {
        mToolbar = (SecondaryToolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleViewVisibility(View.VISIBLE);
        mToolbar.setTitleViewText(mTilte);
        mToolbar.setLeftViewBackground(AppSkinHelper.getReformDrawable(this, R.drawable.common_toobar_icon_back_layer));
        mToolbar.setRightViewBackground(AppSkinHelper.getReformDrawable(this, R.drawable.common_toobar_icon_search_layer));
        mToolbar.setListener(new SecondaryToolbar.OnToolbarListener() {

            @Override
            public void onToolbarClick(View v) {
                switch (v.getId()) {
                    case R.id.btn_left:
                        finish();
                        break;
                    case R.id.btn_right:
                        SearchActivity.startSearch(MultiTabBaseActivity.this, null);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    private void initTabsValue() {
        mPagerSlidingTab.setUnderlineHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 0, getResources().getDisplayMetrics()));
        mPagerSlidingTab.setIndicatorHeight((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()));
        mPagerSlidingTab.setSelectedTextColor(AppSkinHelper.getAttributeColor(this, R.attr.themeViewpagerIndicatorColorValue, Color.BLACK));
        mPagerSlidingTab.setTextColor(AppSkinHelper.getAttributeColor(this, R.attr.themeListItemDescColor, Color.parseColor("#999999")));
    }

    private void setAdapter(PagerAdapter adapter) {
        mViewPager.setAdapter(adapter);
        mPagerSlidingTab.setViewPager(mViewPager);
        mPagerSlidingTab.setOnPageChangeListener(new OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                onPagerSelected(arg0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
        mViewPager.setCurrentItem(getTagItem());
    }

    protected void loadAdapter() {
        setAdapter(getAdapter(mPageType));
    }

    @Override
    protected boolean composeByFragment() {
        return true;
    }

    @Override
    protected String getPageField() {
        return null;
    }

    public void setTabText(int position, String oldtitle, String newtitle) {
        View view = mPagerSlidingTab.findViewWithTag(oldtitle + position);
        if (view != null && view instanceof TextView) {
            TextView tab = (TextView) view;
            tab.setText(newtitle);
        }
    }

    protected void setOffscreenPageLimit(int limit){
        mViewPager.setOffscreenPageLimit(limit);
    }

    protected void onPagerSelected(int arg0){

    }
}
