package com.example.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Desa {

    @SerializedName("id_kel")
    @Expose
    private String idKel;
    @SerializedName("id_kec")
    @Expose
    private String idKec;
    @SerializedName("nama")
    @Expose
    private String nama;
    @SerializedName("id_jenis")
    @Expose
    private Integer idJenis;

    public String getIdKel() {
        return idKel;
    }

    public void setIdKel(String idKel) {
        this.idKel = idKel;
    }

    public String getIdKec() {
        return idKec;
    }

    public void setIdKec(String idKec) {
        this.idKec = idKec;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Integer getIdJenis() {
        return idJenis;
    }

    public void setIdJenis(Integer idJenis) {
        this.idJenis = idJenis;
    }

}
