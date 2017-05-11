package laba2;

import javafx.collections.ObservableList;

import javax.xml.bind.*;
import java.io.*;
import java.util.HashSet;

/**
 * Created by danil on 23.02.2017.
 */

public class XMLworker {

    public static void saveCollection(String path, HashSet hs)throws JAXBException{
        try{
            JAXBContext context = JAXBContext.newInstance(ClassWrapper.class);
            ClassWrapper cw = new ClassWrapper();
            cw.setTheCollection(hs);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File fileWrite = new File(path);
            FileWriter fw = new FileWriter(fileWrite);
            BufferedWriter bw = new BufferedWriter(fw);
            marshaller.marshal(cw, bw);
        }catch(IOException e){
            System.out.println("Введён не верный путь файла");
        }
    }

    public static void saveCollection(File fileWrite, HashSet hs)throws JAXBException{
        try{
            JAXBContext context = JAXBContext.newInstance(ClassWrapper.class);
            ClassWrapper cw = new ClassWrapper();
            cw.setTheCollection(hs);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileWriter fw = new FileWriter(fileWrite);
            BufferedWriter bw = new BufferedWriter(fw);
            marshaller.marshal(cw, bw);
        }catch(IOException e){
            System.out.println("Введён не верный путь файла");
        }
    }
    public static void saveCollection(PrintWriter printWriter, HashSet hs)throws JAXBException{
        try{
            JAXBContext context = JAXBContext.newInstance(ClassWrapper.class);
            ClassWrapper cw = new ClassWrapper();
            cw.setTheCollection(hs);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            BufferedWriter bw = new BufferedWriter(printWriter);
            marshaller.marshal(cw, bw);
        }catch(Exception e){
            System.out.println("Введён не верный путь файла");
        }
    }
    public static HashSet getCollection(String path)throws JAXBException{
        try{
            File fileRead = new File(path);
            FileReader fr = new FileReader(fileRead);
            BufferedReader br = new BufferedReader(fr);
            ClassWrapper returnedHS = JAXB.unmarshal(br, ClassWrapper.class);
            return returnedHS.getTheCollection();
        }catch(IOException e){
            System.out.println("Введите корректный путь файл");
            return null;
        }
    }
    public static HashSet getCollection(InputStream path)throws JAXBException{
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(path));
            ClassWrapper returnedHS = JAXB.unmarshal(br, ClassWrapper.class);
            return returnedHS.getTheCollection();
        }catch(Exception e){
            System.out.println("Введите корректный путь файл");
            return null;
        }
    }
}
