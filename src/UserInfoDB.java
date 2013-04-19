import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: 23-rd
 * Date: 19.04.13
 * Time: 11:07
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = ("UserInfo"))
public class UserInfoDB
{
    @Id @GeneratedValue (strategy = GenerationType.SEQUENCE)
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
