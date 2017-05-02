package laba2;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by vladp on 13.03.2017.
 */
public class ReadXMLFile {
    public Date date;
    public void fillList(LinkedList<FoodResidus> list, String FILENAME){
        try {
            final File xmlFile = new File(FILENAME);
            BufferedInputStream b=new BufferedInputStream(new FileInputStream(xmlFile));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc=db.parse(b);

            doc.getDocumentElement().normalize();

            NodeList nodelist=doc.getElementsByTagName("theCollection");

            for(int i=0; i<nodelist.getLength(); i++){
                Node node=nodelist.item(i);
                if(Node.ELEMENT_NODE == node.getNodeType()){
                    Element element=(Element) node;
                    String name  = element.getElementsByTagName("name").item(0).getTextContent();
                    int weight = Integer.parseInt(element.getElementsByTagName("weight").item(0).getTextContent());
                    boolean fliesAttraction = (element.getElementsByTagName("fliesAttraction").item(0).getTextContent().equals("true"))? true : false;
                    list.add(new Whine(name, weight));
                }
            }
            date=new Date();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}