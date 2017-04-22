/**
 * Created by danil on 16.02.2017.
 */
package laba2;

public class FoodResidus implements Comparable {

    public String name = "";

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
}