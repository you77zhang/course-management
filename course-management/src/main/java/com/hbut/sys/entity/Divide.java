package com.hbut.sys.entity;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zyf
 * @since 2023-04-02
 */
public class Divide implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer gno;

    private String dCno;

    private String dSno;

    private Integer gscore;

    private String sclass;

    private Integer sgroup;

    public Integer getGno() {
        return gno;
    }

    public void setGno(Integer gno) {
        this.gno = gno;
    }
    public String getdCno() {
        return dCno;
    }

    public void setdCno(String dCno) {
        this.dCno = dCno;
    }
    public String getdSno() {
        return dSno;
    }

    public void setdSno(String dSno) {
        this.dSno = dSno;
    }
    public Integer getGscore() {
        return gscore;
    }

    public void setGscore(Integer gscore) {
        this.gscore = gscore;
    }
    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }
    public Integer getSgroup() {
        return sgroup;
    }

    public void setSgroup(Integer sgroup) {
        this.sgroup = sgroup;
    }

    @Override
    public String toString() {
        return "Divide{" +
            "gno=" + gno +
            ", dCno=" + dCno +
            ", dSno=" + dSno +
            ", gscore=" + gscore +
            ", sclass=" + sclass +
            ", sgroup=" + sgroup +
        "}";
    }
}
