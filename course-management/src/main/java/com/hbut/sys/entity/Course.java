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
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;

    private String cno;

    private String cTno;

    private Double credit;

    private String cname;

    private String cterm;

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }
    public String getcTno() {
        return cTno;
    }

    public void setcTno(String cTno) {
        this.cTno = cTno;
    }
    public Double getCredit() {
        return credit;
    }

    public void setCredit(Double credit) {
        this.credit = credit;
    }
    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
    public String getCterm() {
        return cterm;
    }

    public void setCterm(String cterm) {
        this.cterm = cterm;
    }

    @Override
    public String toString() {
        return "Course{" +
            "cno=" + cno +
            ", cTno=" + cTno +
            ", credit=" + credit +
            ", cname=" + cname +
            ", cterm=" + cterm +
        "}";
    }
}
