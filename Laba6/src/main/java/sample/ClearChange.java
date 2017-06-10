package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import laba2.FoodResidus;

import java.util.ArrayList;

/**
 * Created by vladp on 17.05.2017.
 */
public class ClearChange implements TypesChanges {
    private final static String TYPE_CHANGE=TypesChanges.CLEAR_ALL;
    private ObservableList<FoodResidus> items= FXCollections.observableArrayList();

    public ClearChange(ObservableList<FoodResidus> items) {
        this.items=items;
    }

    @Override
    public TypesChanges getElement() {
        return this;
    }

    @Override
    public void showElement() {
        for(FoodResidus item:items){
            System.out.println(item.getName()+" "+item.getWeight() + " " +TYPE_CHANGE);
        }
    }

    public String getType(){
        return TYPE_CHANGE;
    }

    public ObservableList<FoodResidus> getItems(){
        return this.items;
    }
}
