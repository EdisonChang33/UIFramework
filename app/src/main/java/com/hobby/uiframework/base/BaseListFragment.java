package com.hobby.uiframework.base;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.hobby.uiframework.R;
import com.hobby.uiframework.support.DataLoader;
import com.hobby.uiframework.widget.NoSaveStateFrameLayout;

/**
 * @author EdisonChang
 */
public abstract class BaseListFragment extends BaseFragment implements View.OnClickListener, AbsListView.OnScrollListener {

    abstract protected AbsListView onCreateList();

    abstract protected void onListViewCreated(AbsListView list);

    abstract protected void addFooterView(AbsListView list, View foot);

    abstract protected void refreshList();

    abstract protected void onDestroyList();

    abstract protected DataLoader onCreateDataLoader();

    protected DataLoader loader;
    protected View mRefreshView;

    protected ViewGroup mRootLayout;
    protected View mRefreshRoot;
    protected View mListWrapper;   //ViewGroup
    protected AbsListView absListView;
    protected View mRefreshFoot;
    protected int scrollState;
    private Parcelable listState;
    private boolean resumeLoad = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        if (absListView == null) {
            absListView = onCreateList();
            absListView.setOnScrollListener(this);
            scrollState = SCROLL_STATE_IDLE;
            mRefreshFoot = inflater.inflate(R.layout.common_list_foot, null);
            addFooterView(absListView, mRefreshFoot);
            mRefreshFoot.findViewById(R.id.footer_refresh_retry).setOnClickListener(this);
            mRefreshFoot.setVisibility(View.GONE);
            onListViewCreated(absListView);
        }

        mRootLayout = getRootLayout();
        if (mRootLayout == null) {
            mRootLayout = NoSaveStateFrameLayout.wrap(null == mListWrapper ? absListView : mListWrapper);
        }

        return mRootLayout;
    }

    @SuppressLint("InflateParams")
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mRefreshRoot == null) {
            mRefreshRoot = LayoutInflater.from(getActivity()).inflate(R.layout.common_refresh_root, null);
            mRefreshView = mRefreshRoot.findViewById(R.id.refresh_layout);
            mRefreshRoot.findViewById(R.id.common_retry).setOnClickListener(this);
        }

        if (!addCommonRrefresh()) {
            View rootView = getView();
            if (rootView instanceof ViewGroup) {
                ViewGroup root = (ViewGroup) rootView;
                root.addView(mRefreshRoot, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
        }
    }

    protected boolean addCommonRrefresh() {
        return false;
    }

    @Override
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        if (loader == null) {
            loader = onCreateDataLoader();
        }
    }

    protected void loadDataIfEmpty() {
        if (loader != null && loader.isEmpty() && loader.getLoadState() != DataLoader.LOAD_STATE_LOADING) {
            loader.setLoadState(DataLoader.LOAD_STATE_NORMAL);
            loadData();
        } else if (loader == null || !loader.isEmpty()) {
            refresh(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (listState != null) {
            absListView.onRestoreInstanceState(listState);
        }
        if ((isVisible || !isViewPagerChild()) && resumeLoad) {
            loadDataIfEmpty();
        }
    }

    @Override
    public void onPause() {
        listState = absListView.onSaveInstanceState();
        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisible) {
        super.setUserVisibleHint(isVisible);
        if (isVisible && loader != null && getView() != null) {
            loadDataIfEmpty();
        }
    }

    @Override
    public void onDestroyView() {
        onDestroyList();
        if (loader != null) {
            loader.cancel();
        }
        mRefreshRoot = null;
        absListView = null;
        mRefreshView = null;
        mRefreshFoot = null;
        super.onDestroyView();
    }

    protected void refresh(boolean needRefresh) {

        if (needRefresh) {
            refreshList();
        }

        if (loader == null) {
            return;
        }

        final int loadState = loader.getLoadState();
        boolean isEmpty = isDataEmpty();
        boolean isLoading = loadState == DataLoader.LOAD_STATE_LOADING;
        boolean isFailed = loadState == DataLoader.LOAD_STATE_FAILED;
        final boolean isEnd = loadState == DataLoader.LOAD_STATE_FINISHED;

        if (mRefreshView != null && mRefreshFoot != null) {
            if (isEmpty) {
                showView(mRefreshView.findViewById(R.id.common_loading), isLoading);
                showView(mRefreshView.findViewById(R.id.common_retry), isFailed);
                showView(mRefreshView.findViewById(R.id.common_no_content), !isLoading && !isFailed);
            } else {
                showView(mRefreshFoot.findViewById(R.id.RefreshProgress), !isEnd && !isFailed);
                showView(mRefreshFoot.findViewById(R.id.footer_refresh_retry), isFailed);
            }
        }

        showView(mRefreshView, isEmpty);
        showView(absListView, !isEmpty);
        showView(mRefreshFoot, !isEmpty && !isEnd);
    }

    private boolean isDataEmpty() {
        return loader.isEmpty();
    }

    protected void showView(View v, boolean bShow) {
        if (v != null) {
            v.setVisibility(bShow ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.footer_refresh_retry:
            case R.id.common_retry:
                loadData();
                break;
            default:
                break;
        }
    }

    protected void loadData() {
        if (loader != null) {
            loader.loadData();
        }

        refresh(false);
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if (totalItemCount <= 1) {
            return;
        }

        if (isVisible && loader != null && loader.getLoadState() == DataLoader.LOAD_STATE_NORMAL && firstVisibleItem + visibleItemCount == totalItemCount) {
            loadData();
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        this.scrollState = scrollState;
    }

    protected ViewGroup getRootLayout() {
        return null;
    }
}
