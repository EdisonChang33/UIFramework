package com.hobby.uiframework.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import com.hobby.uiframework.base.BaseSingleActivity;
import com.hobby.uiframework.fragment.SimpleFragment;

/**
 * Created by EdisonChang on 2016/9/19.
 */
public class SingleTabActivity extends BaseSingleActivity {

    @Override
    protected Fragment initializeFragment() {
        return new SimpleFragment();
    }

    public static void startActivity(Context context) {
        Intent intent = new Intent(context, SingleTabActivity.class);
        context.startActivity(intent);
    }
}
