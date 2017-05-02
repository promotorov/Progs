package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import laba2.FoodResidus;
import laba2.XMLworker;

import java.util.HashSet;
import java.util.Iterator;


/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreen{
    private static ObservableList<FoodResidus> data;

    private static Stage primaryStage;
    private static Scene scene;
    private static AnchorPane mainPane;
    private static SplitPane splitPane;
    private static AnchorPane leftPane;
    private static GridPane filtrButtonsContainer;
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
        columnName.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
        columnName.setCellValueFactory(new PropertyValueFactory<FoodResidus, String>("name"));
        columnWeight=new TableColumn<>("Weight");
        columnWeight.prefWidthProperty().bind(table.widthProperty().multiply(0.5));
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
            data = FXCollections.observableArrayList();
            HashSet<FoodResidus> set=XMLworker.getCollection("src\\sample.xml");
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

    public static void drawButton(){ //TODO Сделать адекватое изменеие размера и местоположения для кнопок при расширенни окна
        buttonFiler =new Button("Фильтровать");
        leftPane.getChildren().add(buttonFiler);
        AnchorPane.setTopAnchor(buttonFiler, 480.0);
        AnchorPane.setBottomAnchor(buttonFiler, 30.0);
        AnchorPane.setLeftAnchor(buttonFiler, 46.25);
        AnchorPane.setRightAnchor(buttonFiler, 208.75);

        buttonDelFiler =new Button("Удалить фильтр");
        leftPane.getChildren().add(buttonDelFiler);
        AnchorPane.setTopAnchor(buttonDelFiler, 480.0);
        AnchorPane.setBottomAnchor(buttonDelFiler, 30.0);
        AnchorPane.setLeftAnchor(buttonDelFiler, 208.75);
        AnchorPane.setRightAnchor(buttonDelFiler, 46.25);

        buttonInfo=new Button("Информация");
        rightPane.getChildren().add(buttonInfo);
        AnchorPane.setTopAnchor(buttonInfo, 30.0);
        AnchorPane.setBottomAnchor(buttonInfo, 480.0);
        AnchorPane.setLeftAnchor(buttonInfo, 40.25);
        AnchorPane.setRightAnchor(buttonInfo, 210.75);

        buttonClear=new Button("Очистить");
        rightPane.getChildren().add(buttonClear);
        AnchorPane.setTopAnchor(buttonClear, 90.0);
        AnchorPane.setBottomAnchor(buttonClear, 420.0);
        AnchorPane.setLeftAnchor(buttonClear, 40.25);
        AnchorPane.setRightAnchor(buttonClear, 210.75);

        buttonRemoveEl=new Button("Удалить элементы");
        rightPane.getChildren().add(buttonRemoveEl);
        AnchorPane.setTopAnchor(buttonRemoveEl, 150.0);
        AnchorPane.setBottomAnchor(buttonRemoveEl, 360.0);
        AnchorPane.setLeftAnchor(buttonRemoveEl, 40.25);
        AnchorPane.setRightAnchor(buttonRemoveEl, 210.75);

        buttonChoose=new Button("Выбрать файл");
        rightPane.getChildren().add(buttonChoose);
        AnchorPane.setTopAnchor(buttonChoose, 210.0);
        AnchorPane.setBottomAnchor(buttonChoose, 300.0);
        AnchorPane.setLeftAnchor(buttonChoose, 40.25);
        AnchorPane.setRightAnchor(buttonChoose, 210.75);

        buttonSave=new Button("Сохранить");
        rightPane.getChildren().add(buttonSave);
        AnchorPane.setTopAnchor(buttonSave, 270.0);
        AnchorPane.setBottomAnchor(buttonSave, 240.0);
        AnchorPane.setLeftAnchor(buttonSave, 40.25);
        AnchorPane.setRightAnchor(buttonSave, 210.75);
    }

    public static void setControllers(){
        MainScreenController.buttonFiltr(buttonFiler);
        MainScreenController.buttonDelFiltr(buttonDelFiler);
        MainScreenController.buttonInfo(buttonInfo);
        MainScreenController.buttonClear(buttonClear);
        MainScreenController.buttonRemoveEl(buttonRemoveEl);
        MainScreenController.buttonChoose(buttonChoose);
        MainScreenController.buttonSave(buttonSave);
    }

    public static void loadMainScreen(){
        drawPanes();
        drawTable();
        initTable();
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 750, 550);
        primaryStage.setScene(scene);
        splitPane.prefWidthProperty().bind(scene.widthProperty());
        splitPane.prefHeightProperty().bind(scene.heightProperty());
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
}
