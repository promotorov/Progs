package changes;

import javafx.collections.ObservableList;
import items.FoodResidus;

/**
 * Created by vladp on 05.06.2017.
 */
public class RemoveGreatestChange implements TypesChanges {
    private final static String TYPE_CHANGE=TypesChanges.REMOVEGR;
    private ObservableList<FoodResidus> oldData;
    private ObservableList<FoodResidus> newData;

    public RemoveGreatestChange(ObservableList<FoodResidus> newData, ObservableList<FoodResidus> oldData) {
        this.newData = newData;
        this.oldData = oldData;
    }

    @Override
    public TypesChanges getElement() {
        return this;
    }

    @Override
    public void showElement() {
        System.out.println("Remove_Greater");
    }

    @Override
    public String getType() {
        return TYPE_CHANGE;
    }

    public ObservableList<FoodResidus> getNewData() {
        return newData;
    }

    public ObservableList<FoodResidus> getOldData() {
        return oldData;
    }
}
