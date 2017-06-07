package sample;

import laba2.FoodResidus;

/**
 * Created by vladp on 17.05.2017.
 */
public class AddChange implements TypesChanges {
    private final static String TYPE_CHANGE=TypesChanges.ADD;
    private FoodResidus item;

    public AddChange(FoodResidus item) {
        this.item = item;
    }

    @Override
    public TypesChanges getElement() {
        return this;
    }

    @Override
    public void showElement() {
        System.out.println(this.item.getName()+" " +this.item.getWeight()+" " + TYPE_CHANGE);
    }

    public String getType(){
        return TYPE_CHANGE;
    }
    public FoodResidus getItem(){
        return this.item;
    }
}
