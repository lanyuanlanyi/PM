package tools;

import JDBC.Person;
import windows.MainView;
import windows.PlusView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by 85168 on 2017/3/25.
 */
public class ModifyModel {


    //ȡ��model����-����al
    public ArrayList<Person> readModel(DefaultTableModel model){

        ArrayList<Person> al=new ArrayList<Person>();

        //model.getRowCount();

        //System.out.println(model.getRowCount());

        for (int i = 0; i <model.getRowCount() ; i++) {
            int id = Integer.parseInt(String.valueOf(model.getValueAt(i, 0)));
            String name = String.valueOf(model.getValueAt(i, 1));
            String sex = String.valueOf(model.getValueAt(i, 2));
            String dep = String.valueOf(model.getValueAt(i, 3));
            int sal = Integer.parseInt(String.valueOf(model.getValueAt(i, 4)));

            Person person = new Person(id, name, sex, dep, sal);

            al.add(person);
        }


        //System.out.println(al);

        return al;
    }
//modelд��һ��Person
    public void writeModel(Person p,DefaultTableModel model){

        String []data={String.valueOf(p.getId()),p.getName(),
                p.getSex(),p.getDep(),String.valueOf(p.getSal())};
        model.addRow(data);
    }





    //��alд��model����
    public void writeModel(ArrayList<Person> al,DefaultTableModel model){

        model.setRowCount(0);
        for (int i = 0; i <al.size() ; i++) {

            Person p=al.get(i);
            String []data={String.valueOf(p.getId()),p.getName(),p.getSex(),p.getDep(),String.valueOf(p.getSal())};
            model.addRow(data);
        }


    }
    //��alд��model����----��д����
    public void WriteModel(ArrayList<Person> al,DefaultTableModel model){

        model.setRowCount(0);
        for (int i = al.size()-1; i >=0 ; i--) {

            Person p=al.get(i);
            String []data={String.valueOf(p.getId()),p.getName(),p.getSex(),p.getDep(),String.valueOf(p.getSal())};
            model.addRow(data);
        }


    }

    //���PV��
    public void cleanPvField(PlusView pv){

        pv.tf1.setText("");
        pv.jcb3.setSelectedIndex(0);
        pv.jcb4.setSelectedIndex(0);
        pv.tf2.setText("");
        pv.tf5.setText("");

    }
    //���ѡ�е�����-������
    public Person getSelectedPreson(JTable table,DefaultTableModel model){

        //Person person=new Person();
        int i=table.getSelectedRow();

            int id = Integer.parseInt(String.valueOf(model.getValueAt(i, 0)));
            String name = String.valueOf(model.getValueAt(i, 1));
            String sex = String.valueOf(model.getValueAt(i, 2));
            String dep = String.valueOf(model.getValueAt(i, 3));
            int sal = Integer.parseInt(String.valueOf(model.getValueAt(i, 4)));

            Person person = new Person(id, name, sex, dep, sal);

        return person;
    }
//����ID
    public int getSelectedId(JTable table,DefaultTableModel model){

        int i=table.getSelectedRow();
        int id = Integer.parseInt(String.valueOf(model.getValueAt(i, 0)));

        return id;
    }




//���ѡ�е�����-�����˵ļ���
    public ArrayList<Person> getSelectedPresons(JTable table,DefaultTableModel model){

        ArrayList<Person>al=new ArrayList<Person>();
        int []arr=table.getSelectedRows();

        for (int i = 0; i <arr.length ; i++) {

            int id = Integer.parseInt(String.valueOf(model.getValueAt(arr[i], 0)));
            String name = String.valueOf(model.getValueAt(arr[i], 1));
            String sex = String.valueOf(model.getValueAt(arr[i], 2));
            String dep = String.valueOf(model.getValueAt(arr[i], 3));
            int sal = Integer.parseInt(String.valueOf(model.getValueAt(arr[i], 4)));

            Person person = new Person(id, name, sex, dep, sal);

            al.add(person);
        }
        //System.out.println(al);

        return al;
    }
    //�����˵ļ���
    public ArrayList<Person> getTablePresons(JTable table,DefaultTableModel model) {

        ArrayList<Person> al = new ArrayList<Person>();

        int m = table.getRowCount();
        int[] arr=new int[m];
        for (int i = 0; i <m ; i++)      arr[i]=i;



        for (int i = 0; i < arr.length; i++) {

            int id = Integer.parseInt(String.valueOf(model.getValueAt(arr[i], 0)));
            String name = String.valueOf(model.getValueAt(arr[i], 1));
            String sex = String.valueOf(model.getValueAt(arr[i], 2));
            String dep = String.valueOf(model.getValueAt(arr[i], 3));
            int sal = Integer.parseInt(String.valueOf(model.getValueAt(arr[i], 4)));

            Person person = new Person(id, name, sex, dep, sal);

            al.add(person);
        }
        //System.out.println(al);

        return al;
    }





//�����ڴ���ֵ��pv����
    public void mainToPlus(Point point,JTable table,PlusView pv){

        int m=table.rowAtPoint(point);
        int n=table.columnAtPoint(point);
        //String str=String.valueOf(table.getValueAt(m, n));

        String sex=String.valueOf(table.getValueAt(m, 2));
        String dep=String.valueOf(table.getValueAt(m, 3));

        int x=0;int y=0;
        if ("��".equals(sex))    x=1;
        else    if ("Ů".equals(sex))    x=2;
        else    if ("".equals(sex))    x=0;

        if ("".equals(dep))    y=0;
        else    if ("����".equals(dep))    y=1;
        else    if ("�쵼".equals(dep))    y=2;
        else    if ("����".equals(dep))    y=3;



        pv.tf1.setText(String.valueOf(table.getValueAt(m, 0)));
        pv.tf2.setText(String.valueOf(table.getValueAt(m, 1)));
        pv.jcb3.setSelectedIndex(x);
        pv.jcb4.setSelectedIndex(y);
        pv.tf5.setText(String.valueOf(table.getValueAt(m, 4)));

    }


}
