package sample;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
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
    public static void buttonFiltr(Button button, ObservableList data,ObservableList UnSeeingData, TableView<FoodResidus> table){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SetFiltersWindow.loadSetFiltersWindow(data, UnSeeingData, table);
                System.out.println("Удаляем фильтры");
            }
        });
    }
    public static void buttonDelFiltr(Button button, ObservableList data, ObservableList UnSeeingData,TableView<FoodResidus> table){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Iterator<FoodResidus> iterator=UnSeeingData.iterator();
                    while(iterator.hasNext()){
                        data.add(iterator.next());
                    }
                    table.setItems(data);
                }
                catch (Exception e){
                    System.out.println(e.getMessage());
                }
                UnSeeingData.clear();
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
    public static void RemoveElOKbutton(Button button, ObservableList<FoodResidus> data){
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
                FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML файлы", "*.xml"));
                File selectedFile = fileChooser.showOpenDialog(null);
                SaveTable saveTable = new SaveTable("save", data, selectedFile);
                saveTable.start();
                Stage stage = (Stage) SaveWindow.SaveChooseButton.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void SaveDefaultButton(Button button, ObservableList<FoodResidus> data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SaveTable saveTable=new SaveTable("saveDefault", data);
                saveTable.start();
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
    public static void buttonOkSucsessfull(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) SaveSucsessfullScreen.getButtonOkInfo().getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void buttonInfoApplication(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Выводим инфу о приложении");
                InfoApplicationWindow.loadInfoScreen();
            }
        });
    }
    public static void buttonSettings(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SettingsWindow.loadMainScreen();
            }
        });
    }
    public static void buttonOkInfo(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) InfoApplicationWindow.buttonOkInfo.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void buttonOkInfoError(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) ErrorWindow.buttonOkInfo.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void SetFiltersOKbutton(Button button, ObservableList data,ObservableList UnSeeingData,TableView<FoodResidus> table, TextField textFieldName, TextField textFieldWeight){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(CompareMethods.isCorrect(textFieldName.getText(),textFieldWeight.getText())){
                    Iterator<FoodResidus> iterator=data.iterator();
                    while(iterator.hasNext()){
                        FoodResidus CurentItrator = iterator.next();
                        if((CompareMethods.nameCompare(CurentItrator.getName(),textFieldName.getText()))&&(CompareMethods.weightCompare(CurentItrator.getWeight(),textFieldWeight.getText()))){
                        }else{
                            UnSeeingData.add(CurentItrator);
                            iterator.remove();
                        }
                    }
                }else{
                    ErrorWindow.loadInfoScreen("Неверный формат фильтра");
                }
                table.setItems(data);
                Stage stage = (Stage) SetFiltersWindow.SetFiltersOKbutton.getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void SetFiltersCancelButton(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) SetFiltersWindow.SetFiltersCancelButton.getScene().getWindow();
                stage.close();
                System.out.println("отмена");
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
                        if(t.getNewValue().length()!=0) {
                            t.getTableView().getItems().get(
                                    t.getTablePosition().getRow()).setName(t.getNewValue());
                        }else {
                            t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setName("Безыменный");
                            columnName.setCellFactory(TextFieldTableCell.forTableColumn());
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
                            try {
                                t.getTableView().getItems().get(
                                        t.getTablePosition().getRow()).setWeight(t.getNewValue());
                            }
                            catch (Exception e){
                                t.getTableView().getItems().get(t.getTablePosition().getRow()).setWeight(0);
                                columnWeight.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
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

    public static void openListItem(ListView list, ObservableList data){
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getClickCount() == 2) {
                    String filename = list.getSelectionModel().getSelectedItem().toString();
                    Loadtable loadtable=new Loadtable("load", data, MainScreen.getTable(),filename);
                    loadtable.start();
                    MainScreen.getTable().setItems(data);
                }
            }
        });
    }

    public static void buttonOkError(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) ErrorWindow.getButtonOkInfo().getScene().getWindow();
                stage.close();
            }
        });
    }
}
