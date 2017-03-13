package com.wuxian.charge.login;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wuxian.charge.R;
import com.wuxian.charge.base.BaseActivity;
import com.wuxian.charge.databinding.ActivityLoginBinding;

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityLoginBinding binding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        binding.setLoginViewModel(new LoginViewModel(this));
    }
}
