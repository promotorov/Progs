package sample;

import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import laba2.FoodResidus;

import java.util.*;

/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreen{//TODO определить для всех окон максимальные и минимальные размеры
    private static BooleanProperty visiable=new SimpleBooleanProperty();
    private static ArrayList<Integer> searched=new ArrayList<>();
    private static int currentSearch=0;
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
    private static TextField nameSearch;
    private static NumberTextfield weightSearch;
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
    private static Button buttonUndo;
    private static Button buttonSearch;
    private static Button buttonRedo;
    private static Text count;
    private static HBox tableStatements;
    private static Button buttonWeigthUp;
    private static Button buttonWeigthDown;
    private static Button buttonNameUp;
    private static Button buttonNameDown;
    private static TextField test;
    private static Button buttonBack;
    private static Button buttonNext;
    private static HBox search;

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

    private static void drawTable(){
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

    private static void initTable(){
        data=FXCollections.observableArrayList();
        InitTable initTable=new InitTable("init", data, table);
        initTable.start();
        table.setItems(data);
    }

    public static void drawItems(){
        buttonFiler =new Button("Фильтровать");
        buttonDelFiler =new Button("Удалить фильтр");

        leftFilterButtonsContainer = new HBox();
        leftFilterButtonsContainer.setSpacing(40);
        leftFilterButtonsContainer.setAlignment(Pos.CENTER);
        leftFilterButtonsContainer.getChildren().add(buttonFiler);
       // leftFilterButtonsContainer.getChildren().add(buttonDelFiler);

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
        buttonInfo.setPrefWidth(150.0);
        buttonClear.setPrefWidth(150.0);
        buttonRemoveEl.setPrefWidth(150.0);
        buttonChoose.setPrefWidth(150.0);
        buttonSave.setPrefWidth(150.0);

        rightPane.getChildren().add(rightFilterButtonsContainer);
        AnchorPane.setTopAnchor(rightFilterButtonsContainer, 0.0);
        AnchorPane.setBottomAnchor(rightFilterButtonsContainer, 0.0);
        AnchorPane.setLeftAnchor(rightFilterButtonsContainer, 30.0);
        AnchorPane.setRightAnchor(rightFilterButtonsContainer, 100.0);

        buttonUndo=new Button();
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
        AnchorPane.setLeftAnchor(SettingsContainer, 200.0);
        AnchorPane.setRightAnchor(rightFilterButtonsContainer, 280.0);

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
        tableStatements=new HBox();
        tableStatements.setAlignment(Pos.CENTER_LEFT);
        tableStatements.setSpacing(8);

        search=new HBox();
        search.setAlignment(Pos.CENTER_LEFT);
        search.setSpacing(8);
        AnchorPane.setLeftAnchor(search, 10.0);
        AnchorPane.setBottomAnchor(search, 10.0);
        AnchorPane.setRightAnchor(search, 50.0);
        Image imageUndo=new Image("/icons/undoNew.png", 32, 32, false, false);
        Image imageRedo=new Image("/icons/redoNew.png", 32, 32, false, false);
        buttonUndo=new Button();
        buttonRedo=new Button();
        buttonUndo.setGraphic(new ImageView(imageUndo));
        buttonRedo.setGraphic(new ImageView(imageRedo));
        tableStatements.getChildren().addAll(buttonUndo, buttonRedo);
        rightPane.getChildren().addAll(tableStatements);
        AnchorPane.setLeftAnchor(tableStatements, 10.0);
        AnchorPane.setTopAnchor(tableStatements, 10.0);
        AnchorPane.setRightAnchor(tableStatements, 100.0);
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
        buttonBack =new Button();
        buttonBack.setGraphic(new ImageView(new Image("/icons/back.png", 24, 24, false, false)));
        buttonNext =new Button();
        buttonNext.setGraphic(new ImageView(new Image("/icons/next.png", 24, 24, false, false)));
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

        test=new TextField();
        AnchorPane.setTopAnchor(buttonNameUp, 15.0);
        AnchorPane.setLeftAnchor(buttonNameUp, 4.0);

        buttonSearch=new Button();
        buttonSearch.setGraphic(new ImageView(new Image("/icons/search.png", 32, 32, false, false)));

        count=new Text();
        count.setStyle("-fx-font-weight: bold");
        weightSearch=new NumberTextfield();
        weightSearch.setPrefWidth(60);
        nameSearch=new TextField();
        nameSearch.setPrefWidth(120);
        tableStatements.getChildren().addAll(buttonSearch);
        rightPane.getChildren().add(search);
        buttonSearch.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!search.getChildren().contains(nameSearch)) {
                    search.getChildren().add(nameSearch);
                    search.getChildren().add(weightSearch);
                    search.getChildren().add(count);
                    for(int i=0; i<data.size(); i++){
                        data.get(i).setShowContextMenu(false);
                    }
                    visiable.setValue(true);
                }
                else {
                    search.getChildren().remove(nameSearch);
                    search.getChildren().remove(weightSearch);
                    search.getChildren().remove(count);
                    search.getChildren().removeAll(buttonBack, buttonNext);
                    for(int i=0; i<data.size(); i++){
                        data.get(i).setShowContextMenu(true);
                    }
                    for (int i=0; i<data.size(); i++){
                        data.get(i).setHighlightProperty(false);
                    }
                    visiable.setValue(false);
                    nameSearch.clear();
                    weightSearch.clear();
                    count.setText("");
                    columnName.setVisible(false);
                    columnName.setVisible(true);
                }
            }
        });
        buttonBack.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentSearch!=0){
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(false);
                    currentSearch--;
                    System.out.println(currentSearch);
                    table.scrollTo(searched.get(currentSearch));
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(true);
                    count.setText((currentSearch+1)+" из "+searched.size());
                }
                else {
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(false);
                    currentSearch=searched.size()-1;
                    table.scrollTo(searched.get(searched.size()-1));
                    count.setText((currentSearch+1) + " из " + searched.size());
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(true);
                }
                columnName.setVisible(false);
                columnName.setVisible(true);
            }
        });
        buttonNext.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(currentSearch!=(searched.size()-1)){
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(false);
                    currentSearch++;
                    System.out.println(currentSearch);
                    table.scrollTo(searched.get(currentSearch));
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(true);
                    count.setText((currentSearch+1)+" из "+searched.size());
                }
                else {
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(false);
                    currentSearch=0;
                    table.scrollTo(searched.get(0));
                    count.setText((currentSearch+1) + " из " + searched.size());
                    data.get(searched.get(currentSearch)).setActivehighlightProperty(true);
                }
                columnName.setVisible(false);
                columnName.setVisible(true);
            }
        });
        weightSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkHighlight();
            }
        });
        nameSearch.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                checkHighlight();
            }
        });
        nameSearch.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if(event.getCode()== KeyCode.ENTER){
                    System.out.println("y");
                }
            }
        });
    }

    public static void setControllers(){
        UnSeeingData = FXCollections.observableArrayList();
        MainScreenController.buttonFiltr(buttonFiler, data,UnSeeingData, table);
        //MainScreenController.buttonDelFiltr(buttonDelFiler, data, UnSeeingData, table);
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
    }

    public static void loadMainScreen(){
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
            primaryStage.getIcons().add(new Image("/icons/edit.png"));//TODO определить иконку
            primaryStage.setTitle("Остатки еды");
            scene = new Scene(mainPane, 950, 550);
            primaryStage.setScene(scene);
            leftPane.setMinWidth(300.0);
            rightPane.setMinWidth(450.0);
            splitPane.prefWidthProperty().bind(scene.widthProperty());
            splitPane.prefHeightProperty().bind(scene.heightProperty());
            primaryStage.getScene().getStylesheets().add("/css/Main.css");
            primaryStage.show();
            Platform.runLater(new Runnable()
            {
                @Override
                public void run()
                {
                    table.requestFocus();
                    //table.getSelectionModel().select(15);
                    //table.scrollTo(15);
                    //table.getFocusModel().focus(0);
                    table.setPlaceholder(new Text(""));
                    System.out.println(table.getColumns().get(0));
                }
            });
            primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
                public void handle(WindowEvent we) {
                    System.exit(0);
                }
            });
        }
        catch (Exception e){

        }
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

    public static TableColumn<FoodResidus, String> getColumnName() {
        return columnName;
    }

    public static TableColumn<FoodResidus, Integer> getColumnWeight() {
        return columnWeight;
    }

    public static ObservableList getData(){
        return data;
    }

    public static Button getButtonUndo() {
        return buttonUndo;
    }

    public static Button getButtonRedo() {
        return buttonRedo;
    }

    public static void setData(ObservableList<FoodResidus> data) {
        MainScreen.data = data;
    }

    public static ObservableList getUnSeeingData() {
        return UnSeeingData;
    }

    public static void setUnSeeingData(ObservableList unSeeingData) {
        UnSeeingData = unSeeingData;
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setPrimaryStage(Stage primaryStage) {
        MainScreen.primaryStage = primaryStage;
    }

    public static Scene getScene() {
        return scene;
    }

    public static void setScene(Scene scene) {
        MainScreen.scene = scene;
    }

    public static AnchorPane getMainPane() {
        return mainPane;
    }

    public static void setMainPane(AnchorPane mainPane) {
        MainScreen.mainPane = mainPane;
    }

    public static SplitPane getSplitPane() {
        return splitPane;
    }

    public static void setSplitPane(SplitPane splitPane) {
        MainScreen.splitPane = splitPane;
    }

    public static AnchorPane getLeftPane() {
        return leftPane;
    }

    public static void setLeftPane(AnchorPane leftPane) {
        MainScreen.leftPane = leftPane;
    }

    public static HBox getLeftFilterButtonsContainer() {
        return leftFilterButtonsContainer;
    }

    public static void setLeftFilterButtonsContainer(HBox leftFilterButtonsContainer) {
        MainScreen.leftFilterButtonsContainer = leftFilterButtonsContainer;
    }

    public static VBox getRightFilterButtonsContainer() {
        return rightFilterButtonsContainer;
    }

    public static void setRightFilterButtonsContainer(VBox rightFilterButtonsContainer) {
        MainScreen.rightFilterButtonsContainer = rightFilterButtonsContainer;
    }

    public static AnchorPane getRightPane() {
        return rightPane;
    }

    public static void setRightPane(AnchorPane rightPane) {
        MainScreen.rightPane = rightPane;
    }

    public static TextField getNameSearch() {
        return nameSearch;
    }

    public static void setNameSearch(TextField nameSearch) {
        MainScreen.nameSearch = nameSearch;
    }

    public static TextField getWeightSearch() {
        return weightSearch;
    }

    public static void setWeightSearch(NumberTextfield weightSearch) {
        MainScreen.weightSearch = weightSearch;
    }

    public static void setTable(TableView<FoodResidus> table) {
        MainScreen.table = table;
    }

    public static void setColumnName(TableColumn<FoodResidus, String> columnName) {
        MainScreen.columnName = columnName;
    }

    public static void setColumnWeight(TableColumn<FoodResidus, Integer> columnWeight) {
        MainScreen.columnWeight = columnWeight;
    }

    public static HBox getListViewContainer() {
        return ListViewContainer;
    }

    public static void setListViewContainer(HBox listViewContainer) {
        ListViewContainer = listViewContainer;
    }

    public static ListView getListView() {
        return listView;
    }

    public static void setListView(ListView listView) {
        MainScreen.listView = listView;
    }

    public static HBox getSettingsContainer() {
        return SettingsContainer;
    }

    public static void setSettingsContainer(HBox settingsContainer) {
        SettingsContainer = settingsContainer;
    }

    public static void setButtonUndo(Button buttonUndo) {
        MainScreen.buttonUndo = buttonUndo;
    }

    public static Button getButtonSearch() {
        return buttonSearch;
    }

    public static void setButtonSearch(Button buttonSearch) {
        MainScreen.buttonSearch = buttonSearch;
    }

    public static void setButtonRedo(Button buttonRedo) {
        MainScreen.buttonRedo = buttonRedo;
    }

    public static Text getCount() {
        return count;
    }

    public static void setCount(Text count) {
        MainScreen.count = count;
    }

    public static HBox getTableStatements() {
        return tableStatements;
    }

    public static void setTableStatements(HBox tableStatements) {
        MainScreen.tableStatements = tableStatements;
    }

    public static Button getButtonWeigthUp() {
        return buttonWeigthUp;
    }

    public static void setButtonWeigthUp(Button buttonWeigthUp) {
        MainScreen.buttonWeigthUp = buttonWeigthUp;
    }

    public static Button getButtonWeigthDown() {
        return buttonWeigthDown;
    }

    public static void setButtonWeigthDown(Button buttonWeigthDown) {
        MainScreen.buttonWeigthDown = buttonWeigthDown;
    }

    public static Button getButtonNameUp() {
        return buttonNameUp;
    }

    public static void setButtonNameUp(Button buttonNameUp) {
        MainScreen.buttonNameUp = buttonNameUp;
    }

    public static Button getButtonNameDown() {
        return buttonNameDown;
    }

    public static void setButtonNameDown(Button buttonNameDown) {
        MainScreen.buttonNameDown = buttonNameDown;
    }

    public static TextField getTest() {
        return test;
    }

    public static void setTest(TextField test) {
        MainScreen.test = test;
    }

    public static ArrayList<Integer> getSearched() {
        return searched;
    }

    public static void setSearched(ArrayList<Integer> searched) {
        MainScreen.searched = searched;
    }

    public static Button getButtonBack() {
        return buttonBack;
    }

    public static void setButtonBack(Button buttonBack) {
        MainScreen.buttonBack = buttonBack;
    }

    public static Button getButtonNext() {
        return buttonNext;
    }

    public static void setButtonNext(Button buttonNext) {
        MainScreen.buttonNext = buttonNext;
    }

    public static HBox getSearch() {
        return search;
    }

    public static void setSearch(HBox search) {
        MainScreen.search = search;
    }

    public static void checkHighlight(){
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

    public static BooleanProperty isVisiable() {
        return visiable;
    }

    public static void setVisiable(boolean visiable) {
        MainScreen.visiable.setValue(visiable);
    }
}