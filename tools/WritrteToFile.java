package tools;


import JDBC.Person;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by 85168 on 2017/3/27.
 */
public class WritrteToFile {


 //将al写入到file
    public void writeToSQL(File file, ArrayList<Person> al) throws TransformerException, ParserConfigurationException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.newDocument();
        Element root = document.createElement("personroom");

        for(Person p:al) {
            Element person = document.createElement("person");

            person.setAttribute("person", "company-COM");//属性名，属性值


            Element id = document.createElement("id");
            id.setTextContent(String.valueOf(p.getId()));
            Element name = document.createElement("name");
            name.setTextContent(p.getName());
            Element sex = document.createElement("sex");
            sex.setTextContent(p.getSex());
            Element dep = document.createElement("dep");
            dep.setTextContent(p.getDep());
            Element sal = document.createElement("sal");
            sal.setTextContent(String.valueOf(p.getSal()));


            root.appendChild(person);
            //student的子bq

            person.appendChild(id);
            person.appendChild(name);
            person.appendChild(sex);
            person.appendChild(dep);
            person.appendChild(sal);
        }
        document.appendChild(root);

        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer former = tf.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(file);
        former.transform(source, result);
    }




}
