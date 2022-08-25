package com.example.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PopupProfil {

    @SerializedName("Nama")
    @Expose
    private String nama;
    @SerializedName("NIK")
    @Expose
    private String nik;
    @SerializedName("Foto")
    @Expose
    private String foto;
    @SerializedName("No.Register")
    @Expose
    private String noRegister;
    @SerializedName("Berlaku")
    @Expose
    private String berlaku;
    @SerializedName("Dibuat")
    @Expose
    private String dibuat;
    @SerializedName("Link")
    @Expose
    private String link;

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNoRegister() {
        return noRegister;
    }

    public void setNoRegister(String noRegister) {
        this.noRegister = noRegister;
    }

    public String getBerlaku() {
        return berlaku;
    }

    public void setBerlaku(String berlaku) {
        this.berlaku = berlaku;
    }

    public String getDibuat() {
        return dibuat;
    }

    public void setDibuat(String dibuat) {
        this.dibuat = dibuat;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

}
