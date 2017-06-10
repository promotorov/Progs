package sample;

import laba2.FoodResidus;

import java.util.ArrayList;

/**
 * Created by vladp on 17.05.2017.
 */
public class RemoveChange implements TypesChanges {
    private final static String TYPE_CHANGE=TypesChanges.REMOVE;
    private FoodResidus item;
    private int index;
    public RemoveChange(FoodResidus item, int index) {
        this.item = item;
        this.index = index;
    }

    @Override
    public TypesChanges getElement() {
        return this;
    }

    @Override
    public void showElement() {
        System.out.println(this.item.getName()+" " +this.item.getWeight()+" "+index+" " + TYPE_CHANGE);
    }

    public String getType(){
        return TYPE_CHANGE;
    }

    public int getIndex(){
        return this.index;
    }

    public FoodResidus getItem(){
        return this.item;
    }

}
