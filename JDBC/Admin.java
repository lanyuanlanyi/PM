package JDBC;

/**
 * Created by 85168 on 2017/3/28.
 */
public class Admin {

    private String id;
    private String pass;


    public Admin(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public String getPass() {
        return pass;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
