package sample;

import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import laba2.FoodResidus;
import laba2.Whine;
import org.controlsfx.control.textfield.TextFields;
import laba2.XMLworker;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import static laba2.JSONworker.toJavaObject;
import static laba2.XMLworker.saveCollection;

/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreenController {
    public static void buttonFiltr(Button button, ObservableList<FoodResidus> data,ObservableList UnSeeingData, TableView<FoodResidus> table){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                for(int i=0; i<data.size(); i++){
                    data.get(i).setHighlightProperty(false);
                    data.get(i).setActivehighlightProperty(false);
                }
                MainScreen.getColumnName().setVisible(false);
                MainScreen.getColumnName().setVisible(true);
                SetFiltersWindow.loadSetFiltersWindow(data, UnSeeingData, table);
                System.out.println("Удаляем фильтры");
            }
        });
    }
    /*public static void buttonDelFiltr(Button button, ObservableList data, ObservableList UnSeeingData,TableView<FoodResidus> table){
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
    }*/
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
                    ObservableList<FoodResidus> oldTemp=FXCollections.observableArrayList(data);
                    FoodResidus jsonCompared = toJavaObject(RemoveElWindow.textField.getText());
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
                        boolean add=true;
                        for(int b=0; b<listView.getItems().size(); b++){
                            if(listView.getItems().get(b).equals(selectedFiles.get(i).getAbsolutePath())) add=false;
                        }
                        if(add)listView.getItems().add(selectedFiles.get(i).getAbsolutePath());
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
                ObservableList<FoodResidus> temp=FXCollections.observableArrayList(data);
                ClearChange clearChange=new ClearChange(temp);
                TableStatements.addChange(clearChange);
                data.clear();
                Stage stage = (Stage) ClearWindow.ClearOKbutton.getScene().getWindow();
                stage.close();
                MainScreen.checkHighlight();
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
    public static void buttonOkInfo(Button button, TextArea textArea) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) InfoApplicationWindow.buttonOkInfo.getScene().getWindow();
                InfoApplicationWindow.setTeext(textArea.getText());
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
    public static void SetFiltersOKbutton(Button button, ObservableList<FoodResidus> data,ObservableList UnSeeingData,TableView<FoodResidus> table, TextField textFieldName, TextField textFieldWeight){
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
                                    ObservableList<FoodResidus> oldTemp=FXCollections.observableArrayList(data);
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
                                    MainScreen.checkHighlight();
                                    RemoveGreatestChange r=new RemoveGreatestChange(newTemp, oldTemp);
                                    TableStatements.addChange(r);
                                }else{
                                    ErrorWindow.loadInfoScreen("Неверный формат фильтра");
                                }
                            }
                        });
                        Stage stage = (Stage) SetFiltersWindow.SetFiltersOKbutton.getScene().getWindow();
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
                            if(!t.getOldValue().equals(t.getNewValue())) {
                                String temp=MainScreen.getNameSearch().getText().trim();
                                int len=temp.length();
                                if(len>t.getNewValue().trim().length()) {
                                    data.get(t.getTablePosition().getRow()).setActivehighlightProperty(false);
                                    data.get(t.getTablePosition().getRow()).setHighlightProperty(false);
                                }
                                else if(!t.getNewValue().trim().substring(0,len).equals(temp)) {
                                    data.get(t.getTablePosition().getRow()).setHighlightProperty(false);
                                    data.get(t.getTablePosition().getRow()).setActivehighlightProperty(false);
                                }
                                else if(len!=0) data.get(t.getTablePosition().getRow()).setHighlightProperty(true);
                                else  {
                                    data.get(t.getTablePosition().getRow()).setActivehighlightProperty(false);
                                    data.get(t.getTablePosition().getRow()).setHighlightProperty(false);
                                }
                                Whine oldTemp = new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                        t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                                t.getTableView().getItems().get(
                                        t.getTablePosition().getRow()).setName(t.getNewValue().trim());
                                Whine newTemp = new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                        t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                                EditChange editChange = new EditChange(newTemp, oldTemp, t.getTablePosition().getRow());
                                TableStatements.addChange(editChange);
                                columnName.setVisible(false);
                                columnName.setVisible(true);
                            }
                            columnName.setCellFactory(TextFieldTableCell.forTableColumn());
                        }else {
                            Whine oldTemp=new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                    t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                            t.getTableView().getItems().get(
                                t.getTablePosition().getRow()).setName("Безыменный");
                            Whine newTemp=new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                    t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                            EditChange editChange=new EditChange(newTemp, oldTemp, t.getTablePosition().getRow());

                            if(MainScreen.getNameSearch().getText().trim().equals("Безыменный")) data.get(t.getTablePosition().getRow()).setHighlightProperty(true);
                            else {
                                data.get(t.getTablePosition().getRow()).setHighlightProperty(false);
                                data.get(t.getTablePosition().getRow()).setActivehighlightProperty(false);
                                }
                            columnName.setVisible(false);
                            columnName.setVisible(true);
                            TableStatements.addChange(editChange);
                            columnName.setCellFactory(TextFieldTableCell.forTableColumn());
                        }
                    }
                }
        );
        columnName.setOnEditStart(
                new EventHandler<TableColumn.CellEditEvent<FoodResidus, String>>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent<FoodResidus, String> t) {
                        TextField tt=new TextField();
                        tt.setText("dddddd");
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
                                if(!t.getOldValue().equals(t.getNewValue())) {
                                    System.out.println("BRRRRRRRRRRRRRRRR");
                                    System.out.println("new Value:" + t.getNewValue());
                                    Whine oldTemp = new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                                    t.getTableView().getItems().get(
                                            t.getTablePosition().getRow()).setWeight(t.getNewValue());
                                    System.out.println("SUCSESSS");
                                    Whine newTemp = new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                                    EditChange editChange = new EditChange(newTemp, oldTemp, t.getTablePosition().getRow());
                                    TableStatements.addChange(editChange);
                                    if(MainScreen.getWeightSearch().getText().trim().length()!=0) {
                                        if (Integer.parseInt(MainScreen.getWeightSearch().getText()) != t.getNewValue()) {
                                            data.get(t.getTablePosition().getRow()).setHighlightProperty(false);
                                        }
                                        else data.get(t.getTablePosition().getRow()).setHighlightProperty(true);
                                    }
                                    else data.get(t.getTablePosition().getRow()).setHighlightProperty(false);
                                    columnWeight.setVisible(false);
                                    columnWeight.setVisible(true);
                                }
                            }
                            catch (Exception e){
                                if(t.getOldValue()!=0) {
                                    Whine oldTemp = new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                                    t.getTableView().getItems().get(t.getTablePosition().getRow()).setWeight(0);
                                    Whine newTemp = new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                                    EditChange editChange = new EditChange(newTemp, oldTemp, t.getTablePosition().getRow());
                                    TableStatements.addChange(editChange);
                                    if(Integer.parseInt(MainScreen.getWeightSearch().getText().trim())==0) data.get(t.getTablePosition().getRow()).setHighlightProperty(true);
                                    else data.get(t.getTablePosition().getRow()).setHighlightProperty(false);
                                }
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
                    TableRow<FoodResidus> row = new TableRow<FoodResidus>() {
                        @Override
                        protected void updateItem( FoodResidus person, boolean b ) {
                            super.updateItem( person, b );
                            if ( person == null )
                                return;
                            if ( person.isHighlightProperty() ) {
                                setStyle( "-fx-background-color: #EDFB23;" );
                            } else {
                                setStyle( null );
                            }
                            if( person.isActivehighlightProperty()){
                                setStyle("-fx-background-color: #33D217;");
                            }
                        }

                    };
                    MenuItem itemRemove=new MenuItem("Remove");
                    MenuItem itemAdd=new MenuItem("Add");
                    MenuItem itemAdd2=new MenuItem("Add");
                    ContextMenu menuRemove=new ContextMenu();
                    ContextMenu menuAdd=new ContextMenu();
                    menuRemove.getItems().addAll(itemRemove, itemAdd2);
                    menuAdd.getItems().addAll(itemAdd);;
                    itemRemove.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            System.out.println(MainScreen.isVisiable());
                            Whine temp=new Whine(row.getItem().getName(), row.getItem().getWeight());
                            RemoveChange removeChange=new RemoveChange(temp, row.getIndex());
                            TableStatements.addChange(removeChange);
                            table.getItems().remove(row.getItem());
                            MainScreen.checkHighlight();
                        }
                    });
                    itemAdd.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            table.getItems().add(new Whine("NULL", 0));
                            AddChange addChange=new AddChange(new Whine("NULL", 0));
                            TableStatements.addChange(addChange);
                            MainScreen.checkHighlight();
                        }
                    });
                    itemAdd2.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            table.getItems().add(new Whine("NULL", 0));
                            AddChange addChange=new AddChange(new Whine("NULL", 0));
                            TableStatements.addChange(addChange);
                            MainScreen.checkHighlight();
                        }
                    });
                    row.contextMenuProperty().bind(

                            Bindings.when(row.emptyProperty())

                                    .then(menuAdd)

                                    .otherwise(menuRemove)

                    );
                    return  row;
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

    public static void addIntoEmptyTable(Pane pane, ObservableList data){
        pane.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent click) {
                if (click.getButton() == MouseButton.SECONDARY && data.isEmpty()) {
                    AddChange addChange=new AddChange(new Whine("NULL", 0));
                    TableStatements.addChange(addChange);
                    data.add(new Whine("Безыменный", 0));
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

    public static void buttonUndo(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Undo");
                TableStatements.undo();
            }
        });
    }

    public static void buttonRedo(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Redo");
                TableStatements.redo();
            }
        });
    }

    public static void buttonWeightUp(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            ObservableList<FoodResidus> data=MainScreen.getData();
            @Override
            public void handle(ActionEvent event) {
                System.out.println("nameUp");
                Collections.sort(data);
                TableStatements.getChanges().clear();
                TableStatements.setCurrentStatement(-1);
            }
        });
    }

    public static void buttonWeightDown(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            ObservableList<FoodResidus> data=MainScreen.getData();
            @Override
            public void handle(ActionEvent event) {
                System.out.println("namedown");
                TableStatements.redo();
                Collections.sort(data, Collections.reverseOrder());
                TableStatements.getChanges().clear();
                TableStatements.setCurrentStatement(-1);
            }
        });
    }

    public static void buttonNameUp(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            ObservableList<FoodResidus> data=MainScreen.getData();
            @Override
            public void handle(ActionEvent event) {
                System.out.println("weigthup");
                TableStatements.redo();
                Collections.sort(data, new SortedByName());
                TableStatements.getChanges().clear();
                TableStatements.setCurrentStatement(-1);
            }
        });
    }

    public static void buttonNameDown(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            ObservableList<FoodResidus> data=MainScreen.getData();
            @Override
            public void handle(ActionEvent event) {
                System.out.println("weigthdown");
                TableStatements.redo();
                Collections.sort(data, Collections.reverseOrder(new SortedByName()));
                TableStatements.getChanges().clear();
                TableStatements.setCurrentStatement(-1);
            }
        });
    }

    public static void autoFill(TextField textField){
        TextFields.bindAutoCompletion(textField, Dictionarz.getWords());

    }

    public static void autoComplet(TextArea textArea){;
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String word;
                        String subWord;
                        boolean contains;
                        word = "";
                        subWord = "";
                        contains = false;
                        int newSize = textArea.getText().length();
                        if (newSize >= InfoApplicationWindow.getOldsize()) {
                            //System.out.println(textArea.getText());
                            char prob = ' ';
                            int last = textArea.getText().lastIndexOf(prob);
                            String temp = textArea.getText().substring(last + 1);
                            HashSet set = new Dictionary().getDictionary();
                            Iterator<String> iterator = set.iterator();
                            while (iterator.hasNext()) {
                                word = iterator.next();
                                if (word.length() <= temp.length() || temp.length() == 0) continue;
                                subWord = word.substring(0, temp.length());
                                if (subWord.equals(temp)) {
                                    contains = true;
                                    break;
                                }
                            }
                            if (contains == true) {
                                textArea.appendText(word.substring(temp.length()));
                                int sub=0;
                                sub=textArea.getText().length()-word.substring(temp.length()).length();
                                textArea.selectRange(sub,textArea.getText().length());
                            }
                        }
                        InfoApplicationWindow.setOldsize(textArea.getText().length());
                    }
                });
            }
        });
    }
}
