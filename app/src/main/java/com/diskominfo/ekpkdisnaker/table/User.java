package com.diskominfo.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

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
    @SerializedName("kecamatan")
    @Expose
    private String kecamatan;
    @SerializedName("deskel")
    @Expose
    private String deskel;
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
    @SerializedName("jurusan_pendidikan")
    @Expose
    private String jurusanPendidikan;
    @SerializedName("tahun_lulus")
    @Expose
    private String tahunLulus;
    @SerializedName("nilai_pendidikan")
    @Expose
    private String nilaiPendidikan;
    @SerializedName("bahasa_dikuasai")
    @Expose
    private String bahasaDikuasai;
    @SerializedName("jabatan_diinginkan")
    @Expose
    private String jabatanDiinginkan;
    @SerializedName("lokasi_penempatan")
    @Expose
    private String lokasiPenempatan;
    @SerializedName("lokasi_diinginkan")
    @Expose
    private String lokasiDiinginkan;
    @SerializedName("upah_diinginkan")
    @Expose
    private String upahDiinginkan;
    @SerializedName("pelatihan_diinginkan")
    @Expose
    private String pelatihanDiinginkan;
    @SerializedName("pas_foto")
    @Expose
    private String pasFoto;
    @SerializedName("foto_ktp")
    @Expose
    private String fotoKtp;
    @SerializedName("file_ijazah")
    @Expose
    private String fileIjazah;
    @SerializedName("password")
    @Expose
    private String password;
    @SerializedName("hak_akses")
    @Expose
    private String hakAkses;
    @SerializedName("api_token")
    @Expose
    private String apiToken;
    @SerializedName("firebase_token")
    @Expose
    private String firebaseToken;
    @SerializedName("register_via")
    @Expose
    private String registerVia;
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

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getDeskel() {
        return deskel;
    }

    public void setDeskel(String deskel) {
        this.deskel = deskel;
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

    public String getJurusanPendidikan() {
        return jurusanPendidikan;
    }

    public void setJurusanPendidikan(String jurusanPendidikan) {
        this.jurusanPendidikan = jurusanPendidikan;
    }

    public String getTahunLulus() {
        return tahunLulus;
    }

    public void setTahunLulus(String tahunLulus) {
        this.tahunLulus = tahunLulus;
    }

    public String getNilaiPendidikan() {
        return nilaiPendidikan;
    }

    public void setNilaiPendidikan(String nilaiPendidikan) {
        this.nilaiPendidikan = nilaiPendidikan;
    }

    public String getBahasaDikuasai() {
        return bahasaDikuasai;
    }

    public void setBahasaDikuasai(String bahasaDikuasai) {
        this.bahasaDikuasai = bahasaDikuasai;
    }

    public String getJabatanDiinginkan() {
        return jabatanDiinginkan;
    }

    public void setJabatanDiinginkan(String jabatanDiinginkan) {
        this.jabatanDiinginkan = jabatanDiinginkan;
    }

    public String getLokasiPenempatan() {
        return lokasiPenempatan;
    }

    public void setLokasiPenempatan(String lokasiPenempatan) {
        this.lokasiPenempatan = lokasiPenempatan;
    }

    public String getLokasiDiinginkan() {
        return lokasiDiinginkan;
    }

    public void setLokasiDiinginkan(String lokasiDiinginkan) {
        this.lokasiDiinginkan = lokasiDiinginkan;
    }

    public String getUpahDiinginkan() {
        return upahDiinginkan;
    }

    public void setUpahDiinginkan(String upahDiinginkan) {
        this.upahDiinginkan = upahDiinginkan;
    }

    public String getPelatihanDiinginkan() {
        return pelatihanDiinginkan;
    }

    public void setPelatihanDiinginkan(String pelatihanDiinginkan) {
        this.pelatihanDiinginkan = pelatihanDiinginkan;
    }

    public String getPasFoto() {
        return pasFoto;
    }

    public void setPasFoto(String pasFoto) {
        this.pasFoto = pasFoto;
    }

    public String getFotoKtp() {
        return fotoKtp;
    }

    public void setFotoKtp(String fotoKtp) {
        this.fotoKtp = fotoKtp;
    }

    public String getFileIjazah() {
        return fileIjazah;
    }

    public void setFileIjazah(String fileIjazah) {
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

    public String getFirebaseToken() {
        return firebaseToken;
    }

    public void setFirebaseToken(String firebaseToken) {
        this.firebaseToken = firebaseToken;
    }

    public String getRegisterVia() {
        return registerVia;
    }

    public void setRegisterVia(String registerVia) {
        this.registerVia = registerVia;
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
