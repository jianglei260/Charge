package com.wuxian.charge.main;

import android.app.Fragment;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wuxian.charge.R;
import com.wuxian.charge.databinding.FragmentMainBinding;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by jianglei on 2017/2/28.
 */

public class MainFragment extends Fragment {
    FragmentMainBinding binding;
    MainViewModel mainViewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        mainViewModel=new MainViewModel(this);
        binding.setMainViewModel(mainViewModel);
        mainViewModel.onCreate();
        return binding.getRoot();
    }

    public void updateTime() {
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy年MM月dd日 HH时mm分");
//        calendar.get(Calendar.YEAR) + "年" + calendar.get(Calendar.MONTH) + "月" + calendar.get(Calendar.DATE) +"日"+ calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE);
        String dateStr = format.format(date);
        mainViewModel.date.set(dateStr);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainViewModel.onDestory();
    }
}
