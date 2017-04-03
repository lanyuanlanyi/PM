package work3_30;

/**
 * Created by 85168 on 2017/3/30.
 * 顺序存储结构
 * 合理组织数据，提高程序运行的空间效率和时间效率
 * 数组时信息的载体数据包括数值，字符，图片，声音
 * 数据元素相当于JAVA中的一个对象
 * 数据元素由数据项组成
 * 数据项相当于对象的属性
 * 数据项是对数据元素的集合相当于JVAa中的集合
 *
 * 数据结构分为逻辑结构和物理结构
 * 逻辑结构可分为4种
 * 1.集合，数据元素属于同一个集合外没有任何其他关系
 * 2.线性表1对1
 * 3.树1对多
 * 4。图，多对多
 *
 * 物理存储结构-
 * 顺序的
 * 链式的
 */
public class myarraylist {

    private Object[] obj;
    private int size;

    public  myarraylist(){

        obj=null;
        size=0;
    }
    //添加元素-尾部
    public void add(Object data){

        if (obj==null){

            obj=new Object[1];
            obj[0]=data;
            size++;
        }else {

            Object[] newObj=new Object[size+1];
            System.arraycopy(obj,0,newObj,0,size);
            newObj[size]=data;
            obj=newObj;
            size++;
        }
    }
    //获取长度
    public int size(){
        return size;
    }
    public String toString(){

        StringBuilder sb=new StringBuilder("[");
        if (size>0){
            for (Object o:obj){

                sb.append(o.toString()+",");
                sb.append("]");
            }
            //sb.append("]");
        }

        return sb.toString();
    }
    //指定位置添加
    public void add(int index,Object data){
        if (index<0 || index>size){
            throw  new ArrayIndexOutOfBoundsException();
        }else {

            Object[]newObj=new Object[size+1];
            System.arraycopy(obj,0,newObj,0,index);
            newObj[index]=data;
            System.arraycopy(obj,index,newObj,index+1,size-index);
            obj=newObj;
            size++;
        }
    }
    //删除指定位置
    public void remove(int index){

        if (index<0 || index>size){
            throw  new ArrayIndexOutOfBoundsException();
        }else {
            Object[]newObj=new Object[size-1];
            System.arraycopy(obj,0,newObj,0,index);
            System.arraycopy(obj,index+1,newObj,index,size-index-1);
            obj=newObj;
            size--;
        }

    }
    //获取指定位置元素
    public Object get(int index){
        if (index<0 || index>size){
            throw  new ArrayIndexOutOfBoundsException();
        }

        return obj[index];
    }
    //删除指定元素
    public boolean remove(Object data) {

        int index = -1;
        for (Object o:obj){
            index++;
            if (o != null || o.equals(data)) {

                remove(index);
                return true;
            }
        }

        return false;
    }
   // 修改指定位置元素
    public void set(int index,Object data){
        if (index<0 || index>size){
            throw  new ArrayIndexOutOfBoundsException();
        }


    }

    public static void main(String[] args) {

        myarraylist al=new myarraylist();
        al.add("java01");
        al.add("java02");
        al.add(1,"data");
        al.remove(1);
        System.out.println(al.size());
        System.out.println(al);
    }



}
