package com.example.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ListView;

/**
 * @author EdisonChang
 */
public class ListViewCustom extends ListView {
    public ListViewCustom(Context context) {
        super(context);
    }

    public ListViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFocusChanged(boolean gainFocus, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(gainFocus, direction, previouslyFocusedRect);
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
    }
}
