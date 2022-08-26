package com.diskominfo.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Desa {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("id_kec")
    @Expose
    private Integer idKec;
    @SerializedName("nama")
    @Expose
    private String nama;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdKec() {
        return idKec;
    }

    public void setIdKec(Integer idKec) {
        this.idKec = idKec;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

}
