package laba2;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import sample.ErrorWindow;
import sample.SaveSucsessfullScreen;

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
            System.out.println("1");
            Marshaller marshaller = context.createMarshaller();
            System.out.println("2");
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            File fileWrite = new File(path);
            FileWriter fw = new FileWriter(fileWrite);
            BufferedWriter bw = new BufferedWriter(fw);
            marshaller.marshal(cw, bw);
        }catch(JAXBException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.loadInfoScreen("Не удалось записать данные в xml-file");
                }
            });
        }catch(IOException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.loadInfoScreen("Не удалось записать данные в xml-file");
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
                    SaveSucsessfullScreen.loadInfoScreen();
                }
            });

        }catch(JAXBException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.loadInfoScreen("Не удалось записать данные в xml-file");
                }
            });
        }
        catch (IOException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.loadInfoScreen("Не удалось записать данные в xml-file");
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
                    ErrorWindow.loadInfoScreen("Что-то не так с данными в файле. Проверьте их корректность.");
                }
            });
            return null;
        }catch (FileNotFoundException e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.loadInfoScreen("Файл не найден. Проверьте корректность пути.");
                }
            });
            return null;
        }catch (Exception e){
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    ErrorWindow.loadInfoScreen("Произошла неизвестная ошибка.");
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
}
