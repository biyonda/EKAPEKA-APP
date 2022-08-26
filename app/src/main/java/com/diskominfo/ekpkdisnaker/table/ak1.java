package com.diskominfo.ekpkdisnaker.table;

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
    @SerializedName("periode_berlaku")
    @Expose
    private String periodeBerlaku;
    @SerializedName("sts_ak1")
    @Expose
    private Integer stsAk1;
    @SerializedName("sts_berlaku")
    @Expose
    private Integer stsBerlaku;
    @SerializedName("tgl_laporan_1")
    @Expose
    private String tglLaporan1;
    @SerializedName("nip_petugas_lapor_1")
    @Expose
    private String nipPetugasLapor1;
    @SerializedName("tgl_laporan_2")
    @Expose
    private String tglLaporan2;
    @SerializedName("nip_petugas_lapor_2")
    @Expose
    private String nipPetugasLapor2;
    @SerializedName("tgl_laporan_3")
    @Expose
    private String tglLaporan3;
    @SerializedName("nip_petugas_lapor_3")
    @Expose
    private String nipPetugasLapor3;
    @SerializedName("laporan_ke")
    @Expose
    private Integer laporanKe;
    @SerializedName("tmp_penempatan")
    @Expose
    private String tmpPenempatan;
    @SerializedName("tgl_penempatan")
    @Expose
    private String tglPenempatan;
    @SerializedName("file_penempatan")
    @Expose
    private String filePenempatan;
    @SerializedName("informasi_peserta")
    @Expose
    private InformasiPeserta informasiPeserta;
    @SerializedName("file_ak1")
    @Expose
    private String fileAk1;
    @SerializedName("file_temporary")
    @Expose
    private String fileTemporary;
    @SerializedName("verified_by")
    @Expose
    private String verifiedBy;
    @SerializedName("keterangan")
    @Expose
    private String keterangan;
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

    public String getPeriodeBerlaku() {
        return periodeBerlaku;
    }

    public void setPeriodeBerlaku(String periodeBerlaku) {
        this.periodeBerlaku = periodeBerlaku;
    }

    public Integer getStsAk1() {
        return stsAk1;
    }

    public void setStsAk1(Integer stsAk1) {
        this.stsAk1 = stsAk1;
    }

    public Integer getStsBerlaku() {
        return stsBerlaku;
    }

    public void setStsBerlaku(Integer stsBerlaku) {
        this.stsBerlaku = stsBerlaku;
    }

    public String getTglLaporan1() {
        return tglLaporan1;
    }

    public void setTglLaporan1(String tglLaporan1) {
        this.tglLaporan1 = tglLaporan1;
    }

    public String getNipPetugasLapor1() {
        return nipPetugasLapor1;
    }

    public void setNipPetugasLapor1(String nipPetugasLapor1) {
        this.nipPetugasLapor1 = nipPetugasLapor1;
    }

    public String getTglLaporan2() {
        return tglLaporan2;
    }

    public void setTglLaporan2(String tglLaporan2) {
        this.tglLaporan2 = tglLaporan2;
    }

    public String getNipPetugasLapor2() {
        return nipPetugasLapor2;
    }

    public void setNipPetugasLapor2(String nipPetugasLapor2) {
        this.nipPetugasLapor2 = nipPetugasLapor2;
    }

    public String getTglLaporan3() {
        return tglLaporan3;
    }

    public void setTglLaporan3(String tglLaporan3) {
        this.tglLaporan3 = tglLaporan3;
    }

    public String getNipPetugasLapor3() {
        return nipPetugasLapor3;
    }

    public void setNipPetugasLapor3(String nipPetugasLapor3) {
        this.nipPetugasLapor3 = nipPetugasLapor3;
    }

    public Integer getLaporanKe() {
        return laporanKe;
    }

    public void setLaporanKe(Integer laporanKe) {
        this.laporanKe = laporanKe;
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

    public String getFilePenempatan() {
        return filePenempatan;
    }

    public void setFilePenempatan(String filePenempatan) {
        this.filePenempatan = filePenempatan;
    }

    public InformasiPeserta getInformasiPeserta() {
        return informasiPeserta;
    }

    public void setInformasiPeserta(InformasiPeserta informasiPeserta) {
        this.informasiPeserta = informasiPeserta;
    }

    public String getFileAk1() {
        return fileAk1;
    }

    public void setFileAk1(String fileAk1) {
        this.fileAk1 = fileAk1;
    }

    public String getFileTemporary() {
        return fileTemporary;
    }

    public void setFileTemporary(String fileTemporary) {
        this.fileTemporary = fileTemporary;
    }

    public String getVerifiedBy() {
        return verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy) {
        this.verifiedBy = verifiedBy;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
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
