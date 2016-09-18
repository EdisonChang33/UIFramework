package com.hobby.uiframework;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.hobby.uiframework.widget.NoSaveStateFrameLayout;

/**
 * @author EdisonChang
 */
public class CustomListFragment extends Fragment {

    protected View mRefreshView;
    protected View mRefreshRoot;
    protected ListView mListNormal;
    protected View mRefreshFoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        if (mListNormal == null) {
            mListNormal = (ListView) inflater.inflate(com.hobby.focustrouble.R.layout.common_list_view, null, false);
        }
        return NoSaveStateFrameLayout.wrap(mListNormal);
    }

    @Override
    public void setUserVisibleHint(boolean isVisible) {
        super.setUserVisibleHint(isVisible);
        if (isVisible) {
            loadDataIfEmpty();
        }
    }

    private void loadDataIfEmpty() {
        //load data from web
    }

    @Override
    public void onDestroyView() {
        mRefreshRoot = null;
        mListNormal = null;
        mRefreshView = null;
        mRefreshFoot = null;        
        super.onDestroyView();
    }
}
