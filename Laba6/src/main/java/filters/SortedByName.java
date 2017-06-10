package filters;

import items.FoodResidus;

import java.util.Comparator;

/**
 * Created by vladp on 05.06.2017.
 */
public class SortedByName implements Comparator<FoodResidus> {
    public int compare(FoodResidus obj1, FoodResidus obj2)
    {
        String str1 = obj1.getName();
        String str2 = obj2.getName();
        return str1.compareTo(str2);
    }
}
