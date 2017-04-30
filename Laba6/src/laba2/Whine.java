/**
 * Created by danil on 16.02.2017.
 */
package laba2;

public class Whine extends FoodResidus{
    public Whine(int weight){
        super("whine", weight);
    }
    public Whine(String name){
        super(name, 50);
    }
    public Whine(String name,int weight)
    {
        super(name, weight);
    }
}