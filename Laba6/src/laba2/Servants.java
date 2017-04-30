/**
 * Created by danil on 16.02.2017.
 */
package laba2;

import java.util.HashSet;

public class Servants extends GroupOfPeople {
    public boolean areHere;
    private boolean cleanedUp;
    public HashSet<String> obyazannosti;
    public void toCleanUp(){
        if(areHere){
            cleanedUp=true;
        }
    }
    public Servants(int c,boolean h){
        count=c;
        msg="Спрпуууутууус...*сказали Слуги*";
        areHere=h;
        obyazannosti= new HashSet<>();
        obyazannosti.add("Мыть");
        obyazannosti.add("Кормить");
        obyazannosti.add("Выгонять мух");
        obyazannosti.add("Развлекать");

    }
    public boolean cleanedUpIsTrue(){
        return cleanedUp;
    }
    public void showCount(){
        System.out.println("У Спрутуса было "+count+" слуг");
    }
    public boolean showHere(){
        return areHere;
    }
    public int returnCount(){
        return count;
    }
    public void PrintMSG(){
        if(areHere){
            System.out.println(msg);
        }
    }
}