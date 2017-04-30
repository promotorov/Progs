/**
 * Created by danil on 16.02.2017.
 */
package laba2;


public class Cheese extends FoodResidus{
    public Cheese(int weight){
        super("cheese", weight);
    }
    public Cheese(String name){
        super(name, 50);
    }
    public Cheese(String name,int weight){
        super(name, weight);
    }
}

