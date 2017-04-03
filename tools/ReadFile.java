package tools;


import JDBC.Person;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 85168 on 2017/3/27.
 */
public class ReadFile {


//���ļ�д�뵽al
    public ArrayList<Person> readFromXML(File file) throws DocumentException {
        SAXReader reader=new SAXReader();
        Document doc=reader.read(file);

        Element root=doc.getRootElement();
        List<Person> personList=root.elements();

        String[]arr=new String[10];
        EditPerson ep=new EditPerson();

        ArrayList<Person>al=new ArrayList<>();

        for (int i=0;i<personList.size();i++){
            Element person=(Element)personList.get(i);
            String value=person.attributeValue("person");
            System.out.println(value);
            List<Element> childList=person.elements();
            for (int j = 0;j<childList.size();j++){
                Element child=childList.get(j);
                String text=child.getText();
                arr[j]=text;
                //System.out.println(text+"????"+j);
            }

        Person p=new Person(Integer.parseInt(arr[0]),arr[1],arr[2],arr[3],Integer.parseInt(arr[4]));
        al.add(p);
        }
        return al;
    }

    //��alд�����ݿ�
    public void writeToSql(ArrayList<Person> al){

        CheckPerson ck=new CheckPerson();
        EditPerson ep=new EditPerson();
        ArrayList bl=new ArrayList ();
        ArrayList<Person>cl=new ArrayList<Person>();

        for (int i = 0; i <al.size() ; i++) {

            if(ck.checkId(al.get(i)))      bl.add(i);
        }
        for (int i = 0; i <bl.size() ; i++) {

            int m=Integer.valueOf(String.valueOf(bl.get(i)));
            cl.add(al.get(m));

        }//�õ��غϵ�����
        for (int i = 0; i <cl.size() ; i++) {
            al.remove(cl.get(i));
        }


        if (bl.size()==0) {

            if (al.size()==0) {
                JOptionPane.showMessageDialog(null, "��д����ϢΪ�գ�д��ʧ��");
                return;
            }else {
                ep.addAll(al);
                JOptionPane.showMessageDialog(null, "�ɹ�д��" + al.size() + "����Ϣ�����ݿ�");
            }

        }else if (bl.size()!=0) {



            //��⵽�ظ�ID��Ϣ���Ƿ񸲸���Щ����
            int y = JOptionPane.showConfirmDialog(null, "��⵽���ݿ������ظ���Ϣ���Ƿ񸲸ǣ�");
            if (y==0){

                if ((al.size()+cl.size())==0) {
                    JOptionPane.showMessageDialog(null, "��д����ϢΪ�գ�д��ʧ��");
                    return;
                }else {
                    ep.addAll(al);
                    ep.deleteAll(cl);
                    ep.addAll(cl);
                    JOptionPane.showMessageDialog(null,"�ɹ�д��"+(al.size()+cl.size())+"����Ϣ�����ݿ�\n���и�����Ϣ"+cl.size()+"��");
                }

            }else if (y==1){
                ep.addAll(al);
                JOptionPane.showMessageDialog(null,"�ɹ�д��"+al.size()+"����Ϣ�����ݿ�");

            }else if (y==2){
                System.out.println("��ȡ��");
            }



        }

    }






}
