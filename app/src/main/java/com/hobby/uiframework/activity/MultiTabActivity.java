package com.hobby.uiframework.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;

import com.hobby.uiframework.base.MultiTabBaseActivity;
import com.hobby.uiframework.fragment.GridFragment;
import com.hobby.uiframework.fragment.ListFragment;
import com.hobby.uiframework.fragment.SimpleFragment;

/**
 * Created by EdisonChang on 2016/9/19.
 */
public class MultiTabActivity extends MultiTabBaseActivity {

    @Override
    protected PagerAdapter getAdapter() {
        return new ChildAdapter(getSupportFragmentManager());
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, MultiTabActivity.class);
        context.startActivity(intent);
    }

    private class ChildAdapter extends FragmentStatePagerAdapter {

        private String[] itemNames = {"tab1", "tab2", "tab3"};

        public ChildAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new ListFragment();
                    break;
                case 1:
                    fragment = new GridFragment();
                    break;
                case 2:
                    fragment = new SimpleFragment();
                    break;
                default:
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return itemNames.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position >= itemNames.length)
                return null;

            return itemNames[position];
        }
    }
}
