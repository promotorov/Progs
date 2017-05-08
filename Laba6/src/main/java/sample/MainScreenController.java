package sample;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import laba2.FoodResidus;
import laba2.Whine;
import laba2.XMLworker;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static laba2.JSONworker.toJavaObject;
import static laba2.XMLworker.saveCollection;

/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreenController {
    public static void buttonFiltr(Button button){
        /*button.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {//TODO Это какой-то экспериментальный кусок кода?
            @Override
            public void handle(MouseEvent m) {
                if(m.getButton()==MouseButton.SECONDARY) System.out.println("R");
                if(m.getButton()==MouseButton.PRIMARY) System.out.println("L");
                if(m.getButton()==MouseButton.MIDDLE)System.out.println("M");
                System.out.println("Устанавливаем фильтры");
            }
        });*/
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SetFiltersWindow.loadSetFiltersWindow();
                System.out.println("Удаляем фильтры");
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
    public static void buttonInfo(Button button,ObservableList data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Выводим инфу");
                InfoWindow.loadInfoWindow(data);
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
    public static void buttonRemoveEl(Button button, ObservableList data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Удаляем элементы");
                RemoveElWindow.loadRemoveElWindow(data);
            }
        });
    }
    public static void RemoveElOKbutton(Button button, ObservableList<FoodResidus> data){//TODO доделать
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    FoodResidus jsonCompared = toJavaObject(RemoveElWindow.textField.getText());
                    Iterator<FoodResidus> iterator = data.iterator();
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
    public static void buttonChoose(Button button, ListView listView){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML файлы","*.xml"));
                List<File> selectedFiles = fileChooser.showOpenMultipleDialog(null);
                if(selectedFiles!=null) {
                    for (int i = 0; i < selectedFiles.size(); i++) {
                        listView.getItems().add(selectedFiles.get(i).getAbsolutePath());
                    }
                }else{
                    System.out.println("Файл не выбрали");
                }
            }
        });
    }
    public static void buttonSave(Button button, ObservableList data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Сохраняем");
                SaveWindow.loadSaveWindow(data);
            }
        });
    }
    public static void SaveChooseButton(Button button, ObservableList<FoodResidus> data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                FileChooser fileChooser=new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML файлы","*.xml"));
                File selectedFile=fileChooser.showOpenDialog(null);
                ObservableList<FoodResidus> ob=data;
                HashSet<FoodResidus> set=new HashSet<>();
                Iterator<FoodResidus> iterator=ob.iterator();
                while(iterator.hasNext()){
                    set.add(iterator.next());
                }
                try {
                    saveCollection(selectedFile, set);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                Stage stage = (Stage) SaveWindow.SaveChooseButton.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void SaveDefaultButton(Button button, ObservableList<FoodResidus> data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                ObservableList<FoodResidus> ob=data;
                HashSet<FoodResidus> set=new HashSet<>();
                Iterator<FoodResidus> iterator=ob.iterator();
                while(iterator.hasNext()){
                    set.add(iterator.next());
                }
                try {
                    saveCollection(new File("src\\main\\java\\sample.xml"), set);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                Stage stage = (Stage) SaveWindow.SaveDefaultButton.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void buttonClear(Button button, ObservableList data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Очищаем");
                ClearWindow.loadClearWindow(data);
            }
        });
    }
    public static void ClearOKbutton(Button button, ObservableList<FoodResidus> data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                data.clear();
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
    public static void tableViewRightClick(TableView tableView){
        tableView.setRowFactory(new Callback<TableView<FoodResidus>, TableRow<FoodResidus>>() {
            @Override
            public TableRow<FoodResidus> call(TableView<FoodResidus> table) {
                System.out.println("ds");
                TableRow<FoodResidus> row=new TableRow<FoodResidus>();
                MenuItem itemRemove=new MenuItem("Remove");
                MenuItem itemAdd=new MenuItem("Add");
                MenuItem itemAdd2=new MenuItem("Add");
                ContextMenu menuRemove=new ContextMenu();
                ContextMenu menuAdd=new ContextMenu();
                menuRemove.getItems().addAll(itemRemove, itemAdd2);
                menuAdd.getItems().addAll(itemAdd);
                itemRemove.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        table.getItems().remove(row.getItem());
                    }
                });
                itemAdd.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        table.getItems().add(new Whine("NULL", 0));
                    }
                });
                itemAdd2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        table.getItems().add(new Whine("NULL", 0));
                    }
                });
                row.contextMenuProperty().bind(
                        Bindings.when(row.emptyProperty())
                                .then(menuAdd)
                                .otherwise(menuRemove)
                );
                return row;
            }
        });
    }
}
