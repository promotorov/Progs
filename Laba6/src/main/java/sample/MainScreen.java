package sample;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import laba2.FoodResidus;
import laba2.XMLworker;

import java.util.HashSet;
import java.util.Iterator;


/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreen{//TODO определить для всех окон максимальные и минимальные размеры
    public static ObservableList<FoodResidus> data;

    private static Stage primaryStage;
    private static Scene scene;
    private static AnchorPane mainPane;
    private static SplitPane splitPane;
    private static AnchorPane leftPane;
    private static HBox leftFilterButtonsContainer;
    private static VBox rightFilterButtonsContainer;
    private static AnchorPane rightPane;
    private static TableView<FoodResidus> table;
    private static TableColumn<FoodResidus, String> columnName;
    private static TableColumn<FoodResidus, Integer> columnWeight;
    private static Button buttonFiler;
    private static Button buttonDelFiler;
    private static Button buttonInfo;
    private static Button buttonClear;
    private static Button buttonRemoveEl;
    private static Button buttonChoose;
    private static Button buttonSave;


    private static void drawPanes(){
        mainPane=new AnchorPane();
        splitPane=new SplitPane();

        leftPane=new AnchorPane();
        AnchorPane.setTopAnchor(leftPane, 0.0);
        AnchorPane.setLeftAnchor(leftPane, 0.0);
        AnchorPane.setBottomAnchor(leftPane, 0.0);
        AnchorPane.setRightAnchor(leftPane, 375.0);
        rightPane=new AnchorPane();
        AnchorPane.setTopAnchor(rightPane, 0.0);
        AnchorPane.setLeftAnchor(rightPane, 375.0);
        AnchorPane.setBottomAnchor(rightPane, 0.0);
        AnchorPane.setRightAnchor(rightPane, 0.0);
        splitPane.getItems().add(leftPane);
        splitPane.getItems().add(rightPane);
        mainPane.getChildren().add(splitPane);
    }

    private static void setCSS(){
        mainPane.setId("mainPane");
        leftPane.setId("leftPane");
        rightPane.setId("rightPane");
        buttonFiler.setId("buttonFilter");
        buttonDelFiler.setId("buttonDelFilter");
        buttonInfo.setId("buttonInfo");
        buttonClear.setId("buttonClear");
        buttonRemoveEl.setId("buttonRemoveEl");
        buttonChoose.setId("buttonChoose");
        buttonSave.setId("buttonSave");
    }

    private static void drawTable(){
        table=new TableView<>();
        table.setEditable(true);
        columnName=new TableColumn<>("Name");
        columnName.setPrefWidth(162.5);
        columnName.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        columnWeight=new TableColumn<>("Weight");
        columnWeight.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        table.getColumns().addAll(columnName,columnWeight);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        AnchorPane.setTopAnchor(table, 20.0);
        AnchorPane.setBottomAnchor(table, 100.0);
        AnchorPane.setRightAnchor(table, 25.0);
        AnchorPane.setLeftAnchor(table, 25.0);

        leftPane.getChildren().add(table);
    }

    private static void initTable(){
        try {
            data = FXCollections.observableArrayList();
            HashSet<FoodResidus> set=XMLworker.getCollection("src\\main\\java\\sample.xml");
            Iterator<FoodResidus> iterator=set.iterator();
            while(iterator.hasNext()){
                data.add(iterator.next());
            }
            table.setItems(data);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("sd");
        }
    }

    public static void drawButton(){
        buttonFiler =new Button("Фильтровать");
        buttonDelFiler =new Button("Удалить фильтр");

        leftFilterButtonsContainer = new HBox();
        leftFilterButtonsContainer.setSpacing(40);
        leftFilterButtonsContainer.setAlignment(Pos.CENTER);
        leftFilterButtonsContainer.getChildren().add(buttonFiler);
        leftFilterButtonsContainer.getChildren().add(buttonDelFiler);

        leftPane.getChildren().add(leftFilterButtonsContainer);
        AnchorPane.setBottomAnchor(leftFilterButtonsContainer, 32.0);
        AnchorPane.setLeftAnchor(leftFilterButtonsContainer, 0.0);
        AnchorPane.setRightAnchor(leftFilterButtonsContainer, 0.0);

        buttonInfo=new Button("Информация");
        buttonClear=new Button("Очистить");
        buttonRemoveEl=new Button("Удалить элементы");
        buttonChoose=new Button("Выбрать файл");
        buttonSave=new Button("Сохранить");
        rightFilterButtonsContainer=new VBox();
        rightFilterButtonsContainer.setAlignment(Pos.CENTER_LEFT);
        rightFilterButtonsContainer.setSpacing(30);
        rightFilterButtonsContainer.getChildren().add(buttonInfo);
        rightFilterButtonsContainer.getChildren().add(buttonClear);
        rightFilterButtonsContainer.getChildren().add(buttonRemoveEl);
        rightFilterButtonsContainer.getChildren().add(buttonChoose);
        rightFilterButtonsContainer.getChildren().add(buttonSave);

        rightPane.getChildren().add(rightFilterButtonsContainer);
        AnchorPane.setTopAnchor(rightFilterButtonsContainer, 0.0);
        AnchorPane.setBottomAnchor(rightFilterButtonsContainer, 0.0);
        AnchorPane.setLeftAnchor(rightFilterButtonsContainer, 30.0);
        AnchorPane.setRightAnchor(rightFilterButtonsContainer, 100.0);
    }

    public static void setControllers(){
        MainScreenController.buttonFiltr(buttonFiler);
        MainScreenController.buttonDelFiltr(buttonDelFiler);
        MainScreenController.buttonInfo(buttonInfo);
        MainScreenController.buttonClear(buttonClear);
        MainScreenController.buttonRemoveEl(buttonRemoveEl);
        MainScreenController.buttonChoose(buttonChoose);
        MainScreenController.buttonSave(buttonSave);
        MainScreenController.editName(columnName, data);
        MainScreenController.editWeight(columnWeight, data);
        MainScreenController.tableViewRightClick(table);
    }

    public static void loadMainScreen(){
        drawPanes();
        drawTable();
        initTable();
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setMinHeight(550.0);
        primaryStage.setMinWidth(750.0);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 750, 550);
        primaryStage.setScene(scene);
        splitPane.prefWidthProperty().bind(scene.widthProperty());
        splitPane.prefHeightProperty().bind(scene.heightProperty());
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
}