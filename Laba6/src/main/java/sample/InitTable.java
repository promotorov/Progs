package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import laba2.FoodResidus;
import laba2.XMLworker;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by vladp on 09.05.2017.
 */
public class InitTable extends Thread{
    ObservableList<FoodResidus> data;
    TableView table;
    public InitTable(String str, ObservableList<FoodResidus> data, TableView table) {
        super(str);
        this.data=data;
        this.table=table;
    }
    public void run() {
        try {
            HashSet<FoodResidus> set = XMLworker.getCollection("src\\main\\resources\\sample.xml");
            Iterator<FoodResidus> iterator = set.iterator();
            while (iterator.hasNext()) {
                data.add(iterator.next());
                System.out.println("Dsdsd");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
