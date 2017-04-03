package Homework3_30;

/**
 * Created by 郭 on 2017/3/30.
 *设计一个抽象数据类型：Class
 数据成员为 Student。
 数据操作：为基本方法。
 要求：能通过自己写的run方法测试基本方法。
 */
public class MyClass {

    Node head;
    int size;

    public MyClass(){
        head=null;
        size=0;
    }
    //添加数据
    public void add(Student student){

        if (head == null) {

            Node temp = new Node(student);
            head = temp;
            size++;
        } else {

            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = new Node(student);
            size++;
        }
    }

    //头部添加
    public void addFirst(Student student){

        Node temp=new Node(student);
        temp.next=head;
        head=temp;
        size++;
    }

    //指定位置添加

    //删除第一个学生
    public void removeFirst(){
        head = head.next;
        size--;
    }

    //指定位置删除
    public void remove(int index){

        if (index<0 || index>size){
            throw new ArrayIndexOutOfBoundsException();
        }else {
            int i=0;
            Node temp=head;
            while (temp.next!=null){
                if (i==index){
                    getNode(index-1).next=temp.next;
                    size--;
                    break;
                }
                temp=temp.next;
                i++;
            }
        }
    }

    //指定元素删除
    public boolean remove(Student student){

        int i=0;
        Node temp=head;
        while (temp.next!=null){
            if (student.isSame(temp.data)){

                getNode(i-1).next=temp.next;
                size--;
                return true;
            }
            temp=temp.next;
            i++;
        }
        return false;
    }

    //获得指定节点的方法
    public Node getNode(int index){
        int i=0;
        Node temp=head;
        while (temp.next!=null){
            if (i==index)   return temp;
            temp=temp.next;
            i++;
        }
        return null;
    }

    //toString
    public String toString(){

        StringBuilder sb=new StringBuilder("[");

        if (size>0){

            Node temp=head;
            sb.append(temp.data.toString() + ",");
            while (temp.next!=null){
                temp=temp.next;
                sb.append(temp.data.toString() + ",");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("]");
        return sb.toString();
    }

}

//学生类
class Student{

    private int id;
    private String name;
    private char sex;
    private int age;
    private double scores;

    public Student(int id, String name, char sex, int age, double scores) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.scores = scores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getScores() {
        return scores;
    }

    public void setScores(double scores) {
        this.scores = scores;
    }

    //测试是否相同的方法
    public boolean isSame(Student stu){

        boolean flag = false;
        boolean f1 = this.id == stu.id;
        boolean f2 = this.name == stu.name;
        boolean f3 = this.sex == stu.sex;
        boolean f4 = this.age == stu.age;
        boolean f5 = this.scores == stu.scores;

        flag = (f1 && f2 && f3 && f4 && f5);

        return flag;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", age=" + age +
                ", scores=" + scores +
                '}';
    }
}

//Node类
class Node{

    Student data;
    Node next;

    public Node(Student data){
        this.data=data;
    }
}

//测试类
class run{
    public static void main(String[] args) {

        MyClass mc = new MyClass();

        mc.add(new Student(8888,"李天一",'男',22,99));
        mc.add(new Student(1001,"张二",'男',25,89));
        System.out.println("add__________\n"+mc);

        mc.addFirst(new Student(1001,"李三",'男',24,94));
        System.out.println("addFirst__________\n"+mc);

        mc.remove(1);
        //mc.remove(9);
        System.out.println("remove__________\n"+mc);

        //测试equals
        Student student1 = new Student(1001, "李三", '男', 24, 94);
        Student student2 = new Student(1001, "李三", '男', 24, 94);
        System.out.println("===|"+student1.equals(student2));
        System.out.println("===|"+student1.isSame(student2));

        //指定元素删除
        mc.add(new Student(8888,"李天一",'男',22,99));
        System.out.println("|____"+mc);
        mc.remove(new Student(8888,"李天一",'男',22,99));
        System.out.println("|____"+mc);

    }
}
