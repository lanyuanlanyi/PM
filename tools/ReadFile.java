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


//将文件写入到al
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

    //将al写入数据库
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

        }//得到重合的数组
        for (int i = 0; i <cl.size() ; i++) {
            al.remove(cl.get(i));
        }


        if (bl.size()==0) {

            if (al.size()==0) {
                JOptionPane.showMessageDialog(null, "待写入信息为空，写入失败");
                return;
            }else {
                ep.addAll(al);
                JOptionPane.showMessageDialog(null, "成功写入" + al.size() + "条信息到数据库");
            }

        }else if (bl.size()!=0) {



            //检测到重复ID信息，是否覆盖这些数据
            int y = JOptionPane.showConfirmDialog(null, "检测到数据库内有重复信息，是否覆盖？");
            if (y==0){

                if ((al.size()+cl.size())==0) {
                    JOptionPane.showMessageDialog(null, "待写入信息为空，写入失败");
                    return;
                }else {
                    ep.addAll(al);
                    ep.deleteAll(cl);
                    ep.addAll(cl);
                    JOptionPane.showMessageDialog(null,"成功写入"+(al.size()+cl.size())+"条信息到数据库\n其中覆盖信息"+cl.size()+"条");
                }

            }else if (y==1){
                ep.addAll(al);
                JOptionPane.showMessageDialog(null,"成功写入"+al.size()+"条信息到数据库");

            }else if (y==2){
                System.out.println("已取消");
            }



        }

    }






}
