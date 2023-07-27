package com.diskominfo.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pengalaman {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("jabatan")
    @Expose
    private String jabatan;
    @SerializedName("lama_kerja")
    @Expose
    private String lamaKerja;
    @SerializedName("pemberi_kerja")
    @Expose
    private String pemberiKerja;
    @SerializedName("uraian_tugas")
    @Expose
    private String uraianTugas;
    @SerializedName("catatan")
    @Expose
    private Object catatan;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

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

    public String getJabatan() {
        return jabatan;
    }

    public void setJabatan(String jabatan) {
        this.jabatan = jabatan;
    }

    public String getLamaKerja() {
        return lamaKerja;
    }

    public void setLamaKerja(String lamaKerja) {
        this.lamaKerja = lamaKerja;
    }

    public String getPemberiKerja() {
        return pemberiKerja;
    }

    public void setPemberiKerja(String pemberiKerja) {
        this.pemberiKerja = pemberiKerja;
    }

    public String getUraianTugas() {
        return uraianTugas;
    }

    public void setUraianTugas(String uraianTugas) {
        this.uraianTugas = uraianTugas;
    }

    public Object getCatatan() {
        return catatan;
    }

    public void setCatatan(Object catatan) {
        this.catatan = catatan;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

}
