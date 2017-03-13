package com.wuxian.charge.main;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wuxian.charge.R;
import com.wuxian.charge.base.BaseActivity;

public class MainActivity extends BaseActivity {
    private MainFragment mainFragment;
    private AdFragment adFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainFragment = new MainFragment();
        adFragment = new AdFragment();

        getFragmentManager().beginTransaction().add(R.id.container, mainFragment).commit();
        registeEventAction(Intent.ACTION_TIME_TICK);
    }

    @Override
    protected void onEvent(String action) {
        if (action.equals(Intent.ACTION_TIME_TICK)) {
            mainFragment.updateTime();
        }
    }
}
