package com.wuxian.charge.login;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wuxian.charge.R;
import com.wuxian.charge.base.BaseActivity;
import com.wuxian.charge.databinding.ActivityAccountLoginBinding;

public class AccountLoginActivity extends BaseActivity {
    ActivityAccountLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_account_login);
        binding.setAccountViewModel(new AccountViewModel(this));
    }
}
