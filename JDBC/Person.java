package JDBC;

/**
 * Created by 85168 on 2017/3/23.
 */
public class Person {
    private int id;
    private String name;
    private String sex;
    private String dep;
    private int sal;


    public Person(int id, String name, String sex, String dep, int sal) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.dep = dep;
        this.sal = sal;
    }



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public void setSal(int sal) {
        this.sal = sal;
    }



    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSex() {
        return sex;
    }

    public String getDep() {
        return dep;
    }

    public int getSal() {
        return sal;
    }



    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", dep='" + dep + '\'' +
                ", sal=" + sal;
    }
}
