/**
 * Created by danil on 16.02.2017.
 */
package laba2;

public class Flies {
    public void checkAttractionOfCheese(Cheese c){
        if(c.fliesAttraction){
            System.out.println("Полетели есть сыр");
        }
    }
    public void checkAttractionOfWhine(Whine w){
        if(w.fliesAttraction){
            System.out.println("Полетели пить вино");
        }
    }
    public void checkAttractionOfMeet(Meet m){
        if(m.fliesAttraction){
            System.out.println("Полетели есть мясо");
        }
    }
}

