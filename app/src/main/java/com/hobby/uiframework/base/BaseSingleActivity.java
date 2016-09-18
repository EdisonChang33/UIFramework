package com.hobby.uiframework.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hobby.uiframework.R;

/**
 * @author EdisonChang
 */
public abstract class BaseSingleActivity extends BaseActivity {

    private RelativeLayout mToolbar;
    private ImageView backIcon;
    private TextView titleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_single_activity_wrapper);

        initialize();
        beginSubFragmentTransaction();
    }

    private void initialize() {
        mToolbar = (RelativeLayout) findViewById(R.id.common_toolbar);
        backIcon = (ImageView) mToolbar.findViewById(R.id.common_back);
        titleView = (TextView) mToolbar.findViewById(R.id.common_title);
        titleView.setText(getTitleText());
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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

    protected abstract String getTitleText();

    protected abstract Fragment initializeFragment();
}
