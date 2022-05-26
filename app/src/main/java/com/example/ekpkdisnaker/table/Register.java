package com.example.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("username")
    @Expose
    private String username;
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
    private Integer jnsKelamin;
    @SerializedName("agama")
    @Expose
    private String agama;
    @SerializedName("sts_nikah")
    @Expose
    private String stsNikah;
    @SerializedName("alamat")
    @Expose
    private String alamat;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("telepon")
    @Expose
    private String telepon;
    @SerializedName("kd_pendidikan")
    @Expose
    private String kdPendidikan;
    @SerializedName("nama_pendidikan")
    @Expose
    private String namaPendidikan;
    @SerializedName("pas_foto")
    @Expose
    private Object pasFoto;
    @SerializedName("foto_ktp")
    @Expose
    private Object fotoKtp;
    @SerializedName("file_ijazah")
    @Expose
    private Object fileIjazah;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("hak_akses")
    @Expose
    private String hakAkses;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public Integer getJnsKelamin() {
        return jnsKelamin;
    }

    public void setJnsKelamin(Integer jnsKelamin) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelepon() {
        return telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
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

    public Object getPasFoto() {
        return pasFoto;
    }

    public void setPasFoto(Object pasFoto) {
        this.pasFoto = pasFoto;
    }

    public Object getFotoKtp() {
        return fotoKtp;
    }

    public void setFotoKtp(Object fotoKtp) {
        this.fotoKtp = fotoKtp;
    }

    public Object getFileIjazah() {
        return fileIjazah;
    }

    public void setFileIjazah(Object fileIjazah) {
        this.fileIjazah = fileIjazah;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHakAkses() {
        return hakAkses;
    }

    public void setHakAkses(String hakAkses) {
        this.hakAkses = hakAkses;
    }

    public String getApiToken() {
        return apiToken;
    }

    public void setApiToken(String apiToken) {
        this.apiToken = apiToken;
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
