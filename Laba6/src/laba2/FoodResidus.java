/**
 * Created by danil on 16.02.2017.
 */
package laba2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public  class FoodResidus implements Comparable {
//TODO dfghj
    public SimpleStringProperty name;

    public SimpleIntegerProperty wheight;

    public boolean fliesAttraction=false;

    @Override
    public int compareTo(Object o) {
        FoodResidus comparedFoodResidus = (FoodResidus) o;
        int result;
        result = wheight.get()-comparedFoodResidus.wheight.get();
        if(result!=0){return (int) result/Math.abs(result);}
        return 0;
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

    public String getName(){return name.get();}

    public void setName(String name){
        this.name.set(name);
    }

    public Integer getWeight(){
        return wheight.get();
    }

    public void setWeight(int weight){
        this.wheight.set(weight);
    }
}