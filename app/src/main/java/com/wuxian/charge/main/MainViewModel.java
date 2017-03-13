package com.wuxian.charge.main;

import android.content.Intent;
import android.databinding.ObservableField;

import com.kelin.mvvmlight.command.ReplyCommand;
import com.wuxian.charge.base.BaseViewModel;
import com.wuxian.charge.login.LoginActivity;
import com.wuxian.charge.manage.ManageActivity;
import com.wuxian.charge.rent.RentActivity;

import rx.functions.Action0;

/**
 * Created by jianglei on 2017/2/28.
 */

public class MainViewModel extends BaseViewModel{
    private MainFragment fragment;
    public ObservableField<String> date=new ObservableField<>();
    public ReplyCommand rentClick = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            Intent intent=new Intent(fragment.getActivity(), LoginActivity.class);
            fragment.startActivity(intent);
        }
    });

    public ReplyCommand returnClick = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            Intent intent=new Intent(fragment.getActivity(), LoginActivity.class);
            fragment.startActivity(intent);
        }
    });
    public ReplyCommand adminClick = new ReplyCommand(new Action0() {
        @Override
        public void call() {
            Intent intent=new Intent(fragment.getActivity(), ManageActivity.class);
            fragment.startActivity(intent);
        }
    });
    public MainViewModel(MainFragment fragment) {
        this.fragment = fragment;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fragment.updateTime();
    }
}
