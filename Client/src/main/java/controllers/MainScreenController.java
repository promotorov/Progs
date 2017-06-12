package controllers;

import changes.*;
import filters.SortedByName;
import io.Loadtable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.converter.IntegerStringConverter;
import items.FoodResidus;
import items.Whine;
import screens.*;
import screens.MainScreen;
import java.io.File;
import java.util.*;
import static serealize.XMLworker.saveCollection;

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
                MainScreen.getInstace().getColumnName().setVisible(false);
                MainScreen.getInstace().getColumnName().setVisible(true);
                SetFiltersWindow.getInstace().loadSetFiltersWindow(data, UnSeeingData, table);
                System.out.println("Удаляем фильтры");
            }
        });
    }

    public static void buttonInfo(Button button,ObservableList data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Выводим инфу");
                InfoWindow.getInstace().loadInfoWindow(data);
            }
        });
    }

    public static void buttonRemoveEl(Button button, ObservableList data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Удаляем элементы");
                RemoveElWindow.getInstace().loadRemoveElWindow(data);
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
                SaveWindow.getInstace().loadSaveWindow(data);
            }
        });
    }

    public static void buttonSearch(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!MainScreen.getInstace().getSearchContainer().getChildren().contains(MainScreen.getInstace().getNameSearch())) {
                    MainScreen.getInstace().getSearchContainer().getChildren().add(MainScreen.getInstace().getNameSearch());
                    MainScreen.getInstace().getSearchContainer().getChildren().add(MainScreen.getInstace().getWeightSearch());
                    MainScreen.getInstace().getSearchContainer().getChildren().add(MainScreen.getInstace().getCount());
                    for(int i=0; i<MainScreen.getInstace().getData().size(); i++){
                        MainScreen.getInstace().getTable().getItems().get(i).setShowContextMenu(false);
                    }
                }
                else {
                    MainScreen.getInstace().getSearchContainer().getChildren().remove(MainScreen.getInstace().getNameSearch());
                    MainScreen.getInstace().getSearchContainer().getChildren().remove(MainScreen.getInstace().getWeightSearch());
                    MainScreen.getInstace().getSearchContainer().getChildren().remove(MainScreen.getInstace().getCount());
                    MainScreen.getInstace().getSearchContainer().getChildren().removeAll(MainScreen.getInstace().getButtonBack(), MainScreen.getInstace().getButtonNext());
                    for(int i=0; i<MainScreen.getInstace().getData().size(); i++){
                        MainScreen.getInstace().getTable().getItems().get(i).setShowContextMenu(true);
                    }
                    for (int i=0; i<MainScreen.getInstace().getData().size(); i++){
                        MainScreen.getInstace().getTable().getItems().get(i).setHighlightProperty(false);
                    }
                    MainScreen.getInstace().getNameSearch().clear();
                    MainScreen.getInstace().getWeightSearch().clear();
                    MainScreen.getInstace().getCount().setText("");
                    MainScreen.getInstace().getColumnName().setVisible(false);
                    MainScreen.getInstace().getColumnName().setVisible(true);
                }
                MainScreen.getInstace().getTable().refresh();
            }
        });
    }

    public static void buttonBack(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(MainScreen.getInstace().getCurrentSearch()!=0){
                    MainScreen.getInstace().getTable().getItems().get(MainScreen.getInstace().getSearched().get(MainScreen.getInstace().getCurrentSearch())).setActivehighlightProperty(false);
                    MainScreen.getInstace().setCurrentSearch(MainScreen.getInstace().getCurrentSearch()-1);
                    MainScreen.getInstace().getTable().scrollTo(MainScreen.getInstace().getSearched().get(MainScreen.getInstace().getCurrentSearch()));
                    MainScreen.getInstace().getTable().getItems().get(MainScreen.getInstace().getSearched().get(MainScreen.getInstace().getCurrentSearch())).setActivehighlightProperty(true);
                    MainScreen.getInstace().getCount().setText((MainScreen.getInstace().getCurrentSearch()+1)+" из "+MainScreen.getInstace().getSearched().size());
                }
                else {
                    MainScreen.getInstace().getTable().getItems().get(MainScreen.getInstace().getSearched().get(MainScreen.getInstace().getCurrentSearch())).setActivehighlightProperty(false);
                    MainScreen.getInstace().setCurrentSearch(MainScreen.getInstace().getSearched().size()-1);
                    MainScreen.getInstace().getTable().scrollTo(MainScreen.getInstace().getSearched().get(MainScreen.getInstace().getSearched().size()-1));
                    MainScreen.getInstace().getCount().setText((MainScreen.getInstace().getCurrentSearch()+1) + " из " + MainScreen.getInstace().getSearched().size());
                    MainScreen.getInstace().getTable().getItems().get(MainScreen.getInstace().getSearched().get(MainScreen.getInstace().getCurrentSearch())).setActivehighlightProperty(true);
                }
                MainScreen.getInstace().getColumnName().setVisible(false);
                MainScreen.getInstace().getColumnName().setVisible(true);
            }
        });
    }

    public static void buttonNext(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int currentSearch=MainScreen.getInstace().getCurrentSearch();
                ArrayList<Integer> searched=MainScreen.getInstace().getSearched();
                ObservableList<FoodResidus> data=MainScreen.getInstace().getTable().getItems();
                TableView<FoodResidus> table = MainScreen.getInstace().getTable();
                Text count = MainScreen.getInstace().getCount();
                TableColumn columnName = MainScreen.getInstace().getColumnName();
                if(currentSearch!=(searched.size()-1)){
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(false);
                    currentSearch++;
                    MainScreen.getInstace().setCurrentSearch(currentSearch);
                    System.out.println(currentSearch);
                    table.scrollTo(searched.get(currentSearch));
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(true);
                    count.setText((currentSearch+1)+" из "+searched.size());
                }
                else {
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(false);
                    currentSearch=0;
                    MainScreen.getInstace().setCurrentSearch(currentSearch);
                    table.scrollTo(searched.get(0));
                    count.setText((currentSearch+1) + " из " + searched.size());
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(true);
                }
                columnName.setVisible(false);
                columnName.setVisible(true);
            }
        });
    }

    public static void addListenersToTextControllers(TextField...textFields){
        for(int i=0; i<textFields.length; i++){
            textFields[i].textProperty().addListener(new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    checkHighlight();
                }
            });
        }
    }

    public static void checkHighlight(){
        ArrayList<Integer> searched=MainScreen.getInstace().getSearched();
        ObservableList<FoodResidus> data=MainScreen.getInstace().getTable().getItems();
        TableView<FoodResidus> table = MainScreen.getInstace().getTable();
        Text count = MainScreen.getInstace().getCount();
        TableColumn columnName = MainScreen.getInstace().getColumnName();
        TextField nameSearch = MainScreen.getInstace().getNameSearch();
        TextField weightSearch = MainScreen.getInstace().getWeightSearch();
        HBox search = MainScreen.getInstace().getSearchContainer();
        Button buttonBack = MainScreen.getInstace().getButtonBack();
        Button buttonNext = MainScreen.getInstace().getButtonNext();
        searched.clear();
        int index=-1;
        for (int i=0; i<data.size(); i++){
            data.get(i).setHighlightProperty(false);
            data.get(i).setActivehighlightProperty(false);
        }
        int co=0;
        count.setText("");
        if(nameSearch.getText().trim().length()!=0 && weightSearch.getText().trim().length()!=0) {
            for (int i = 0; i < data.size(); i++) {
                try {
                    int length = nameSearch.getText().trim().length();
                    if (length > data.get(i).getName().length()) continue;
                    if (length != 0) {
                        if (data.get(i).getWeight() == Integer.parseInt(weightSearch.getText()) &&
                                data.get(i).getName().substring(0, length).equals(nameSearch.getText().trim())) {
                            data.get(i).setHighlightProperty(true);
                            searched.add(i);
                            if(index==-1) index=i;
                            co++;
                        }
                    } else {
                        if (data.get(i).getWeight() == Integer.parseInt(weightSearch.getText())) {
                            data.get(i).setHighlightProperty(true);
                            co++;
                            searched.add(i);
                            if(index==-1) index=i;
                        }
                    }
                } catch (Exception e) {

                }
            }
        }
        else if(nameSearch.getText().trim().length()!=0 && weightSearch.getText().trim().length()==0){
            for (int i = 0; i < data.size(); i++) {
                try {
                    int length = nameSearch.getText().trim().length();
                    if (length == 0) break;
                    if (length > data.get(i).getName().length()) continue;
                    if (data.get(i).getName().substring(0, length).equals(nameSearch.getText().trim())) {
                        data.get(i).setHighlightProperty(true);
                        co++;
                        searched.add(i);
                        if(index==-1) index=i;
                    }
                } catch (Exception e) {

                }
            }
        }
        else if(nameSearch.getText().trim().length()==0 && weightSearch.getText().trim().length()!=0){
            for (int i = 0; i < data.size(); i++) {
                try {
                    if (data.get(i).getWeight() == Integer.parseInt(weightSearch.getText())) {
                        data.get(i).setHighlightProperty(true);
                        if(index==-1) index=i;
                        co++;
                        searched.add(i);
                    }
                } catch (Exception e) {

                }
            }
        }
        else if(nameSearch.getText().trim().length()==0 && weightSearch.getText().trim().length()==0){
            for(int i=0; i<data.size(); i++){
                data.get(i).setHighlightProperty(false);
            }
        }
        if(index!=-1) table.scrollTo(index);
        if(co!=0) {
            count.setText("1 из "+co);
            data.get(index).setActivehighlightProperty(true);
            if(!search.getChildren().contains(buttonBack)){
                search.getChildren().addAll(buttonBack, buttonNext);
            }
        }
        else if (co==0){
            count.setText("Нет совпадений");
            if(search.getChildren().contains(buttonBack)){
                search.getChildren().removeAll(buttonBack, buttonNext);
            }
            for(int i=0; i<data.size(); i++){
                data.get(i).setActivehighlightProperty(false);
            }
        }
        columnName.setVisible(false);
        columnName.setVisible(true);
    }

    public static void buttonClear(Button button, ObservableList data){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Очищаем");
                ClearWindow.getInstace().loadClearWindow(data);
            }
        });
    }

    public static void buttonInfoApplication(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Выводим инфу о приложении");
                InfoApplicationWindow.getInstace().loadInfoScreen();
            }
        });
    }

    public static void buttonSettings(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                SettingsWindow.getInstance().loadMainScreen();
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
                                String temp=MainScreen.getInstace().getNameSearch().getText().trim();
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

                            if(MainScreen.getInstace().getNameSearch().getText().trim().equals("Безыменный")) data.get(t.getTablePosition().getRow()).setHighlightProperty(true);
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
                                    Whine oldTemp = new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                                    t.getTableView().getItems().get(
                                            t.getTablePosition().getRow()).setWeight(t.getNewValue());
                                    Whine newTemp = new Whine(t.getTableView().getItems().get(t.getTablePosition().getRow()).getName(),
                                            t.getTableView().getItems().get(t.getTablePosition().getRow()).getWeight());
                                    EditChange editChange = new EditChange(newTemp, oldTemp, t.getTablePosition().getRow());
                                    TableStatements.addChange(editChange);
                                    if(MainScreen.getInstace().getWeightSearch().getText().trim().length()!=0) {
                                        if (Integer.parseInt(MainScreen.getInstace().getWeightSearch().getText()) != t.getNewValue()) {
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
                                    if(Integer.parseInt(MainScreen.getInstace().getWeightSearch().getText().trim())==0) data.get(t.getTablePosition().getRow()).setHighlightProperty(true);
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
                TableRow<FoodResidus> row = new TableRow<FoodResidus>() {
                    @Override
                    protected void updateItem( FoodResidus person, boolean b ) {
                        super.updateItem( person, b );
                        MenuItem itemRemove=new MenuItem("Remove");
                        MenuItem itemAdd=new MenuItem("Add");
                        MenuItem itemAdd2=new MenuItem("Add");
                        ContextMenu menuRemove=new ContextMenu();
                        ContextMenu menuAdd=new ContextMenu();
                        menuRemove.getItems().addAll(itemRemove, itemAdd2);
                        menuAdd.getItems().addAll(itemAdd);
                        itemAdd.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                table.getItems().add(new Whine("NULL", 0));
                                AddChange addChange=new AddChange(new Whine("NULL", 0));
                                TableStatements.addChange(addChange);
                                checkHighlight();
                            }
                        });
                        if ( person == null ) {
                            if(getTableView().getItems().size()>0) {
                                if (getTableView().getItems().get(0).isShowContextMenu())
                                    setContextMenu(menuAdd);
                            }
                            return;
                        }
                        if ( person.isHighlightProperty() ) {
                            setStyle( "-fx-background-color: #EDFB23;" );
                        } else {
                            setStyle( null );
                        }
                        if( person.isActivehighlightProperty()){
                            setStyle("-fx-background-color: #33D217;");
                        }
                        if (person.isShowContextMenu()){
                            setContextMenu(menuRemove);
                        }
                        itemRemove.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                System.out.println(MainScreen.getInstace().isVisiable());
                                Whine temp=new Whine(getItem().getName(), getItem().getWeight());
                                RemoveChange removeChange=new RemoveChange(temp, getIndex());
                                TableStatements.addChange(removeChange);
                                table.getItems().remove(getItem());
                                checkHighlight();
                            }
                        });

                        itemAdd2.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                table.getItems().add(new Whine("NULL", 0));
                                AddChange addChange=new AddChange(new Whine("NULL", 0));
                                TableStatements.addChange(addChange);
                                checkHighlight();
                            }
                        });
                    }

                };
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
                    Loadtable loadtable=new Loadtable("load", data, MainScreen.getInstace().getTable(),filename);
                    loadtable.start();
                    MainScreen.getInstace().getTable().setItems(data);
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
            ObservableList<FoodResidus> data=MainScreen.getInstace().getData();
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
            ObservableList<FoodResidus> data=MainScreen.getInstace().getData();
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
            ObservableList<FoodResidus> data=MainScreen.getInstace().getData();
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
            ObservableList<FoodResidus> data=MainScreen.getInstace().getData();
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

}