package com.diskominfo.ekpkdisnaker.table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KelengkapanData {

    @SerializedName("section_1")
    @Expose
    private Integer section1;
    @SerializedName("prosentase_section_1")
    @Expose
    private Integer prosentaseSection1;
    @SerializedName("section_2")
    @Expose
    private Integer section2;
    @SerializedName("prosentase_section_2")
    @Expose
    private Integer prosentaseSection2;
    @SerializedName("section_3")
    @Expose
    private Integer section3;
    @SerializedName("prosentase_section_3")
    @Expose
    private Integer prosentaseSection3;
    @SerializedName("section_4")
    @Expose
    private Integer section4;
    @SerializedName("prosentase_section_4")
    @Expose
    private Integer prosentaseSection4;
    @SerializedName("section_8")
    @Expose
    private Integer section8;
    @SerializedName("prosentase_section_8")
    @Expose
    private Integer prosentaseSection8;
    @SerializedName("prosentase_total")
    @Expose
    private Integer prosentaseTotal;

    public Integer getSection1() {
        return section1;
    }

    public void setSection1(Integer section1) {
        this.section1 = section1;
    }

    public Integer getProsentaseSection1() {
        return prosentaseSection1;
    }

    public void setProsentaseSection1(Integer prosentaseSection1) {
        this.prosentaseSection1 = prosentaseSection1;
    }

    public Integer getSection2() {
        return section2;
    }

    public void setSection2(Integer section2) {
        this.section2 = section2;
    }

    public Integer getProsentaseSection2() {
        return prosentaseSection2;
    }

    public void setProsentaseSection2(Integer prosentaseSection2) {
        this.prosentaseSection2 = prosentaseSection2;
    }

    public Integer getSection3() {
        return section3;
    }

    public void setSection3(Integer section3) {
        this.section3 = section3;
    }

    public Integer getProsentaseSection3() {
        return prosentaseSection3;
    }

    public void setProsentaseSection3(Integer prosentaseSection3) {
        this.prosentaseSection3 = prosentaseSection3;
    }

    public Integer getSection4() {
        return section4;
    }

    public void setSection4(Integer section4) {
        this.section4 = section4;
    }

    public Integer getProsentaseSection4() {
        return prosentaseSection4;
    }

    public void setProsentaseSection4(Integer prosentaseSection4) {
        this.prosentaseSection4 = prosentaseSection4;
    }

    public Integer getSection8() {
        return section8;
    }

    public void setSection8(Integer section8) {
        this.section8 = section8;
    }

    public Integer getProsentaseSection8() {
        return prosentaseSection8;
    }

    public void setProsentaseSection8(Integer prosentaseSection8) {
        this.prosentaseSection8 = prosentaseSection8;
    }

    public Integer getProsentaseTotal() {
        return prosentaseTotal;
    }

    public void setProsentaseTotal(Integer prosentaseTotal) {
        this.prosentaseTotal = prosentaseTotal;
    }

}
