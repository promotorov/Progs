/**
 * Created by danil on 16.02.2017.
 */
package laba2;

public class Meet extends FoodResidus{
    public Meet(int wheight){
        this.name="meet";
        this.wheight=wheight;
    }
    public Meet(String name){
        this.name=name;
        this.wheight=50;
    }
    public Meet(String name,int wheight){
        this.name=name;
        this.wheight=wheight;
    }
}