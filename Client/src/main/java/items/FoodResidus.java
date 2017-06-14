/**
 * Created by danil on 16.02.2017.
 */
package items;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public  class FoodResidus implements Comparable {
    @XmlElement(name="name")
    String name = "";
    @XmlElement(name="weight")
    int wheight = 0;

    private boolean highlightProperty=false;
    private boolean activehighlightProperty=false;
    private boolean showContextMenu=true;

    public boolean isShowContextMenu() {
        return showContextMenu;
    }

    public void setShowContextMenu(boolean showContextMenu) {
        this.showContextMenu = showContextMenu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FoodResidus)) return false;

        FoodResidus that = (FoodResidus) o;

        if (wheight != that.wheight) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + wheight;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        FoodResidus comparedFoodResidus = (FoodResidus) o;
        int result;
        result = wheight-comparedFoodResidus.wheight;
        if(result!=0){return (int) result/Math.abs(result);}
        return 0;
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

    public boolean isHighlightProperty() {
        return highlightProperty;
    }

    public void setHighlightProperty(boolean highlightProperty) {
        this.highlightProperty = highlightProperty;
    }

    public boolean isActivehighlightProperty() {
        return activehighlightProperty;
    }

    public void setActivehighlightProperty(boolean activehighlightProperty) {
        this.activehighlightProperty = activehighlightProperty;
    }

    public void setWeight(int weight){
        this.wheight=weight;
    }
}