package com.example.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InformasiPeserta {

    @SerializedName("nik")
    @Expose
    private String nik;
    @SerializedName("nama_lengkap")
    @Expose
    private String namaLengkap;
    @SerializedName("tmp_lahir")
    @Expose
    private String tmpLahir;
    @SerializedName("tgl_lahir")
    @Expose
    private String tglLahir;
    @SerializedName("jns_kelamin")
    @Expose
    private String jnsKelamin;
    @SerializedName("agama")
    @Expose
    private String agama;
    @SerializedName("sts_nikah")
    @Expose
    private String stsNikah;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("kd_pendidikan")
    @Expose
    private String kdPendidikan;
    @SerializedName("nama_pendidikan")
    @Expose
    private String namaPendidikan;
    @SerializedName("pas_foto")
    @Expose
    private String pasFoto;
    @SerializedName("file_ijazah")
    @Expose
    private String fileIjazah;
    @SerializedName("foto_ktp")
    @Expose
    private String fotoKtp;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getTmpLahir() {
        return tmpLahir;
    }

    public void setTmpLahir(String tmpLahir) {
        this.tmpLahir = tmpLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getJnsKelamin() {
        return jnsKelamin;
    }

    public void setJnsKelamin(String jnsKelamin) {
        this.jnsKelamin = jnsKelamin;
    }

    public String getAgama() {
        return agama;
    }

    public void setAgama(String agama) {
        this.agama = agama;
    }

    public String getStsNikah() {
        return stsNikah;
    }

    public void setStsNikah(String stsNikah) {
        this.stsNikah = stsNikah;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKdPendidikan() {
        return kdPendidikan;
    }

    public void setKdPendidikan(String kdPendidikan) {
        this.kdPendidikan = kdPendidikan;
    }

    public String getNamaPendidikan() {
        return namaPendidikan;
    }

    public void setNamaPendidikan(String namaPendidikan) {
        this.namaPendidikan = namaPendidikan;
    }

    public String getPasFoto() {
        return pasFoto;
    }

    public void setPasFoto(String pasFoto) {
        this.pasFoto = pasFoto;
    }

    public String getFileIjazah() {
        return fileIjazah;
    }

    public void setFileIjazah(String fileIjazah) {
        this.fileIjazah = fileIjazah;
    }

    public String getFotoKtp() {
        return fotoKtp;
    }

    public void setFotoKtp(String fotoKtp) {
        this.fotoKtp = fotoKtp;
    }

}
