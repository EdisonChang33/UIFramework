package com.hobby.uiframework;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.hobby.uiframework.widget.gradualradiobar.GradualRadioGroup;

/**
 * @author EdisonChang
 */
public class MainActivity extends FragmentActivity implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;
    private GradualRadioGroup gradualRadioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        gradualRadioGroup = (GradualRadioGroup) findViewById(R.id.radiobar);
        initViewPager();
        gradualRadioGroup.setViewPager(mViewPager);
    }

    private void initViewPager() {
        mViewPager = (ViewPager) this.findViewById(R.id.viewpager);
        FragmentPagerAdapter adapter = new HomePageAdapter(MainActivity.this.getSupportFragmentManager());
        mViewPager.addOnPageChangeListener(MainActivity.this);
        mViewPager.setOffscreenPageLimit(4);
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
