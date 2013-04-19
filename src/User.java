/**
 * Created with IntelliJ IDEA.
 * User: 23-rd
 * Date: 19.04.13
 * Time: 11:24
 * To change this template use File | Settings | File Templates.
 */
public class User
{
    private String login;
    private String password;

    public User(String login, String password)
    {
        this.login = login;
        this.password = password;
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
