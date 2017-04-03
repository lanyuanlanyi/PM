package work3_30;

/**
 * Created by 85168 on 2017/3/30.
 */
public class MylinkedList {

    Node head;
    int size;
    public MylinkedList(){
        head=null;
        size=0;
    }
    //添加一个数据
    public void add(Object data){
        if (head==null){
            Node temp=new Node(data);
            head=temp;
            size++;

        }else {
            Node temp=head;
            while (temp.next!=null){
                temp=temp.next;
            }
            temp.next=new Node(data);
            size++;
        }

    }

    public String toString(){

        StringBuilder sb=new StringBuilder("[");
        if (size>0){

            Node temp=head;
            sb.append(temp.data.toString()+",");
            while (temp.next!=null) {
                temp=temp.next;
                sb.append(temp.data.toString()+",");
            }
            sb.deleteCharAt(sb.length()-1);
            }
            sb.append("]");


        return sb.toString();
    }

    //
    //头部添加
    public void addFirst(Object data){
        Node temp=new Node(data);
        temp.next=head;
        head=temp;
        size++;
    }

    //尾部添加
    public void addLast(Object data){
        add(data);
    }

    //指定位置添加
    public void add(int index,Object data){
        int i=-1;
        Node temp=head;
        i++;
        while (temp.next!=null){

            //i++;
            if (index==i){
                Node newNode=new Node(data);
                newNode.next=temp.next;
                temp.next=newNode;
                break;
            }
            temp=temp.next;
//            size++;

        }


    }
   // public Object
    public void removeFirst(){
        head=head.next;
        size--;
    }

    //移除指定位置的元素
    public void remove(int index){
        int i=0;
        Node temp=head;
        while (temp.next!=null){
            if (i==index) {
                getNode(index - 1).next = temp.next;
                size--;
                break;
            }
            temp=temp.next;
            i++;
        }
    }

    //获取指定节点的方法
    public Node getNode(int index){
        int i=0;
        Node temp=head;
        while (temp.next!=null){
            if (i==index){
                return temp;
            }
            temp=temp.next;
            i++;
        }
        return null;
    }

    //移除指定的元素
    public boolean remove(Object data){
        int i=0;
        Node temp=head;
        while (temp.next!=null){
            if (data.equals(temp.data)){
                getNode(i-1).next=temp.next;
                size--;
                return true;
            }
            temp=temp.next;
            i++;
        }
        return false;

    }


    public static void main(String[] args) {

        MylinkedList ll=new MylinkedList();

        ll.add("java0");
        ll.add("java1");
        ll.addFirst("java--00");
        ll.add(2,"___________");
        //ll.removeFirst();
        ll.remove(1);
        System.out.println(ll);


    }
}
//代表节点的类
class Node{

    Object data;
    Node next;

    public Node(Object data){
        this.data=data;
    }

}