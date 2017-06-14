package controllers;

import changes.ClearChange;
import changes.TableStatements;
import io.dataBaseInteraction.DBIClear;
import io.dataBaseInteraction.DataBaseInteraction;
import items.FoodResidus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import screens.ClearWindow;

/**
 * Created by danil on 10.06.2017.
 */
public class ClearController {
    public static void ClearOKbutton(Button button, ObservableList<FoodResidus> data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<FoodResidus> temp= FXCollections.observableArrayList(data);
                ClearChange clearChange=new ClearChange(temp);
                TableStatements.addChange(clearChange);
                data.clear();
                Stage stage = (Stage) ClearWindow.getInstace().getClearOKbutton().getScene().getWindow();
                stage.close();
                MainScreenController.checkHighlight();
                DBIClear db=new DBIClear("clear", data);
                db.start();
            }
        });
    }
    public static void ClearCancelButton(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) ClearWindow.getInstace().getClearOKbutton().getScene().getWindow();
                stage.close();
            }
        });
    }
}
