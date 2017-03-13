package com.wuxian.charge.data.repository.remote;

import android.content.Intent;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.wuxian.charge.app.Config;

import java.util.HashMap;

import retrofit2.Retrofit;

/**
 * Created by jianglei on 16/7/11.
 */

public class RetrofitProvider {
    public static Gson gson = new GsonBuilder()
            .create();

    public static Retrofit create() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Config.BASE_URL).addConverterFactory(new CustomConverterFactory(gson)).build();
        return retrofit;
    }

    public static Retrofit create(String url) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(new CustomConverterFactory(gson)).build();
        return retrofit;
    }

}
