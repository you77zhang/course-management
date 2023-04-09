package com.hbut.sys.entity.tempList;

import java.io.Serializable;

public class SelectC implements Serializable {

    private static final long serialVersionUID = 1L;

    private String sno;
    private String sname;
    private String sclass;
    private Integer sgroup;
    private String cname;
    private Integer gscore;


    public SelectC() {
    }

    public SelectC(String sno, String sname, String sclass, Integer sgroup, String cname, Integer gscore) {
        this.sno = sno;
        this.sname = sname;
        this.sclass = sclass;
        this.sgroup = sgroup;
        this.cname = cname;
        this.gscore = gscore;
    }

    /**
     * 获取
     * @return sno
     */
    public String getSno() {
        return sno;
    }

    /**
     * 设置
     * @param sno
     */
    public void setSno(String sno) {
        this.sno = sno;
    }

    /**
     * 获取
     * @return sname
     */
    public String getSname() {
        return sname;
    }

    /**
     * 设置
     * @param sname
     */
    public void setSname(String sname) {
        this.sname = sname;
    }

    /**
     * 获取
     * @return sclass
     */
    public String getSclass() {
        return sclass;
    }

    /**
     * 设置
     * @param sclass
     */
    public void setSclass(String sclass) {
        this.sclass = sclass;
    }

    /**
     * 获取
     * @return sgroup
     */
    public Integer getSgroup() {
        return sgroup;
    }

    /**
     * 设置
     * @param sgroup
     */
    public void setSgroup(Integer sgroup) {
        this.sgroup = sgroup;
    }

    /**
     * 获取
     * @return cname
     */
    public String getCname() {
        return cname;
    }

    /**
     * 设置
     * @param cname
     */
    public void setCname(String cname) {
        this.cname = cname;
    }

    /**
     * 获取
     * @return gscore
     */
    public Integer getGscore() {
        return gscore;
    }

    /**
     * 设置
     * @param gscore
     */
    public void setGscore(Integer gscore) {
        this.gscore = gscore;
    }

    public String toString() {
        return "SelectC{sno = " + sno + ", sname = " + sname + ", sclass = " + sclass + ", sgroup = " + sgroup + ", cname = " + cname + ", gscore = " + gscore + "}";
    }
}
