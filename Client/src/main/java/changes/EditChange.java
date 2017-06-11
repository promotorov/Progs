package changes;

import items.FoodResidus;

/**
 * Created by vladp on 05.06.2017.
 */
public class EditChange implements TypesChanges {
    private final static String TYPE_CHANGE=TypesChanges.EDIT;
    private FoodResidus newItem;
    private FoodResidus oldItem;
    private int index;

    public EditChange(FoodResidus newItem, FoodResidus oldItem, int index) {
        this.newItem = newItem;
        this.index = index;
        this.oldItem = oldItem;
    }

    @Override
    public TypesChanges getElement() {
        return this;
    }

    @Override
    public void showElement() {
        System.out.println(this.newItem.getName()+" " +this.newItem.getWeight()+" " + TYPE_CHANGE);
        System.out.println(this.oldItem.getName()+" " +this.oldItem.getWeight()+" " + TYPE_CHANGE);
    }

    @Override
    public String getType() {
        return TYPE_CHANGE;
    }

    public FoodResidus getNewItem() {
        return newItem;
    }

    public FoodResidus getOldItem() {
        return oldItem;
    }

    public int getIndex() {
        return index;
    }
}
