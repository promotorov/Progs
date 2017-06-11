package screens;

import controllers.MainScreenController;
import io.InitTable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import items.FoodResidus;
import any.NumberTextfield;

import java.util.*;

/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreen{//TODO написать синглтон
    private BooleanProperty visiable=new SimpleBooleanProperty();
    private ArrayList<Integer> searched=new ArrayList<>();
    private int currentSearch=0;
    private ObservableList<FoodResidus> data;
    private ObservableList UnSeeingData;
    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainPane;
    private SplitPane splitPane;
    private AnchorPane leftPane;
    private HBox leftButtonsContainer;
    private VBox rightButtonsContainer;
    private AnchorPane rightPane;
    private TextField nameSearch;
    private NumberTextfield weightSearch;
    private TableView<FoodResidus> table;
    private TableColumn<FoodResidus, String> columnName;
    private TableColumn<FoodResidus, Integer> columnWeight;
    private HBox ListViewContainer;
    private ListView listView;
    private Button buttonFilter;
    private Button buttonInfo;
    private Button buttonClear;
    private Button buttonRemoveEl;
    private Button buttonChoose;
    private Button buttonSave;
    private HBox topRightCornerContainer;
    private Button buttonSettings;
    private Button buttonInfoApplication;
    private Button buttonUndo;
    private Button buttonSearch;
    private Button buttonRedo;
    private Text count;
    private HBox topLeftCornerContainer;
    private Button buttonWeigthUp;
    private Button buttonWeigthDown;
    private Button buttonNameUp;
    private Button buttonNameDown;
    private Button buttonBack;
    private Button buttonNext;
    private HBox searchContainer;
    private static MainScreen mainScreen;

    private MainScreen(){}
    public static synchronized MainScreen getInstace(){
        if(mainScreen==null){
            mainScreen=new MainScreen();
        }
        return mainScreen;
    }

    private void drawPanes(){
        mainPane=new AnchorPane();
        splitPane=new SplitPane();
        leftPane=new AnchorPane();
        rightPane=new AnchorPane();
        splitPane.getItems().add(leftPane);
        splitPane.getItems().add(rightPane);
        mainPane.getChildren().add(splitPane);
    }

    private void setCSS(){
        mainPane.setId("mainPane");
        leftPane.setId("leftPane");
        rightPane.setId("rightPane");
        buttonFilter.setId("button");
        buttonInfo.setId("button");
        buttonClear.setId("button");
        buttonRemoveEl.setId("button");
        buttonChoose.setId("button");
        buttonSave.setId("button");
        buttonInfoApplication.setId("buttonInfoApplication");
        buttonSettings.setId("buttonSettings");
        buttonUndo.setId("buttonUndo");
        buttonRedo.setId("buttonRedo");
        buttonNameDown.setId("buttonRedo");
        buttonNameUp.setId("buttonRedo");
        buttonWeigthDown.setId("buttonRedo");
        buttonWeigthUp.setId("buttonRedo");
        buttonSearch.setId("buttonSearch");
        buttonNext.setId("buttonNext");
        buttonBack.setId("buttonBack");
    }

    private void drawTable(){
        table=new TableView<>();
        table.setEditable(true);
        columnName=new TableColumn<>("Name");
        columnName.setSortable(false);
        columnName.setPrefWidth(162.5);
        columnName.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        columnWeight=new TableColumn<>("Weight");
        columnWeight.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        columnWeight.setSortable(false);
        table.getColumns().addAll(columnName,columnWeight);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        AnchorPane.setTopAnchor(table, 20.0);
        AnchorPane.setBottomAnchor(table, 100.0);
        AnchorPane.setRightAnchor(table, 25.0);
        AnchorPane.setLeftAnchor(table, 25.0);
        leftPane.getChildren().add(table);
    }

    private void initTable(){
        data=FXCollections.observableArrayList();
        InitTable initTable=new InitTable("init", data, table);
        initTable.start();
        table.setItems(data);
    }

    private void drawLeftButtonsContainer(){
        leftButtonsContainer = new HBox();
        buttonFilter = new Button("Фильтровать");
        leftButtonsContainer.setSpacing(40);
        leftButtonsContainer.setAlignment(Pos.CENTER);
        leftButtonsContainer.getChildren().add(buttonFilter);
        leftPane.getChildren().add(leftButtonsContainer);
        AnchorPane.setBottomAnchor(leftButtonsContainer, 32.0);
        AnchorPane.setLeftAnchor(leftButtonsContainer, 0.0);
        AnchorPane.setRightAnchor(leftButtonsContainer, 0.0);
    }
    private void drawRightButtonsContainer(){
        rightButtonsContainer =new VBox();
        buttonInfo=new Button("Информация");
        buttonClear=new Button("Очистить");
        buttonRemoveEl=new Button("Удалить элементы");
        buttonChoose=new Button("Выбрать файлы");
        buttonSave=new Button("Сохранить");
        rightButtonsContainer.setAlignment(Pos.CENTER_LEFT);
        rightButtonsContainer.setSpacing(30);
        rightButtonsContainer.getChildren().add(buttonInfo);
        rightButtonsContainer.getChildren().add(buttonClear);
        rightButtonsContainer.getChildren().add(buttonRemoveEl);
        rightButtonsContainer.getChildren().add(buttonChoose);
        rightButtonsContainer.getChildren().add(buttonSave);
        buttonInfo.setPrefWidth(150.0);
        buttonClear.setPrefWidth(150.0);
        buttonRemoveEl.setPrefWidth(150.0);
        buttonChoose.setPrefWidth(150.0);
        buttonSave.setPrefWidth(150.0);
        rightPane.getChildren().add(rightButtonsContainer);
        AnchorPane.setTopAnchor(rightButtonsContainer, 0.0);
        AnchorPane.setBottomAnchor(rightButtonsContainer, 0.0);
        AnchorPane.setLeftAnchor(rightButtonsContainer, 30.0);
        AnchorPane.setRightAnchor(rightButtonsContainer, 100.0);
    }
    private void drawTopRightCornerContainer(){
        buttonSettings=new Button();
        buttonSettings.setGraphic(new ImageView(new Image("/icons/settings.png", 32, 32, false ,false)));
        buttonInfoApplication=new Button();
        buttonInfoApplication.setGraphic(new ImageView(new Image("/icons/info.png", 32, 32, false, false)));
        topRightCornerContainer =new HBox();
        topRightCornerContainer.setAlignment(Pos.CENTER_RIGHT);
        topRightCornerContainer.setSpacing(8);
        topRightCornerContainer.getChildren().addAll(buttonInfoApplication, buttonSettings);
        rightPane.getChildren().add(topRightCornerContainer);
        AnchorPane.setTopAnchor(topRightCornerContainer, 10.0);
        AnchorPane.setRightAnchor(topRightCornerContainer, 10.0);
        AnchorPane.setLeftAnchor(topRightCornerContainer, 200.0);
        AnchorPane.setRightAnchor(rightButtonsContainer, 280.0);
    }
    private void drawListViewContainer(){
        ListViewContainer=new HBox();
        listView=new ListView();
        HBox.setHgrow(listView, Priority.SOMETIMES);
        ListViewContainer.getChildren().add(listView);
        rightPane.getChildren().add(ListViewContainer);
        AnchorPane.setTopAnchor(ListViewContainer, 100.0);
        AnchorPane.setBottomAnchor(ListViewContainer, 100.0);
        AnchorPane.setLeftAnchor(ListViewContainer, 200.0);
        AnchorPane.setRightAnchor(ListViewContainer, 20.0);
        ListViewContainer.setPadding(new Insets(0,0,0,0));
    }
    private void drawSearchContainer(){
        searchContainer =new HBox();
        searchContainer.setAlignment(Pos.CENTER_LEFT);
        searchContainer.setSpacing(8);
        AnchorPane.setLeftAnchor(searchContainer, 10.0);
        AnchorPane.setBottomAnchor(searchContainer, 10.0);
        AnchorPane.setRightAnchor(searchContainer, 50.0);
        buttonBack =new Button();
        buttonBack.setGraphic(new ImageView(new Image("/icons/back.png", 24, 24, false, false)));
        buttonNext =new Button();
        buttonNext.setGraphic(new ImageView(new Image("/icons/next.png", 24, 24, false, false)));
        count=new Text();
        count.setStyle("-fx-font-weight: bold");
        weightSearch=new NumberTextfield();
        weightSearch.setPrefWidth(60);
        nameSearch=new TextField();
        nameSearch.setPrefWidth(120);
        topLeftCornerContainer.getChildren().addAll(buttonSearch);
        rightPane.getChildren().add(searchContainer);
    }
    private void drawTopLeftCornerContainer(){
        topLeftCornerContainer =new HBox();
        topLeftCornerContainer.setAlignment(Pos.CENTER_LEFT);
        topLeftCornerContainer.setSpacing(8);
        buttonUndo=new Button();
        buttonRedo=new Button();
        buttonSearch=new Button();
        buttonSearch.setGraphic(new ImageView(new Image("/icons/search.png", 32, 32, false, false)));
        buttonUndo.setGraphic(new ImageView(new Image("/icons/undoNew.png", 32, 32, false, false)));//TODO Убрать маджик числа
        buttonRedo.setGraphic(new ImageView(new Image("/icons/redoNew.png", 32, 32, false, false)));
        topLeftCornerContainer.getChildren().addAll(buttonUndo, buttonRedo);
        rightPane.getChildren().addAll(topLeftCornerContainer);
        AnchorPane.setLeftAnchor(topLeftCornerContainer, 10.0);
        AnchorPane.setTopAnchor(topLeftCornerContainer, 10.0);
        AnchorPane.setRightAnchor(topLeftCornerContainer, 100.0);
    }
    private void drawSortButtons(){//TODO сменить публик на прайват
        buttonNameDown=new Button();
        buttonNameDown.setGraphic(new ImageView(new Image("/icons/down.png", 16, 16, false, false)));
        leftPane.getChildren().add(buttonNameDown);
        AnchorPane.setTopAnchor(buttonNameDown, 35.0);
        AnchorPane.setLeftAnchor(buttonNameDown, 4.0);
        buttonNameUp=new Button();
        buttonNameUp.setGraphic(new ImageView(new Image("/icons/up.png", 16, 16, false, false)));
        leftPane.getChildren().add(buttonNameUp);
        AnchorPane.setTopAnchor(buttonNameUp, 15.0);
        AnchorPane.setLeftAnchor(buttonNameUp, 4.0);
        buttonWeigthDown=new Button();
        buttonWeigthDown.setGraphic(new ImageView(new Image("/icons/down.png", 16, 16, false, false)));
        leftPane.getChildren().add(buttonWeigthDown);
        AnchorPane.setTopAnchor(buttonWeigthDown, 35.0);
        AnchorPane.setRightAnchor(buttonWeigthDown, 4.0);
        buttonWeigthUp=new Button();
        buttonWeigthUp.setGraphic(new ImageView(new Image("/icons/up.png", 16, 16, false, false)));
        leftPane.getChildren().add(buttonWeigthUp);
        AnchorPane.setTopAnchor(buttonWeigthUp, 15.0);
        AnchorPane.setRightAnchor(buttonWeigthUp, 4.0);
        AnchorPane.setTopAnchor(buttonNameUp, 15.0);
        AnchorPane.setLeftAnchor(buttonNameUp, 4.0);
    }
    private void drawItems(){
        drawLeftButtonsContainer();
        drawRightButtonsContainer();
        drawTopRightCornerContainer();
        drawListViewContainer();
        drawSortButtons();
        drawTopLeftCornerContainer();
        drawSearchContainer();
    }

    private void setControllers(){
        UnSeeingData = FXCollections.observableArrayList();
        MainScreenController.buttonFiltr(buttonFilter, data,UnSeeingData, table);
        MainScreenController.buttonInfo(buttonInfo, data);
        MainScreenController.buttonClear(buttonClear, data);
        MainScreenController.buttonRemoveEl(buttonRemoveEl, data);
        MainScreenController.buttonChoose(buttonChoose,listView);
        MainScreenController.openListItem(listView, data);
        MainScreenController.buttonSave(buttonSave, data);
        MainScreenController.editName(columnName, data);
        MainScreenController.editWeight(columnWeight, data);
        MainScreenController.tableViewRightClick(table);
        MainScreenController.buttonInfoApplication(buttonInfoApplication);
        MainScreenController.buttonSettings(buttonSettings);
        MainScreenController.addIntoEmptyTable(leftPane,data);
        MainScreenController.buttonUndo(buttonUndo);
        MainScreenController.buttonRedo(buttonRedo);
        MainScreenController.buttonNameUp(buttonNameUp);
        MainScreenController.buttonNameDown(buttonNameDown);
        MainScreenController.buttonWeightUp(buttonWeigthDown);
        MainScreenController.buttonWeightDown(buttonWeigthUp);
        MainScreenController.buttonSearch(buttonSearch);
        MainScreenController.buttonBack(buttonBack);
        MainScreenController.buttonNext(buttonNext);
        MainScreenController.addListenersToTextControllers(weightSearch,nameSearch);
    }

    public void loadMainScreen(){
        try {
            drawPanes();
            drawTable();
            initTable();
            drawItems();
            setControllers();
            setCSS();
            primaryStage = new Stage();
            primaryStage.setMinHeight(550.0);
            primaryStage.setMinWidth(950.0);
            primaryStage.getIcons().add(new Image("/icons/edit.png"));
            primaryStage.setTitle("Остатки еды");
            scene = new Scene(mainPane, 950, 550);
            primaryStage.setScene(scene);
            leftPane.setMinWidth(300.0);
            rightPane.setMinWidth(450.0);
            splitPane.prefWidthProperty().bind(scene.widthProperty());
            splitPane.prefHeightProperty().bind(scene.heightProperty());
            primaryStage.getScene().getStylesheets().add("/css/Main.css");
            primaryStage.show();
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.exit(0);
                }
            });
        }
        catch (Exception e){}
    }

    public boolean isVisiable() {
        return visiable.get();
    }

    public BooleanProperty visiableProperty() {
        return visiable;
    }

    public ArrayList<Integer> getSearched() {
        return searched;
    }

    public int getCurrentSearch() {
        return currentSearch;
    }

    public ObservableList<FoodResidus> getData() {
        return data;
    }

    public ObservableList getUnSeeingData() {
        return UnSeeingData;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Scene getScene() {
        return scene;
    }

    public AnchorPane getMainPane() {
        return mainPane;
    }

    public SplitPane getSplitPane() {
        return splitPane;
    }

    public AnchorPane getLeftPane() {
        return leftPane;
    }

    public VBox getRightButtonsContainer() {
        return rightButtonsContainer;
    }

    public AnchorPane getRightPane() {
        return rightPane;
    }

    public TextField getNameSearch() {
        return nameSearch;
    }

    public NumberTextfield getWeightSearch() {
        return weightSearch;
    }

    public TableColumn<FoodResidus, String> getColumnName() {
        return columnName;
    }

    public TableColumn<FoodResidus, Integer> getColumnWeight() {
        return columnWeight;
    }

    public HBox getListViewContainer() {
        return ListViewContainer;
    }

    public ListView getListView() {
        return listView;
    }

    public Button getButtonInfo() {
        return buttonInfo;
    }

    public Button getButtonClear() {
        return buttonClear;
    }

    public Button getButtonRemoveEl() {
        return buttonRemoveEl;
    }

    public Button getButtonChoose() {
        return buttonChoose;
    }

    public Button getButtonSave() {
        return buttonSave;
    }

    public HBox getTopRightCornerContainer() {
        return topRightCornerContainer;
    }

    public Button getButtonSettings() {
        return buttonSettings;
    }

    public Button getButtonInfoApplication() {
        return buttonInfoApplication;
    }

    public Button getButtonSearch() {
        return buttonSearch;
    }

    public Text getCount() {
        return count;
    }

    public HBox getTopLeftCornerContainer() {
        return topLeftCornerContainer;
    }

    public Button getButtonWeigthUp() {
        return buttonWeigthUp;
    }

    public Button getButtonWeigthDown() {
        return buttonWeigthDown;
    }

    public Button getButtonNameUp() {
        return buttonNameUp;
    }

    public Button getButtonNameDown() {
        return buttonNameDown;
    }

    public Button getButtonBack() {
        return buttonBack;
    }

    public Button getButtonNext() {
        return buttonNext;
    }

    public HBox getSearchContainer() {
        return searchContainer;
    }

    public TableView<FoodResidus> getTable() {
        return table;
    }

    public Button getButtonRedo() {
        return buttonRedo;
    }

    public Button getButtonUndo() {
        return buttonUndo;
    }

    public HBox getLeftButtonsContainer() {
        return leftButtonsContainer;
    }

    public Button getButtonFilter() {
        return buttonFilter;
    }

    public void setCurrentSearch(int currentSearch) {
        this.currentSearch = currentSearch;
    }
}