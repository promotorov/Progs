package io;

import javafx.collections.ObservableList;
import items.FoodResidus;

import java.io.File;
import java.util.HashSet;
import java.util.Iterator;

import static serealize.XMLworker.saveCollection;

/**
 * Created by vladp on 09.05.2017.
 */
public class SaveTable extends Thread {
    File fileName;
    ObservableList data;
    public SaveTable(String str, ObservableList data, File fileName){
        super(str);
        this.fileName=fileName;
        this.data=data;
    }

    public SaveTable(String str, ObservableList data){
        this.fileName=new File("src\\main\\resources\\any.xml");
        this.data=data;
    }

    public void run(){
        try {
            ObservableList<FoodResidus> ob=data;
            HashSet<FoodResidus> set=new HashSet<>();
            Iterator<FoodResidus> iterator=ob.iterator();
            while(iterator.hasNext()){
                set.add(iterator.next());
            }
            saveCollection(fileName, set);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
