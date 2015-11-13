package com.example.focustrouble;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.custom.ViewPagerCustom;

/**
 * @author EdisonChang
 */
public class MainActivity extends FragmentActivity implements ViewPagerCustom.OnPageChangeListener {

    private ViewPagerCustom mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViewPager();
    }

    private void initViewPager() {
        mViewPager = (ViewPagerCustom) this.findViewById(R.id.view_pager);
        FragmentPagerAdapter adapter = new HomePageAdapter(MainActivity.this.getSupportFragmentManager());
        mViewPager.addOnPageChangeListener(MainActivity.this);
        mViewPager.setOffscreenPageLimit(5);
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
