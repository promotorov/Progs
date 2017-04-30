/**
 * Created by danil on 16.02.2017.
 */
package laba2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class FoodResidus implements Comparable {

    public String name;

    public int weight;


    public boolean fliesAttraction=false;

    @Override
    public int compareTo(Object o) {
        FoodResidus comparedFoodResidus = (FoodResidus) o;
        int result;
        result = weight-comparedFoodResidus.weight;
        if(result!=0){return (int) result/Math.abs(result);}
        return 0;
    }

    public FoodResidus(String name, int weight){
        this.name=name;
        this.weight=weight;
    }

    public void smell(Servants s){
        if(s.cleanedUpIsTrue()){
            fliesAttraction=false;
        }else{
            fliesAttraction=true;
        }
    }
    public void attractFlies(String name){
        if(fliesAttraction){
            System.out.println("*"+name+" привлекает мух*");
        }else{
            System.out.println("*"+name+" больше не пахнет*");
        }
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getWeight(){
        return weight;
    }

    public void setWeight(int weight){
        this.weight=weight;
    }
}