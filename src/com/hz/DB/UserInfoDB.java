package com.hz.DB;

import javax.persistence.*;

@Entity
@Table(name = ("UserInfo"))
public class UserInfoDB
{
    @Id @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "id", unique = true, nullable = false)
    private int id;
    @Column (name = "login")
    private String login;
    @Column
    private String password;
    @Column
    private String state;

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
