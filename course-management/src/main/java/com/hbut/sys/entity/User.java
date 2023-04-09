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
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
            "id=" + id +
            ", name=" + name +
            ", password=" + password +
        "}";
    }
}
