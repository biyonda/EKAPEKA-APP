package com.example.ekpkdisnaker.api;

import com.example.ekpkdisnaker.response.BaseResponse;
import com.example.ekpkdisnaker.response.RegisterResponse;
import com.example.ekpkdisnaker.response.UserResponse;
import com.example.ekpkdisnaker.table.Desa;
import com.example.ekpkdisnaker.table.Kecamatan;
import com.example.ekpkdisnaker.table.Keterampilan;
import com.example.ekpkdisnaker.table.Pengumuman;
import com.example.ekpkdisnaker.table.PopupProfil;
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
            @Field("jurusan") String jurusan,
            @Field("alamat") String alamat,
            @Field("kecamatan") String kecamatan,
            @Field("deskel") String desa,
            @Field("telepon") String telepon,
            @Field("email") String email,
            @Field("password") String password,
            @Field("agama") String agama,
            @Field("tahun_lulus") String tahun_lulus
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

    @FormUrlEncoded
    @POST("updatePenempatan")
    Call<BaseResponse> updatePenempatan(
            @Field("no_register") String no_register,
            @Field("tmp_penempatan") String tmp_penempatan,
            @Field("tgl_penempatan") String tgl_penempatan,
            @Field("file_penempatan") String file_penempatan
    );

    @GET("getKartuAK1")
    Call<BaseResponse> getKartuAK1();

    @GET("getDataAK1")
    Call<BaseResponse<ak1>> getDataAK1();

    @GET("getPengumuman")
    Call<BaseResponse<Pengumuman>> getPengumuman();

    @GET("getKeterampilan")
    Call<BaseResponse<Keterampilan>> getKeterampilan();

    @FormUrlEncoded
    @POST("tambahKeterampilan")
    Call<BaseResponse> tambahKeterampilan(
            @Field("keterangan") String keterangan,
            @Field("tahun") String tahun
    );

    @FormUrlEncoded
    @POST("hapusKeterampilan")
    Call<BaseResponse> hapusKeterampilan(
            @Field("id") String id
    );

    @FormUrlEncoded
    @POST("ubahProfile")
    Call<BaseResponse> ubahProfile(
            @Field("nama_lengkap") String nama_lengkap,
            @Field("tmp_lahir") String tmp_lahir,
            @Field("jns_kelamin") String jns_kelamin,
            @Field("tgl_lahir") String tgl_lahir,
            @Field("sts_nikah") String sts_nikah,
            @Field("kd_pendidikan") String kd_pendidikan,
            @Field("nama_pendidikan") String nama_pendidikan,
            @Field("jurusan") String jurusan,
            @Field("alamat") String alamat,
            @Field("kecamatan") String kecamatan,
            @Field("deskel") String desa,
            @Field("telepon") String telepon,
            @Field("email") String email,
            @Field("password") String password,
            @Field("agama") String agama,
            @Field("tahun_lulus") String tahun_lulus
    );

    @FormUrlEncoded
    @POST("getDesa")
    Call<BaseResponse<Desa>> getDesa(
            @Field("id_kec") String id_kec
    );

    @GET("getKecamatan")
    Call<BaseResponse<Kecamatan>> getKecamatan();

    @FormUrlEncoded
    @POST("getStatusAK1")
    Call<BaseResponse> getStatusAK1(
            @Field("nik") String nik
    );

    @FormUrlEncoded
    @POST("updateLapor")
    Call<BaseResponse> updateLapor(
            @Field("no_register") String no_register
    );

    @FormUrlEncoded
    @POST("getKartuPeserta")
    Call<BaseResponse<PopupProfil>> getKartuPeserta(
            @Field("nik") String nik
    );

}
