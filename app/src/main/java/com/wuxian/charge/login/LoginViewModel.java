package com.wuxian.charge.login;

import android.app.Activity;
import android.content.Intent;

import com.kelin.mvvmlight.command.ReplyCommand;
import com.wuxian.charge.base.BackableViewModel;

import rx.functions.Action0;

/**
 * Created by jianglei on 2017/3/5.
 */

public class LoginViewModel extends BackableViewModel {
    private LoginActivity activity;


    public ReplyCommand qrcodeClick = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            startActivity(QRCodeActivity.class);
        }
    });
    public ReplyCommand fingerClick = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            startActivity(FingerLoginActivity.class);
        }
    });
    public ReplyCommand accountClick = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            startActivity(AccountLoginActivity.class);
        }
    });

    public void startActivity(Class<? extends Activity> target) {
        Intent intent = new Intent(activity, target);
        activity.startActivity(intent);
    }

    public LoginViewModel(LoginActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onBackClick() {
        activity.finish();
    }
}
