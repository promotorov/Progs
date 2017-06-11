package changes;

import controllers.MainScreenController;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import screens.MainScreen;

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
        MainScreen.getInstace().getButtonRedo().setGraphic(new ImageView(image));
        Image image2=new Image("/icons/undo.png", 32, 32, false, false);
        MainScreen.getInstace().getButtonUndo().setGraphic(new ImageView(image2));
        System.out.println(currentStatement+" " + changes.size());
        show();
    }
    public static void show(){
        for(TypesChanges t:changes){
            t.showElement();
        }
    }
    public static void undo(){
        if(currentStatement>=0 ){
            Image image=new Image("/icons/redo.png", 32, 32, false, false);
            MainScreen.getInstace().getButtonRedo().setGraphic(new ImageView(image));
            TypesChanges t=changes.get(currentStatement);
            if(t.getType().equals(TypesChanges.ADD)){
                MainScreen.getInstace().getTable().getItems().remove(MainScreen.getInstace().getTable().getItems().size()-1);
                MainScreenController.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.REMOVE)){
                RemoveChange temp=(RemoveChange)t;
                MainScreen.getInstace().getTable().getItems().add(temp.getIndex(),temp.getItem());
                MainScreenController.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.CLEAR_ALL)){
                ClearChange temp=(ClearChange) t;
                MainScreen.getInstace().getTable().getItems().addAll(temp.getItems());
                MainScreenController.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.EDIT)){
                EditChange temp=(EditChange) t;
                MainScreen.getInstace().getTable().getItems().set(temp.getIndex(), temp.getOldItem());
                MainScreenController.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.REMOVEGR)){
                RemoveGreatestChange temp=(RemoveGreatestChange) t;
                MainScreen.getInstace().getTable().getItems().clear();
                MainScreen.getInstace().getTable().getItems().addAll(temp.getOldData());
                MainScreenController.checkHighlight();
            }
            currentStatement--;
            if(currentStatement==-1){
                Image image2=new Image("/icons/undoNew.png", 32, 32, false, false);
                MainScreen.getInstace().getButtonUndo().setGraphic(new ImageView(image2));
            }
        }
        else {
            Image image2=new Image("/icons/undoNew.png", 32, 32, false, false);
            MainScreen.getInstace().getButtonUndo().setGraphic(new ImageView(image2));
            System.out.println("Nelza");
        }
    }
    public static void redo(){
        if(currentStatement<changes.size()-1 ){
            currentStatement++;
            if(currentStatement==(changes.size()-1)){
                Image image2=new Image("/icons/redoNew.png", 32, 32, false, false);
                MainScreen.getInstace().getButtonRedo().setGraphic(new ImageView(image2));
                MainScreenController.checkHighlight();
            }
            Image image2=new Image("/icons/undo.png", 32, 32, false, false);
            MainScreen.getInstace().getButtonUndo().setGraphic(new ImageView(image2));
            TypesChanges t=changes.get(currentStatement);
            if(t.getType().equals(TypesChanges.ADD)){
                AddChange temp=(AddChange)t;
                MainScreen.getInstace().getTable().getItems().add(((AddChange) t).getItem());
                MainScreenController.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.REMOVE)){
                RemoveChange temp=(RemoveChange)t;
                MainScreen.getInstace().getTable().getItems().remove(temp.getIndex());
                MainScreenController.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.CLEAR_ALL)){
                ClearChange temp=(ClearChange) t;
                MainScreen.getInstace().getTable().getItems().clear();
                MainScreenController.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.EDIT)){
                EditChange temp=(EditChange) t;
                MainScreen.getInstace().getTable().getItems().set(temp.getIndex(), temp.getNewItem());
                MainScreenController.checkHighlight();
            }
            else if(t.getType().equals(TypesChanges.REMOVEGR)){
                RemoveGreatestChange temp=(RemoveGreatestChange) t;
                MainScreen.getInstace().getTable().getItems().clear();
                MainScreen.getInstace().getTable().getItems().addAll(temp.getNewData());
                MainScreenController.checkHighlight();
            }
        }
    }
    public static int getCurrentStatement() {
        return currentStatement;
    }
    public static void setCurrentStatement(int s){
        if(s==-1) {
            Image image2 = new Image("/icons/redoNew.png", 32, 32, false, false);
            MainScreen.getInstace().getButtonRedo().setGraphic(new ImageView(image2));
            Image image=new Image("/icons/undoNew.png", 32, 32, false, false);
            MainScreen.getInstace().getButtonUndo().setGraphic(new ImageView(image));
        }
        currentStatement=s;
    }
    public static ArrayList<TypesChanges> getChanges() {
        return changes;
    }
}
