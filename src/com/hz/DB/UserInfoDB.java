package com.hz.DB;

import com.sun.jndi.toolkit.url.Uri;

import javax.persistence.*;
import java.awt.*;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Table(name = ("UserInfo"))
public class UserInfoDB
{
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
    private int id;
    @Basic
    @Column (name = "login")
    private String login;
    @Column (name = "password")
    private String password;
    @Column
    private String state;
    @Column
    private Date date;
    @Column
    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UserInfoDB() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String isState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
