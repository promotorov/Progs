/**
 * Created by danil on 16.02.2017.
 */
package laba2;



public class Cheese extends FoodResidus{
    public Cheese(int wheight){
        this.name="cheese";
        this.wheight=wheight;
    }
    public Cheese(String name){
        this.name=name;
        this.wheight=50;
    }
    public Cheese(String name,int wheight){
        this.name=name;
        this.wheight=wheight;
    }
}

