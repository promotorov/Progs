package controllers;

import changes.RemoveGreatestChange;
import changes.TableStatements;
import items.FoodResidus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import screens.RemoveElWindow;

import java.io.IOException;
import java.util.Iterator;

import static serealize.JSONworker.toJavaObject;

/**
 * Created by danil on 10.06.2017.
 */
public class RemoveElController {
    public static void RemoveElOKbutton(Button button, ObservableList<FoodResidus> data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    ObservableList<FoodResidus> oldTemp= FXCollections.observableArrayList(data);
                    FoodResidus jsonCompared = toJavaObject(RemoveElWindow.getInstace().getTextField().getText());
                    Iterator<FoodResidus> iterator = data.iterator();
                    while(iterator.hasNext()){
                        FoodResidus compared = iterator.next();
                        if(compared.compareTo(jsonCompared)>0){
                            iterator.remove();
                        }
                    }
                    ObservableList<FoodResidus> newTemp=FXCollections.observableArrayList(data);
                    RemoveGreatestChange r=new RemoveGreatestChange(newTemp, oldTemp);
                    TableStatements.addChange(r);
                    System.out.println("Все элементы превышающие данный удалены");
                } catch (IOException e) {
                    System.out.println("Не верный аргумент");
                }
                Stage stage = (Stage) RemoveElWindow.getInstace().getRemoveElOKbutton().getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void RemoveElCancelButton(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) RemoveElWindow.getInstace().getRemoveElCancelButton().getScene().getWindow();
                stage.close();
            }
        });
    }
}
