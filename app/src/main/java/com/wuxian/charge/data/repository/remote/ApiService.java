package com.wuxian.charge.data.repository.remote;

import com.wuxian.charge.data.entities.Entity;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by jianglei on 2017/3/12.
 */

public interface ApiService {
    @GET("")
    public Call<Entity<String>> getQRCode(@Query("branch_id") String branchId);
    @POST
    @FormUrlEncoded
    public Call<Entity<String>> loginByAccount(@Field("mobile") String mobile,@Field("password") String password);
}
