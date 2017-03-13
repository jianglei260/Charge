package com.wuxian.charge.base;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

/**
 * Created by jianglei on 2017/3/6.
 */

public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                if (name.equals("Button"))
                    return new CustomButton(context, attrs);
                AppCompatDelegate delegate = getDelegate();
                View view = delegate.createView(parent, name, context, attrs);
                return view;
            }
        });
        super.onCreate(savedInstanceState);
    }
    private IntentFilter filter = new IntentFilter();
    private boolean receiverRegisted = false;
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            onEvent(intent.getAction());
            onEvent(intent);
        }
    };

    protected void onEvent(Intent intent) {

    }

    protected void onEvent(String action) {
    }

    protected void registeEventAction(String action) {
        filter.addAction(action);
        registerReceiver(receiver, filter);
        receiverRegisted = true;
    }

    public void publishEvent(String action) {
        Intent intent = new Intent(action);
        sendBroadcast(intent);
    }

    public void publishEvent(Intent intent) {
        sendBroadcast(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (receiverRegisted)
            unregisterReceiver(receiver);
    }


}
