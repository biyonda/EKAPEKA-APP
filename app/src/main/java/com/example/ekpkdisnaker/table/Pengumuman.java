package com.example.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pengumuman {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("jenis_pengumuman")
    @Expose
    private Integer jenisPengumuman;
    @SerializedName("nama_pengumuman")
    @Expose
    private String namaPengumuman;
    @SerializedName("uraian_pengumuman")
    @Expose
    private String uraianPengumuman;
    @SerializedName("link_pengumuman")
    @Expose
    private String linkPengumuman;
    @SerializedName("file_pengumuman")
    @Expose
    private String filePengumuman;
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

    public Integer getJenisPengumuman() {
        return jenisPengumuman;
    }

    public void setJenisPengumuman(Integer jenisPengumuman) {
        this.jenisPengumuman = jenisPengumuman;
    }

    public String getNamaPengumuman() {
        return namaPengumuman;
    }

    public void setNamaPengumuman(String namaPengumuman) {
        this.namaPengumuman = namaPengumuman;
    }

    public String getUraianPengumuman() {
        return uraianPengumuman;
    }

    public void setUraianPengumuman(String uraianPengumuman) {
        this.uraianPengumuman = uraianPengumuman;
    }

    public String getLinkPengumuman() {
        return linkPengumuman;
    }

    public void setLinkPengumuman(String linkPengumuman) {
        this.linkPengumuman = linkPengumuman;
    }

    public String getFilePengumuman() {
        return filePengumuman;
    }

    public void setFilePengumuman(String filePengumuman) {
        this.filePengumuman = filePengumuman;
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
