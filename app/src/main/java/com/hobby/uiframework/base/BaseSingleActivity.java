package com.hobby.uiframework.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.hobby.uiframework.R;

/**
 * @author EdisonChang
 */
public abstract class BaseSingleActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_single_tab_wrapper);

        beginSubFragmentTransaction();
    }

    private void beginSubFragmentTransaction() {
        Fragment fragment = initializeFragment();
        if (fragment != null)
            getSupportFragmentManager().beginTransaction().add(R.id.common_content_layout, fragment).commit();
    }

    @Override
    protected boolean composeByFragment() {
        return true;
    }

    @Override
    protected String getPageId() {
        return null;
    }

    protected abstract Fragment initializeFragment();
}
