package sample;

import com.sun.deploy.security.ValidationState;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import laba2.FoodResidus;

import java.util.ArrayList;

/**
 * Created by vladp on 17.05.2017.
 */
public class TableStatements {
    private static ArrayList<TypesChanges> changes=new ArrayList<>();
    private static int currentStatement=-1;
    public static void addChange(TypesChanges change){
        System.out.println(currentStatement+" " + (changes.size()-1));
        if(currentStatement!=(changes.size()-1)){
            int temp=changes.size();
            for(int i=(currentStatement+1); i<temp; i++){
                System.out.println("delete: "+ i);
                changes.remove(currentStatement+1);
            }
            currentStatement=changes.size()-1;
        }
        currentStatement++;
        changes.add(change);
        Image image=new Image("/icons/redoNew.png", 32, 32, false, false);
        MainScreen.getButtonRedo().setGraphic(new ImageView(image));
        Image image2=new Image("/icons/undo.png", 32, 32, false, false);
        MainScreen.getButtonUndo().setGraphic(new ImageView(image2));
        System.out.println(currentStatement+" " + changes.size());
        show();
    }
    public static void show(){
        for(TypesChanges t:changes){
            t.showElement();
        }
        System.out.println("------------------------------------------");
    }
    public static void undo(){
        if(currentStatement>=0 ){
            Image image=new Image("/icons/redo.png", 32, 32, false, false);
            MainScreen.getButtonRedo().setGraphic(new ImageView(image));
            TypesChanges t=changes.get(currentStatement);
            if(t.getType().equals(TypesChanges.ADD)){
                MainScreen.getTable().getItems().remove(MainScreen.getTable().getItems().size()-1);
                MainScreen.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.REMOVE)){
                RemoveChange temp=(RemoveChange)t;
                MainScreen.getTable().getItems().add(temp.getIndex(),temp.getItem());
                MainScreen.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.CLEAR_ALL)){
                ClearChange temp=(ClearChange) t;
                MainScreen.getTable().getItems().addAll(temp.getItems());
                MainScreen.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.EDIT)){
                EditChange temp=(EditChange) t;
                MainScreen.getTable().getItems().set(temp.getIndex(), temp.getOldItem());
                MainScreen.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.REMOVEGR)){
                RemoveGreatestChange temp=(RemoveGreatestChange) t;
                MainScreen.getTable().getItems().clear();
                MainScreen.getTable().getItems().addAll(temp.getOldData());
                MainScreen.checkHighlight();
            }
            currentStatement--;
            if(currentStatement==-1){
                Image image2=new Image("/icons/undoNew.png", 32, 32, false, false);
                MainScreen.getButtonUndo().setGraphic(new ImageView(image2));
            }
            System.out.println(currentStatement);
        }
        else {
            Image image2=new Image("/icons/undoNew.png", 32, 32, false, false);
            MainScreen.getButtonUndo().setGraphic(new ImageView(image2));
            System.out.println("Nelza");
        }
    }
    public static void redo(){
        if(currentStatement<changes.size()-1 ){
            currentStatement++;
            if(currentStatement==(changes.size()-1)){
                Image image2=new Image("/icons/redoNew.png", 32, 32, false, false);
                MainScreen.getButtonRedo().setGraphic(new ImageView(image2));
                MainScreen.checkHighlight();
            }
            Image image2=new Image("/icons/undo.png", 32, 32, false, false);
            MainScreen.getButtonUndo().setGraphic(new ImageView(image2));
            TypesChanges t=changes.get(currentStatement);
            if(t.getType().equals(TypesChanges.ADD)){
                AddChange temp=(AddChange)t;
                MainScreen.getTable().getItems().add(((AddChange) t).getItem());
                MainScreen.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.REMOVE)){
                RemoveChange temp=(RemoveChange)t;
                MainScreen.getTable().getItems().remove(temp.getIndex());
                MainScreen.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.CLEAR_ALL)){
                ClearChange temp=(ClearChange) t;
                MainScreen.getTable().getItems().clear();
                MainScreen.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.EDIT)){
                EditChange temp=(EditChange) t;
                MainScreen.getTable().getItems().set(temp.getIndex(), temp.getNewItem());
                MainScreen.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.REMOVEGR)){
                RemoveGreatestChange temp=(RemoveGreatestChange) t;
                MainScreen.getTable().getItems().clear();
                MainScreen.getTable().getItems().addAll(temp.getNewData());
                MainScreen.checkHighlight();
            }
            System.out.println(currentStatement);
        }
        else {
            System.out.println("Nelza");
        }
    }

    public static int getCurrentStatement() {
        return currentStatement;
    }

    public static void setCurrentStatement(int s){
        if(s==-1) {
            Image image2 = new Image("/icons/redoNew.png", 32, 32, false, false);
            MainScreen.getButtonRedo().setGraphic(new ImageView(image2));
            Image image=new Image("/icons/undoNew.png", 32, 32, false, false);
            MainScreen.getButtonUndo().setGraphic(new ImageView(image));
        }
        currentStatement=s;
    }

    public static ArrayList<TypesChanges> getChanges() {
        return changes;
    }
}
