package com.example.ekpkdisnaker.api;

import com.example.ekpkdisnaker.response.RegisterResponse;
import com.example.ekpkdisnaker.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @FormUrlEncoded
    @POST("login")
    Call<UserResponse> login(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("register")
    Call<RegisterResponse> register(
            @Field("username") String username,
            @Field("nama_lengkap") String nama_lengkap,
            @Field("tmp_lahir") String tmp_lahir,
            @Field("jns_kelamin") String jns_kelamin,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("sts_nikah") String sts_nikah,
            @Field("kd_pendidikan") String kd_pendidikan,
            @Field("nama_pendidikan") String nama_pendidikan,
            @Field("alamat") String alamat,
            @Field("telepon") String telepon,
            @Field("email") String email,
            @Field("password") String password,
            @Field("agama") String agama
    );

    @GET("getUser")
    Call<UserResponse> getUser();
}
