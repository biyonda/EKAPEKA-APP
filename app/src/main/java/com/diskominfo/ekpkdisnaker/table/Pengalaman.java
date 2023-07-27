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
    private String lama_kerja;
    @SerializedName("pemberi_kerja")
    @Expose
    private String pemberi_kerja;
    @SerializedName("uraian_tugas")
    @Expose
    private String uraian_tugas;

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
        return lama_kerja;
    }

    public void setLamaKerja(String lama_kerja) {
        this.lama_kerja = lama_kerja;
    }

    public String getPemberiKerja() {
        return pemberi_kerja;
    }

    public void setPemberiKerja(String pemberi_kerja) {
        this.pemberi_kerja = pemberi_kerja;
    }

    public String getUraianTugas() {
        return uraian_tugas;
    }

    public void setUraianTugas(String uraian_tugas) {
        this.uraian_tugas = uraian_tugas;
    }

}
