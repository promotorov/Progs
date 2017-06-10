/**
 * Created by danil on 16.02.2017.
 */
package items;

public class Whine extends FoodResidus{
    public Whine(int wheight){
        this.name="whine";
        this.wheight=wheight;
    }
    public Whine(String name){
        this.name=name;
        this.wheight=50;
    }
    public Whine(String name,int wheight){
        this.name=name;
        this.wheight=wheight;
    }
}