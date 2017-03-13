package com.wuxian.charge.base;

import com.kelin.mvvmlight.command.ReplyCommand;

import rx.functions.Action0;

/**
 * Created by jianglei on 2017/3/5.
 */

public abstract class BackableViewModel extends BaseViewModel {
    public ReplyCommand backClick=new ReplyCommand(new Action0() {
        @Override
        public void call() {
            onBackClick();
        }
    });
    public abstract void onBackClick();
}
