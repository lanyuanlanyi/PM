package work3_30;

/**
 * Created by 郭老师 on 2017/3/30.
 * 顺序存储结构
 */
public class MyStack {

    private Object[] obj;
    private int size;
    private Object top;

    public MyStack(){
        obj=new Object[0];
        size=0;
        top=null;
    }
    //入栈
    public void push(Object data){
        Object[]newObj=new Object[size+1];
        System.arraycopy(obj,0,newObj,0,size);
        top=data;
        newObj[size]=top;
        obj = newObj;
        size++;
    }
    //出栈
    public Object pop(){
        Object o = top;
        Object newObj[] = new Object[size - 1];
        System.arraycopy(obj,0,newObj,0,size-1);
        obj = newObj;
        size--;
        top=obj[size-1];
        return o;

    }
    //查看
    public Object peek(){
        return obj[size-1];
    }


    public static void main(String[] args) {

        MyStack stack = new MyStack();
        stack.push("java01");
        stack.push("java02");

        System.out.println(stack.pop());
        System.out.println(stack);

    }


}
