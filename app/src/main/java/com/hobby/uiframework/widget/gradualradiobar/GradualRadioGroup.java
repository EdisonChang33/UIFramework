package com.hobby.uiframework.widget.gradualradiobar;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioGroup;


/**
 * Created by Droidroid on 2016/4/27.
 */
public class GradualRadioGroup extends RadioGroup implements ViewPager.OnPageChangeListener {

    private ViewPager mViewPager;

    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mViewPager.addOnPageChangeListener(this);
    }

    public GradualRadioGroup(Context context) {
        super(context);
    }

    public GradualRadioGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        for (int i = 0; i < getChildCount(); i++) {
            final int position = i;
            getChildAt(i).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    setClickedViewChecked(position);
                    if (mViewPager != null) {
                        mViewPager.setCurrentItem(position, false);
                    }
                }
            });
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        updateGradient(position, positionOffset);
    }

    private void updateGradient(int position, float offset) {
        if (offset > 0) {
            ((GradualRadioButton) getChildAt(position)).updateAlpha(255 * (1 - offset));
            ((GradualRadioButton) getChildAt(position + 1)).updateAlpha(255 * offset);
        }
    }

    @Override
    public void onPageSelected(int position) {
        setSelectedViewChecked(position);
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void setSelectedViewChecked(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            ((GradualRadioButton) getChildAt(i)).setChecked(false);
        }
        ((GradualRadioButton) getChildAt(position)).setChecked(true);
    }

    public void setClickedViewChecked(int position) {
        for (int i = 0; i < getChildCount(); i++) {
            ((GradualRadioButton) getChildAt(i)).setRadioButtonChecked(false);
        }
        ((GradualRadioButton) getChildAt(position)).setRadioButtonChecked(true);
    }
}
