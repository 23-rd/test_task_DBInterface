package com.hz.DB;

import javax.persistence.*;

@Entity
@Table(name = ("UserInfo"))
public class UserInfoDB
{
    @Id @GeneratedValue (strategy = GenerationType.AUTO)
    private String id;
    @Column
    private String login;
    @Column
    private String password;
    @Column
    private boolean state;

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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
