package com.wuxian.charge.login;

import android.content.Intent;
import android.databinding.ObservableField;

import com.kelin.mvvmlight.command.ReplyCommand;
import com.wuxian.charge.base.BackableViewModel;
import com.wuxian.charge.rent.RentActivity;

import rx.functions.Action0;

/**
 * Created by jianglei on 2017/2/28.
 */

public class AccountViewModel extends BackableViewModel{
    private AccountLoginActivity activity;
    public ObservableField<String> userName = new ObservableField<>();
    public ObservableField<String> passWord = new ObservableField<>();

    public ReplyCommand loginClick = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            Intent intent = new Intent(activity, RentActivity.class);
            activity.startActivity(intent);

        }
    });

    public AccountViewModel(AccountLoginActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onBackClick() {

    }
}
