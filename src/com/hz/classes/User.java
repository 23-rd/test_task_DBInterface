package com.hz.classes;

/**
 * Created with IntelliJ IDEA.
 * com.hz.classes.User: 23-rd
 * Date: 19.04.13
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */
public class User
{
    private String login;
    private String password;
    private boolean state;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public User(String login, String password, boolean b)
    {
        this.login = login;
        this.password = password;
        this.state = b;
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
}
