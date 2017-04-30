/**
 * Created by danil on 16.02.2017.
 */
package laba2;

public class Meet extends FoodResidus{
    public Meet(int weight){
        super("meet", weight);
    }
    public Meet(String name){
        super(name, 50);
    }
    public Meet(String name,int weight){
        super(name, weight);
    }
}