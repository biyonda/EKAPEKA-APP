package com.example.ekpkdisnaker.api;

import com.example.ekpkdisnaker.response.BaseResponse;
import com.example.ekpkdisnaker.response.RegisterResponse;
import com.example.ekpkdisnaker.response.UserResponse;
import com.example.ekpkdisnaker.table.Pengumuman;
import com.example.ekpkdisnaker.table.ak1;

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
    @POST("updateToken")
    Call<BaseResponse> updateToken(
            @Field("token") String token
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

    @FormUrlEncoded
    @POST("uploadPasFoto")
    Call<BaseResponse> uploadPasFoto(
            @Field("foto") String foto
    );

    @FormUrlEncoded
    @POST("uploadKTP")
    Call<BaseResponse> uploadKTP(
            @Field("foto") String foto
    );

    @FormUrlEncoded
    @POST("uploadIjazah")
    Call<BaseResponse> uploadIjazah(
            @Field("ijazah") String ijazah
    );

    @GET("getKartuAK1")
    Call<BaseResponse> getKartuAK1();

    @GET("getDataAK1")
    Call<BaseResponse<ak1>> getDataAK1();

    @GET("getPengumuman")
    Call<BaseResponse<Pengumuman>> getPengumuman();
}
