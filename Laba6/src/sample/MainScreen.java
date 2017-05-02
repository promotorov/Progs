package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.DragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import laba2.FoodResidus;
import laba2.ReadXMLFile;
import laba2.Whine;
import laba2.XMLworker;

import java.util.*;

import static javafx.scene.control.cell.ChoiceBoxTableCell.forTableColumn;


/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreen{
    private static ObservableList<FoodResidus> data;
    private static Callback<TableColumn<FoodResidus, String>, TableCell<FoodResidus, String>> cellFactory;
    private static Stage primaryStage;
    private static AnchorPane mainPane;
    private static AnchorPane leftPane;
    private static AnchorPane rightPane;
    private static TableView<FoodResidus> table;
    private static TableColumn<FoodResidus, String> columnName;
    private static TableColumn<FoodResidus, Integer> columnWeight;
    private static Button buttonFiltr;
    private static Button buttonInfo;
    private static LinkedList<FoodResidus> list;
    private static void drawAncor(){
        mainPane=new AnchorPane();
        leftPane=new AnchorPane();
        AnchorPane.setTopAnchor(leftPane, 0.0);
        AnchorPane.setLeftAnchor(leftPane, 0.0);
        AnchorPane.setBottomAnchor(leftPane, 0.0);
        AnchorPane.setRightAnchor(leftPane, 375.0);
        mainPane.getChildren().add(leftPane);
        rightPane=new AnchorPane();
        AnchorPane.setTopAnchor(rightPane, 0.0);
        AnchorPane.setLeftAnchor(rightPane, 375.0);
        AnchorPane.setBottomAnchor(rightPane, 0.0);
        AnchorPane.setRightAnchor(rightPane, 0.0);
        mainPane.getChildren().add(rightPane);
    }

    private static void setCSS(){
        mainPane.setId("mainPane");
        leftPane.setId("leftPane");
        rightPane.setId("rightPane");
        buttonFiltr.setId("buttonFiltr");
        buttonInfo.setId("buttonInfo");
    }

    private static void drawTable(){
        table=new TableView<>();
        table.setEditable(true);
        columnName=new TableColumn<>("Name");
        columnName.setPrefWidth(162.5);
        columnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnName.setCellFactory(TextFieldTableCell.<FoodResidus>forTableColumn());
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

        columnWeight=new TableColumn<>("Weight");
        columnWeight.setPrefWidth(160);
        columnWeight.setCellValueFactory(new PropertyValueFactory<FoodResidus, Integer>("weight"));
        table.getColumns().addAll(columnName,columnWeight);
        AnchorPane.setTopAnchor(table, 20.0);
        AnchorPane.setBottomAnchor(table, 100.0);
        AnchorPane.setRightAnchor(table, 25.0);
        AnchorPane.setLeftAnchor(table, 25.0);
        leftPane.getChildren().add(table);
    }

    private static void initTable(){
        try {
            list=new LinkedList<>();
            new ReadXMLFile().fillList(list, "src\\sample.xml");
            System.out.println(list.size());
            data = FXCollections.observableArrayList(list);
            table.setItems(data);
            table.setEditable(true);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void drawButton(){
        buttonFiltr=new Button("Фильтровать");
        leftPane.getChildren().add(buttonFiltr);
        AnchorPane.setTopAnchor(buttonFiltr, 480.0);
        AnchorPane.setBottomAnchor(buttonFiltr, 30.0);
        AnchorPane.setLeftAnchor(buttonFiltr, 46.25);
        AnchorPane.setRightAnchor(buttonFiltr, 208.75);

        buttonInfo=new Button("Инфа");
        rightPane.getChildren().add(buttonInfo);
        AnchorPane.setTopAnchor(buttonFiltr, 480.0);
        AnchorPane.setBottomAnchor(buttonFiltr, 30.0);
        AnchorPane.setLeftAnchor(buttonFiltr, 46.25);
        AnchorPane.setRightAnchor(buttonFiltr, 208.75);
    }

    public static void setControllers(){
        MainScreenController.buttonFiltr(buttonFiltr);
        MainScreenController.buttonInfo(buttonInfo);
    }

    public static void loadMainScreen(){
        drawAncor();
        drawTable();
        initTable();
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setScene(new Scene(mainPane, 750, 550));
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
}
