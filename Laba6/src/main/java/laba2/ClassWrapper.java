package laba2;

import javafx.collections.ObservableList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;

/**
 * Created by danil on 24.02.2017.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class ClassWrapper {

    private HashSet<FoodResidus> theCollection;

    public ClassWrapper(){
        theCollection = new HashSet<FoodResidus>();
    }
    public HashSet<FoodResidus> getTheCollection(){
        return theCollection;
    }
    public void setTheCollection(HashSet<FoodResidus> theCollection){
        this.theCollection=theCollection;
    }
}
