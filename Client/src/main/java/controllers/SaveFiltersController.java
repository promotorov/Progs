package controllers;

import changes.RemoveGreatestChange;
import changes.TableStatements;
import filters.CompareMethods;
import items.FoodResidus;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import screens.ErrorWindow;
import screens.SetFiltersWindow;

/**
 * Created by danil on 10.06.2017.
 */
public class SaveFiltersController {
    public static void SetFiltersOKbutton(Button button, ObservableList<FoodResidus> data, ObservableList UnSeeingData, TableView<FoodResidus> table, TextField textFieldName, TextField textFieldWeight){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if(CompareMethods.isCorrect(textFieldName.getText(),textFieldWeight.getText())){
                                    ObservableList<FoodResidus> oldTemp= FXCollections.observableArrayList(data);
                                    for(int i=0; i<data.size(); i++){
                                        if((CompareMethods.nameCompare(data.get(i).getName(), textFieldName.getText()))&&(CompareMethods.weightCompare(data.get(i).getWeight(),textFieldWeight.getText()))){
                                        }
                                        else{
                                            UnSeeingData.add(data.get(i));
                                            data.remove(i);
                                            i--;
                                        }
                                    }
                                    ObservableList<FoodResidus> newTemp=FXCollections.observableArrayList(data);
                                    table.setItems(data);
                                    System.out.println(data.size());
                                    MainScreenController.checkHighlight();
                                    RemoveGreatestChange r=new RemoveGreatestChange(newTemp, oldTemp);
                                    TableStatements.addChange(r);
                                }else{
                                    ErrorWindow.getInstace().loadInfoScreen("Неверный формат фильтра");
                                }
                            }
                        });
                        Stage stage = (Stage) SetFiltersWindow.getInstace().getSetFiltersOKbutton().getScene().getWindow();
                        stage.close();
                    }
                });
            }
        });
    }
    public static void SetFiltersCancelButton(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) SetFiltersWindow.getInstace().getSetFiltersCancelButton().getScene().getWindow();
                stage.close();
                System.out.println("отмена");
            }
        });
    }
}
