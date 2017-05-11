package sample;

import javafx.collections.ObservableList;
import laba2.FoodResidus;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Iterator;

import static laba2.XMLworker.saveCollection;

/**
 * Created by vladp on 09.05.2017.
 */
public class SaveTableDefault extends Thread {
    File fileName;
    ObservableList data;

    public SaveTableDefault(String str, ObservableList data){
        this.fileName=new File(this.getClass().getResource("/sample.xml").getPath());
        System.out.println(fileName);
        this.data=data;
    }
    public void run(){
        try {
            PrintWriter wr=new PrintWriter(fileName);
            ObservableList<FoodResidus> ob=data;
            HashSet<FoodResidus> set=new HashSet<>();
            Iterator<FoodResidus> iterator=ob.iterator();
            while(iterator.hasNext()){
                set.add(iterator.next());
            }
            saveCollection(wr, set);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}