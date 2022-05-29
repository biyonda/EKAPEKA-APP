package com.example.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ak1 {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nik_user")
    @Expose
    private String nikUser;
    @SerializedName("no_register")
    @Expose
    private String noRegister;
    @SerializedName("masa_berlaku")
    @Expose
    private String masaBerlaku;
    @SerializedName("sts_ak1")
    @Expose
    private Integer stsAk1;
    @SerializedName("tgl_laporan_1")
    @Expose
    private String tglLaporan1;
    @SerializedName("id_petugas_lapor_1")
    @Expose
    private String idPetugasLapor1;
    @SerializedName("tgl_laporan_2")
    @Expose
    private String tglLaporan2;
    @SerializedName("id_petugas_lapor_2")
    @Expose
    private String idPetugasLapor2;
    @SerializedName("tgl_laporan_3")
    @Expose
    private String tglLaporan3;
    @SerializedName("id_petugas_lapor_3")
    @Expose
    private String idPetugasLapor3;
    @SerializedName("tmp_penempatan")
    @Expose
    private String tmpPenempatan;
    @SerializedName("tgl_penempatan")
    @Expose
    private String tglPenempatan;
    @SerializedName("informasi_peserta")
    @Expose
    private String informasiPeserta;
    @SerializedName("file_ak1")
    @Expose
    private String fileAk1;
    @SerializedName("verified_by")
    @Expose
    private String verifiedBy;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("peserta")
    @Expose
    private Peserta peserta;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNikUser() {
        return nikUser;
    }

    public void setNikUser(String nikUser) {
        this.nikUser = nikUser;
    }

    public String getNoRegister() {
        return noRegister;
    }

    public void setNoRegister(String noRegister) {
        this.noRegister = noRegister;
    }

    public String getMasaBerlaku() {
        return masaBerlaku;
    }

    public void setMasaBerlaku(String masaBerlaku) {
        this.masaBerlaku = masaBerlaku;
    }

    public Integer getStsAk1() {
        return stsAk1;
    }

    public void setStsAk1(Integer stsAk1) {
        this.stsAk1 = stsAk1;
    }

    public String getTglLaporan1() {
        return tglLaporan1;
    }

    public void setTglLaporan1(String tglLaporan1) {
        this.tglLaporan1 = tglLaporan1;
    }

    public String getIdPetugasLapor1() {
        return idPetugasLapor1;
    }

    public void setIdPetugasLapor1(String idPetugasLapor1) {
        this.idPetugasLapor1 = idPetugasLapor1;
    }

    public String getTglLaporan2() {
        return tglLaporan2;
    }

    public void setTglLaporan2(String tglLaporan2) {
        this.tglLaporan2 = tglLaporan2;
    }

    public String getIdPetugasLapor2() {
        return idPetugasLapor2;
    }

    public void setIdPetugasLapor2(String idPetugasLapor2) {
        this.idPetugasLapor2 = idPetugasLapor2;
    }

    public String getTglLaporan3() {
        return tglLaporan3;
    }

    public void setTglLaporan3(String tglLaporan3) {
        this.tglLaporan3 = tglLaporan3;
    }

    public String getIdPetugasLapor3() {
        return idPetugasLapor3;
    }

    public void setIdPetugasLapor3(String idPetugasLapor3) {
        this.idPetugasLapor3 = idPetugasLapor3;
    }

    public String getTmpPenempatan() {
        return tmpPenempatan;
    }

    public void setTmpPenempatan(String tmpPenempatan) {
        this.tmpPenempatan = tmpPenempatan;
    }

    public String getTglPenempatan() {
        return tglPenempatan;
    }

    public void setTglPenempatan(String tglPenempatan) {
        this.tglPenempatan = tglPenempatan;
    }

    public String getInformasiPeserta() {
        return informasiPeserta;
    }

    public void setInformasiPeserta(String informasiPeserta) {
        this.informasiPeserta = informasiPeserta;
    }

    public String getFileAk1() {
        return fileAk1;
    }

    public void setFileAk1(String fileAk1) {
        this.fileAk1 = fileAk1;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
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

    public Peserta getPeserta() {
        return peserta;
    }

    public void setPeserta(Peserta peserta) {
        this.peserta = peserta;
    }

}
