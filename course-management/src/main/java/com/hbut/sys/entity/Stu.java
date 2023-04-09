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
public class Stu implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sno;

    private String sname;

    private String ssex;

    private String sdept;

    private String sclass;

    private String sassign;

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }
    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getSsex() {
        return ssex;
    }

    public void setSsex(String ssex) {
        this.ssex = ssex;
    }
    public String getSdept() {
        return sdept;
    }

    public void setSdept(String sdept) {
        this.sdept = sdept;
    }
    public String getSclass() {
        return sclass;
    }

    public void setSclass(String sclass) {
        this.sclass = sclass;
    }
    public String getSassign() {
        return sassign;
    }

    public void setSassign(String sassign) {
        this.sassign = sassign;
    }

    @Override
    public String toString() {
        return "Stu{" +
            "sno=" + sno +
            ", sname=" + sname +
            ", ssex=" + ssex +
            ", sdept=" + sdept +
            ", sclass=" + sclass +
            ", sassign=" + sassign +
        "}";
    }
}
