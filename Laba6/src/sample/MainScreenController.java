package sample;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import laba2.ClearWindow;
import laba2.FoodResidus;
import laba2.RemoveElWindow;

import java.io.IOException;
import java.util.Iterator;

import static laba2.JSONworker.toJavaObject;

/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreenController {
    public static void buttonFiltr(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Устанавливаем фильтры");
            }
        });
    }
    public static void buttonDelFiltr(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Удаляем фильтры");
            }
        });
    }
    public static void buttonInfo(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Выводим инфу");
                InfoWindow.loadInfoWindow();
            }
        });
    }
    public static void InfoOKbutton(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) InfoWindow.InfoOKbutton.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void buttonRemoveEl(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Удаляем элементы");
                RemoveElWindow.loadRemoveElWindow();
            }
        });
    }
    public static void RemoveElOKbutton(Button button){//TODO доделать
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FoodResidus jsonCompared = toJavaObject(RemoveElWindow.textField.getText());
                    Iterator<FoodResidus> iterator = MainScreen.data.iterator();
                    while(iterator.hasNext()){
                        FoodResidus compared = iterator.next();
                        if(compared.compareTo(jsonCompared)>0){
                            iterator.remove();
                        }
                    }
                    System.out.println("Все элементы превышающие данный удалены");
                } catch (IOException e) {
                    System.out.println("Не верный аргумент");
                }
                Stage stage = (Stage) RemoveElWindow.RemoveElOKbutton.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void RemoveElCancelButton(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) RemoveElWindow.RemoveElCancelButton.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void buttonChoose(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Выбираем элемент");
            }
        });
    }
    public static void buttonSave(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Сохраняем");
            }
        });
    }
    public static void buttonClear(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Очищаем");
                ClearWindow.loadClearWindow();
            }
        });
    }
    public static void ClearOKbutton(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                MainScreen.data.clear();
                Stage stage = (Stage) ClearWindow.ClearOKbutton.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void ClearCancelButton(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) ClearWindow.ClearOKbutton.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void editName(TableColumn columnName, ObservableList<FoodResidus> data){
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnName.setCellFactory(TextFieldTableCell.forTableColumn());
        columnName.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<FoodResidus, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FoodResidus, String> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setName(t.getNewValue());
                        for(int i=0; i<data.size(); i++){
                            System.out.println(data.get(i).getName()+data.get(i).getWeight());
                        }
                    }
                }
        );
    }
    public static void editWeight(TableColumn columnWeight, ObservableList<FoodResidus> data){
        columnWeight.setCellValueFactory(new PropertyValueFactory<>("weight"));
        columnWeight.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        columnWeight.setOnEditCommit(
                new EventHandler<TableColumn.CellEditEvent<FoodResidus, Integer>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FoodResidus, Integer> t) {
                        t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setWeight(t.getNewValue());
                        for (int i = 0; i < data.size(); i++) {
                            System.out.println(data.get(i).getName() + data.get(i).getWeight());
                        }
                    }
                }
        );
    }
}
