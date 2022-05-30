package com.example.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keterampilan {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
    @SerializedName("tahun")
    @Expose
    private String tahun;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

}
