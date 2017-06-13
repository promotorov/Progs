package serealize;

import items.FoodResidus;
import javafx.application.Platform;
import screens.ErrorWindow;
import screens.SaveSucsessfullWindow;

import javax.xml.bind.*;
import java.io.*;
import java.util.HashSet;

/**
 * Created by danil on 23.02.2017.
 */

public class XMLworker {

    public static void saveCollection(String path, HashSet hs){
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
        }catch(JAXBException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.getInstace().loadInfoScreen("Не удалось записать данные в xml-file");
                }
            });
        }catch(IOException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.getInstace().loadInfoScreen("Не удалось записать данные в xml-file");
                }
            });
        }
    }
    public static void saveCollection(File fileWrite, HashSet hs){
        try{
            JAXBContext context = JAXBContext.newInstance(ClassWrapper.class);
            ClassWrapper cw = new ClassWrapper();
            cw.setTheCollection(hs);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            FileWriter fw = new FileWriter(fileWrite);
            BufferedWriter bw = new BufferedWriter(fw);
            marshaller.marshal(cw, bw);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    SaveSucsessfullWindow.getInstace().loadInfoScreen();
                }
            });

        }catch(JAXBException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.getInstace().loadInfoScreen("Не удалось записать данные в xml-file");
                }
            });
        }
        catch (IOException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.getInstace().loadInfoScreen("Не удалось записать данные в xml-file");
                }
            });
        }
    }
    public static HashSet getCollection(String path){
        try{
            File fileRead = new File(path);
            FileReader fr = new FileReader(fileRead);
            BufferedReader br = new BufferedReader(fr);
            ClassWrapper returnedHS = JAXB.unmarshal(br, ClassWrapper.class);
            return returnedHS.getTheCollection();
        }catch(DataBindingException e){
            System.out.println("In");
            System.out.println(e.toString());
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.getInstace().loadInfoScreen("Что-то не так с данными в файле. Проверьте их корректность.");
                }
            });
            return null;
        }catch (FileNotFoundException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.getInstace().loadInfoScreen("Файл не найден. Проверьте корректность пути.");
                }
            });
            return null;
        }catch (Exception e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.getInstace().loadInfoScreen("Произошла неизвестная ошибка.");
                }
            });
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
    public static HashSet xmlToObject(String xml){
        try{
            System.out.println(xml);
            xml=xml.replace("<?xmlversion=\"1.0\"encoding=\"UTF-8\"standalone=\"yes\"?>","<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>");
            xml=xml.substring(0,xml.lastIndexOf(">")+1);
            System.out.println(xml);
            StringReader sr = new StringReader(xml);
            System.out.println(xml.lastIndexOf(">"));
            ClassWrapper returnedHS = JAXB.unmarshal(sr, ClassWrapper.class);
            return returnedHS.getTheCollection();
        }catch(DataBindingException e){
            return null;
        }catch (Exception e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.getInstace().loadInfoScreen("Произошла неизвестная ошибка.");
                }
            });
            return null;
        }
    }
    public static String objectToXML(HashSet<FoodResidus> hs){
        try{
            JAXBContext context = JAXBContext.newInstance(ClassWrapper.class);
            ClassWrapper cw = new ClassWrapper();
            cw.setTheCollection(hs);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            StringWriter sw = new StringWriter();
            marshaller.marshal(cw, sw);
            return sw.toString();
        }catch(JAXBException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.getInstace().loadInfoScreen("Не удалось записать данные в xml-file");
                }
            });
            return null;
        }
    }

}
