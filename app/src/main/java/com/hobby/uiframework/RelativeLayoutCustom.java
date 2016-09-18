package com.hobby.uiframework;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.RelativeLayout;

/**
 * @author EdisonChang
 */

public class RelativeLayoutCustom extends RelativeLayout {

    public RelativeLayoutCustom(Context context) {
        super(context);
    }

    public RelativeLayoutCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        Log.d("RelativeLayoutCustom", "onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);

        Log.d("RelativeLayoutCustom", "onLayout changed = " + changed);
    }

    @Override
    public void requestLayout() {
        super.requestLayout();

        Log.d("RelativeLayoutCustom", "requestLayout");
    }
}
