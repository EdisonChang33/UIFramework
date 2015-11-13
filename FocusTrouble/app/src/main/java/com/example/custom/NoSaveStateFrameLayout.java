package com.example.custom;

import android.content.Context;
import android.graphics.Rect;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

/**
 * @author EdisonChang
 */
public class NoSaveStateFrameLayout extends FrameLayout {

    public static ViewGroup wrap(View child) {
        NoSaveStateFrameLayout wrapper = new NoSaveStateFrameLayout(child.getContext());
        ViewGroup.LayoutParams childParams = child.getLayoutParams();
        if (childParams != null) {
            wrapper.setLayoutParams(childParams);
        }
        LayoutParams lp = new LayoutParams(
                ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
        child.setLayoutParams(lp);
        wrapper.addView(child);
        return wrapper;
    }

    public NoSaveStateFrameLayout(Context context) {
        super(context);
    }

    /**
     * Override to prevent freezing of any child views.
     */
    @Override
    protected void dispatchSaveInstanceState(SparseArray<Parcelable> container) {
        dispatchFreezeSelfOnly(container);
    }

    /**
     * Override to prevent thawing of any child views.
     */
    @Override
    protected void dispatchRestoreInstanceState(SparseArray<Parcelable> container) {
        dispatchThawSelfOnly(container);
    }

    @Override
    public boolean requestFocus(int direction, Rect previouslyFocusedRect) {
        return true;
    }
}