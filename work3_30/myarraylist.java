package work3_30;

/**
 * Created by 85168 on 2017/3/30.
 * ˳��洢�ṹ
 * ������֯���ݣ���߳������еĿռ�Ч�ʺ�ʱ��Ч��
 * ����ʱ��Ϣ���������ݰ�����ֵ���ַ���ͼƬ������
 * ����Ԫ���൱��JAVA�е�һ������
 * ����Ԫ�������������
 * �������൱�ڶ��������
 * �������Ƕ�����Ԫ�صļ����൱��JVAa�еļ���
 *
 * ���ݽṹ��Ϊ�߼��ṹ������ṹ
 * �߼��ṹ�ɷ�Ϊ4��
 * 1.���ϣ�����Ԫ������ͬһ��������û���κ�������ϵ
 * 2.���Ա�1��1
 * 3.��1�Զ�
 * 4��ͼ����Զ�
 *
 * ����洢�ṹ-
 * ˳���
 * ��ʽ��
 */
public class myarraylist {

    private Object[] obj;
    private int size;

    public  myarraylist(){

        obj=null;
        size=0;
    }
    //���Ԫ��-β��
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
    //��ȡ����
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
    //ָ��λ�����
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
    //ɾ��ָ��λ��
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
    //��ȡָ��λ��Ԫ��
    public Object get(int index){
        if (index<0 || index>size){
            throw  new ArrayIndexOutOfBoundsException();
        }

        return obj[index];
    }
    //ɾ��ָ��Ԫ��
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
   // �޸�ָ��λ��Ԫ��
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
