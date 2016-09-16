package com.example.focustrouble;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * @author EdisonChang
 */
public class MultiTabBaseFragment extends Fragment {

    protected ViewPager mViewPager;
    protected View mRoot;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRoot = inflater.inflate(R.layout.multi_tab_fragment_layout, null);
        init();
        return mRoot;
    }

    protected void init() {
        mViewPager = (ViewPager) mRoot.findViewById(R.id.view_page);
        loadAdapter();
    }

    protected void loadAdapter() {
        if (mViewPager.getAdapter() != null)
            return;

        PagerAdapter adapter = getAdapter();
        if (adapter != null) {
            mViewPager.setAdapter(adapter);
        }
    }

    protected PagerAdapter getAdapter() {
        return new SubTabAdapter(getActivity(), getChildFragmentManager());
    }

    private class SubTabAdapter extends FragmentStatePagerAdapter {

        private Context mContext;

        private String[] itemNames = {"sub_tab1", "sub_tab2", "sub_tab3", "sub_tab4", "sub_tab5"};

        public SubTabAdapter(Context context, FragmentManager manager) {
            super(manager);
            mContext = context;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new CustomListFragment();
                    break;
                case 1:
                    fragment = new CustomListFragment();
                    break;
                case 2:
                    fragment = new CustomListFragment();
                    break;
                case 3:
                    fragment = new CustomListFragment();
                    break;
                case 4:
                    fragment = new CustomListFragment();
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
            if (mContext == null || position >= itemNames.length)
                return null;

            return itemNames[position];
        }
    }

}
