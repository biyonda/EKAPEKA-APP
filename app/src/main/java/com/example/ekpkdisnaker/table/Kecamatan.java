package com.example.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Kecamatan {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_kab")
    @Expose
    private String idKab;
    @SerializedName("nama")
    @Expose
    private String nama;

    public String getIdKec() {
        return id;
    }

    public void setIdKec(String id) {
        this.id = id;
    }

    public String getIdKab() {
        return idKab;
    }

    public void setIdKab(String idKab) {
        this.idKab = idKab;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
