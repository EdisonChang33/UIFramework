package com.hobby.uiframework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hobby.uiframework.R;
import com.hobby.uiframework.base.BaseFragment;

/**
 * Created by EdisonChang on 2016/9/19.
 */
public class SimpleFragment extends BaseFragment {

    @Override
    protected boolean isViewPagerChild() {
        return false;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getActivity()).inflate(R.layout.simple_fragment_root, null, false);
    }
}
