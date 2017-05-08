package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import laba2.FoodResidus;
import laba2.XMLworker;

import java.util.HashSet;
import java.util.Iterator;


/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreen{//TODO определить для всех окон максимальные и минимальные размеры
    private static ObservableList<FoodResidus> data;
    private static ObservableList UnSeeingData;

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
    private static HBox ListViewContainer;
    private static ListView listView;
    private static Button buttonFiler;
    private static Button buttonDelFiler;
    private static Button buttonInfo;
    private static Button buttonClear;
    private static Button buttonRemoveEl;
    private static Button buttonChoose;
    private static Button buttonSave;
    private static HBox SettingsContainer;
    private static Button buttonSettings;
    private static Button buttonInfoApplication;


    private static void drawPanes(){
        mainPane=new AnchorPane();
        splitPane=new SplitPane();
        leftPane=new AnchorPane();
        rightPane=new AnchorPane();
        splitPane.getItems().add(leftPane);
        splitPane.getItems().add(rightPane);
        mainPane.getChildren().add(splitPane);
    }

    private static void setCSS(){
        mainPane.setId("mainPane");
        leftPane.setId("leftPane");
        rightPane.setId("rightPane");
        buttonFiler.setId("button");
        buttonDelFiler.setId("button");
        buttonInfo.setId("button");
        buttonClear.setId("button");
        buttonRemoveEl.setId("button");
        buttonChoose.setId("button");
        buttonSave.setId("button");
        buttonInfoApplication.setId("buttonInfoApplication");
        buttonSettings.setId("buttonSettings");
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
            HashSet<FoodResidus> set=XMLworker.getCollection("src\\main\\resources\\sample.xml");
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

    public static void drawItems(){
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
        buttonChoose=new Button("Выбрать файлы");
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

        buttonSettings=new Button();
        Image imageSettings=new Image("/icons/settings.png", 32, 32, false ,false);
        buttonSettings.setGraphic(new ImageView(imageSettings));
        buttonInfoApplication=new Button();
        Image imageInfo=new Image("/icons/info.png", 32, 32, false, false);
        buttonInfoApplication.setGraphic(new ImageView(imageInfo));
        SettingsContainer=new HBox();
        SettingsContainer.setAlignment(Pos.CENTER_RIGHT);
        SettingsContainer.setSpacing(8);
        SettingsContainer.getChildren().addAll(buttonInfoApplication, buttonSettings);
        rightPane.getChildren().add(SettingsContainer);
        AnchorPane.setTopAnchor(SettingsContainer, 10.0);
        AnchorPane.setRightAnchor(SettingsContainer, 10.0);
        AnchorPane.setLeftAnchor(SettingsContainer, 0.0);
        AnchorPane.setRightAnchor(rightFilterButtonsContainer, 280.0);

        ListViewContainer=new HBox();
        listView=new ListView();
        ListViewContainer.getChildren().add(listView);
        ListViewContainer.setAlignment(Pos.CENTER);
        rightPane.getChildren().add(ListViewContainer);
        AnchorPane.setTopAnchor(ListViewContainer, 100.0);
        AnchorPane.setBottomAnchor(ListViewContainer, 100.0);
        AnchorPane.setLeftAnchor(ListViewContainer, 170.0);
        AnchorPane.setRightAnchor(ListViewContainer, 0.0);

    }

    public static void setControllers(){
        UnSeeingData = FXCollections.observableArrayList();
        MainScreenController.buttonFiltr(buttonFiler, data,UnSeeingData, table);
        MainScreenController.buttonDelFiltr(buttonDelFiler, data, UnSeeingData, table);
        MainScreenController.buttonInfo(buttonInfo, data);
        MainScreenController.buttonClear(buttonClear, data);
        MainScreenController.buttonRemoveEl(buttonRemoveEl, data);
        MainScreenController.buttonChoose(buttonChoose,listView);
        MainScreenController.buttonSave(buttonSave, data);
        MainScreenController.editName(columnName, data);
        MainScreenController.editWeight(columnWeight, data);
        MainScreenController.tableViewRightClick(table);
        MainScreenController.buttonInfoApplication(buttonInfoApplication);
        MainScreenController.buttonSettings(buttonSettings);
    }

    public static void loadMainScreen(){
        drawPanes();
        drawTable();
        initTable();
        drawItems();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setMinHeight(550.0);
        primaryStage.setMinWidth(950.0);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        primaryStage.setTitle("Остатки еды");
        scene=new Scene(mainPane, 950, 550);
        primaryStage.setScene(scene);
        splitPane.prefWidthProperty().bind(scene.widthProperty());
        splitPane.prefHeightProperty().bind(scene.heightProperty());
        primaryStage.getScene().getStylesheets().add("/css/Main.css");
        primaryStage.show();
    }

    public static Stage getStage(){
        return primaryStage;
    }
    public static Button getButtonInfo(){
        return buttonInfo;
    }

    public static Button getButtonFiler() {
        return buttonFiler;
    }

    public static void setButtonFiler(Button buttonFiler) {
        MainScreen.buttonFiler = buttonFiler;
    }

    public static Button getButtonDelFiler() {
        return buttonDelFiler;
    }

    public static void setButtonDelFiler(Button buttonDelFiler) {
        MainScreen.buttonDelFiler = buttonDelFiler;
    }

    public static void setButtonInfo(Button buttonInfo) {
        MainScreen.buttonInfo = buttonInfo;
    }

    public static Button getButtonClear() {
        return buttonClear;
    }

    public static void setButtonClear(Button buttonClear) {
        MainScreen.buttonClear = buttonClear;
    }

    public static Button getButtonRemoveEl() {
        return buttonRemoveEl;
    }

    public static void setButtonRemoveEl(Button buttonRemoveEl) {
        MainScreen.buttonRemoveEl = buttonRemoveEl;
    }

    public static Button getButtonChoose() {
        return buttonChoose;
    }

    public static void setButtonChoose(Button buttonChoose) {
        MainScreen.buttonChoose = buttonChoose;
    }

    public static Button getButtonSave() {
        return buttonSave;
    }

    public static void setButtonSave(Button buttonSave) {
        MainScreen.buttonSave = buttonSave;
    }

    public static Button getButtonSettings() {
        return buttonSettings;
    }

    public static void setButtonSettings(Button buttonSettings) {
        MainScreen.buttonSettings = buttonSettings;
    }

    public static Button getButtonInfoApplication() {
        return buttonInfoApplication;
    }

    public static void setButtonInfoApplication(Button buttonInfoApplication) {
        MainScreen.buttonInfoApplication = buttonInfoApplication;
    }

    public static TableView<FoodResidus> getTable() {
        return table;
    }
}