package com.hobby.uiframework.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;

import com.hobby.uiframework.R;
import com.hobby.uiframework.activity.MultiTabActivity;
import com.hobby.uiframework.base.BaseListFragment;
import com.hobby.uiframework.support.DataLoader;
import com.hobby.uiframework.widget.GridViewWithHeader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by EdisonChang on 2016/9/19.
 */
public class GridFragment extends BaseListFragment {

    private final String[] title = {"TEST1", "TEST2", "TEST3", "TEST4", "TEST1", "TEST2", "TEST3", "TEST4"};
    private final String[] desc = {"click_me to start multi tab activity", "TEST2_desc", "TEST3_desc", "TEST4_desc", "TEST1_desc", "TEST2_desc", "TEST3_desc", "TEST4_desc"};


    @Override
    protected AbsListView onCreateList() {
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        return (GridViewWithHeader) inflater.inflate(R.layout.common_grid_view, null, false);
    }

    @Override
    protected void onListViewCreated(AbsListView list) {
        List<Map<String, Object>> listems = new ArrayList<>();
        for (int i = 0; i < title.length; i++) {
            Map<String, Object> listem = new HashMap<>();
            listem.put("common_list_name", title[i]);
            listem.put("common_list_desc", desc[i]);
            listems.add(listem);
        }

        SimpleAdapter adapter = new SimpleAdapter(getActivity(),
                listems, R.layout.simple_grid_item,
                new String[]{"common_list_name", "common_list_desc"},
                new int[]{R.id.common_list_name, R.id.common_list_desc});

        ((GridViewWithHeader) list).setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MultiTabActivity.startActivity(getActivity());
            }
        });
    }

    @Override
    protected void addFooterView(AbsListView list, View foot) {
        ((GridViewWithHeader) list).addFooterView(foot);
    }

    @Override
    protected void refreshList() {

    }

    @Override
    protected void onDestroyList() {

    }

    @Override
    protected DataLoader onCreateDataLoader() {
        return new DataLoader() {
            @Override
            public void loadMore() {

            }

            @Override
            public void loadData() {

            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public void setUrl(String url) {

            }

            @Override
            public String getUrl() {
                return null;
            }
        };
    }

    @Override
    protected boolean isViewPagerChild() {
        return false;
    }
}
