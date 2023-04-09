package com.hbut.sys.entity.tempList;

import java.io.Serializable;

public class TeachC implements Serializable {
    private static final long serialVersionUID = 1L;

    private String tno;
    private String tname;
    private String cno;
    private String cname;


    public TeachC() {
    }

    public TeachC(String tno, String tname, String cno, String cname) {
        this.tno = tno;
        this.tname = tname;
        this.cno = cno;
        this.cname = cname;
    }

    /**
     * 获取
     * @return tno
     */
    public String getTno() {
        return tno;
    }

    /**
     * 设置
     * @param tno
     */
    public void setTno(String tno) {
        this.tno = tno;
    }

    /**
     * 获取
     * @return tname
     */
    public String getTname() {
        return tname;
    }

    /**
     * 设置
     * @param tname
     */
    public void setTname(String tname) {
        this.tname = tname;
    }

    /**
     * 获取
     * @return cno
     */
    public String getCno() {
        return cno;
    }

    /**
     * 设置
     * @param cno
     */
    public void setCno(String cno) {
        this.cno = cno;
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

    public String toString() {
        return "TeachC{tno = " + tno + ", tname = " + tname + ", cno = " + cno + ", cname = " + cname + "}";
    }
}
