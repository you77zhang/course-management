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
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;

    private String tno;

    private String tname;

    private String tsex;

    private String tdept;

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }
    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }
    public String getTsex() {
        return tsex;
    }

    public void setTsex(String tsex) {
        this.tsex = tsex;
    }
    public String getTdept() {
        return tdept;
    }

    public void setTdept(String tdept) {
        this.tdept = tdept;
    }

    @Override
    public String toString() {
        return "Teacher{" +
            "tno=" + tno +
            ", tname=" + tname +
            ", tsex=" + tsex +
            ", tdept=" + tdept +
        "}";
    }
}
