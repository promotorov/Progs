package laba2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by danil on 24.02.2017.
 */
@XmlRootElement
public class ClassWrapper {

    private List<FoodResidus> theCollection;

    public ClassWrapper(){
        theCollection = new LinkedList<FoodResidus>();
    }
    public List<FoodResidus> getTheCollection(){
        return theCollection;
    }
    public void setTheCollection(List<FoodResidus> theCollection){
        this.theCollection=theCollection;
    }
}
