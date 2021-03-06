/**
 * Created by danil on 16.02.2017.
 */
package laba2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public  class FoodResidus implements Comparable {
    @XmlElement(name="name")
    public String name = "";
    @XmlElement(name="weight")
    public int wheight = 0;

    public boolean fliesAttraction=false;

    @Override
    public int compareTo(Object o) {
        FoodResidus comparedFoodResidus = (FoodResidus) o;
        int result;
        result = wheight-comparedFoodResidus.wheight;
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

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name=name;
    }

    public int getWeight(){
        return this.wheight;
    }

    public void setWeight(int weight){
        this.wheight=weight;
    }
}