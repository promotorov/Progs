/**
 * Created by danil on 16.02.2017.
 */
package laba2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Whine extends FoodResidus{
    public Whine(int wheight){
        this.name=new SimpleStringProperty("whine");
        this.wheight=new SimpleIntegerProperty(wheight);
    }
    public Whine(String name){
        this.name=new SimpleStringProperty(name);
        this.wheight=new SimpleIntegerProperty(50);
    }
    public Whine(String name,int wheight){
        this.name=new SimpleStringProperty(name);
        this.wheight=new SimpleIntegerProperty(wheight);
    }
}