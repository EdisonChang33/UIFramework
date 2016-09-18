package com.hobby.uiframework;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * @author EdisonChang
 */
public class HomePageAdapter extends FragmentPagerAdapter {

    private final String[] mTags;
    private final long[] defaultIds;
    private int mChildCount = 0;

    public static final int POSITION_0 = 0;
    public static final int POSITION_1 = 1;
    public static final int POSITION_2 = 2;
    public static final int POSITION_3 = 3;
    public static final int POSITION_4 = 4;

    public HomePageAdapter(FragmentManager fm) {
        super(fm);
        mTags = new String[]{"TAB1", "TAB2", "TAB3", "TAB4", "TAB5"};
        defaultIds = new long[mTags.length];
        initIds();
    }

    private void initIds() {
        int size = mTags.length;
        for (int i = 0; i < size; i++) {
            defaultIds[i] = i;
        }
    }

    @Override
    public Fragment getItem(int index) {
        Fragment fragment;
        switch (index) {
            case POSITION_0:
                fragment = new MultiTabBaseFragment();
                break;
            case POSITION_1:
                fragment = new MultiTabBaseFragment();
                break;
            case POSITION_2:
                fragment = new MultiTabBaseFragment();
                break;
            case POSITION_3:
                fragment = new MultiTabBaseFragment();
                break;
            case POSITION_4:
                fragment = new MultiTabBaseFragment();
                break;
            default:
                fragment = new Fragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return mTags == null ? 0 : mTags.length;
    }

    @Override
    public long getItemId(int position) {
        return defaultIds[position];
    }

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        if (mChildCount > 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
