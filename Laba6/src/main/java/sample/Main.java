package sample;

import com.sun.glass.events.MouseEvent;
import com.sun.glass.ui.CommonDialogs;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import laba2.Cheese;
import laba2.FoodResidus;
import laba2.Meet;
import laba2.Whine;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*AnchorPane mainPane=new AnchorPane();
        AnchorPane leftPane=new AnchorPane();
        AnchorPane rightPane=new AnchorPane();
        leftPane.setId("leftPane");
        rightPane.setId("rightPane");
        mainPane.getChildren().add(leftPane);
        mainPane.getChildren().add(rightPane);
        AnchorPane.setTopAnchor(leftPane, 0.0);
        AnchorPane.setLeftAnchor(leftPane, 0.0);
        AnchorPane.setBottomAnchor(leftPane, 0.0);
        AnchorPane.setRightAnchor(leftPane, 375.0);;
        AnchorPane.setTopAnchor(rightPane, 0.0);
        AnchorPane.setLeftAnchor(rightPane, 375.0);
        AnchorPane.setBottomAnchor(rightPane, 0.0);
        AnchorPane.setRightAnchor(rightPane, 0.0);
        TableView<FoodResidus> table=new TableView();
        table.setEditable(true);
        TableColumn<FoodResidus, String> name=new TableColumn("Name");
        name.setMinWidth(162.5);
        name.setCellValueFactory(new PropertyValueFactory<FoodResidus, String>("name"));
        name.setCellFactory(TextFieldTableCell.<FoodResidus>forTableColumn());
        name.setOnEditCommit((TableColumn.CellEditEvent<FoodResidus, String> t) ->{
            (t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
        } );
        TableColumn<FoodResidus, Integer> weight=new TableColumn("Weight");
        name.setSortable(false);
        weight.setMinWidth(160.0);
        weight.setCellValueFactory(new PropertyValueFactory<FoodResidus, Integer>("weight"));
        weight.setSortType(TableColumn.SortType.ASCENDING);
        table.setItems(data);
        table.getColumns().addAll(name, weight);
        table.setItems(data);
        leftPane.getChildren().add(table);
        AnchorPane.setTopAnchor(table, 20.0);
        AnchorPane.setBottomAnchor(table, 100.0);
        AnchorPane.setRightAnchor(table, 25.0);
        AnchorPane.setLeftAnchor(table, 25.0);

        Button buttonFiltr=new Button("Фильтровать");
        buttonFiltr.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello World!");
            }
        });
        leftPane.getChildren().add(buttonFiltr);
        AnchorPane.setTopAnchor(buttonFiltr, 500.0);

        Button buttonFiltrRemove=new Button("Удалить фильтр");
        buttonFiltrRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                primaryStage.setScene(new Scene(new AnchorPane(), 330, 330));;
            }
        });
        leftPane.getChildren().add(buttonFiltrRemove);
        AnchorPane.setTopAnchor(buttonFiltrRemove, 500.0);
        AnchorPane.setRightAnchor(buttonFiltrRemove, 30.0);

        Button buttonInfo=new Button("Info");
        buttonInfo.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello World!");
            }
        });
        rightPane.getChildren().add(buttonInfo);
        AnchorPane.setTopAnchor(buttonInfo, 50.0);
        AnchorPane.setRightAnchor(buttonInfo, 280.0);

        Button buttonClear=new Button("Clear");
        buttonClear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello World!");
            }
        });
        rightPane.getChildren().add(buttonClear);
        AnchorPane.setTopAnchor(buttonClear, 150.0);
        AnchorPane.setRightAnchor(buttonClear, 280.0);

        Button buttonRemove=new Button("Remove");
        buttonRemove.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello World!");
            }
        });
        rightPane.getChildren().add(buttonRemove);
        AnchorPane.setTopAnchor(buttonRemove, 250.0);
        AnchorPane.setRightAnchor(buttonRemove, 280.0);

        Button buttonOpen=new Button("Open File");
        buttonOpen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello World!");
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Select PDF files");
                fileChooser.setInitialDirectory(new File("C:\\Lab_5"));
                fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML Files", "*.xml"));
                File file = fileChooser.showOpenDialog(primaryStage);
                System.out.println(file);
            }
        });
        rightPane.getChildren().add(buttonOpen);
        AnchorPane.setTopAnchor(buttonOpen, 350.0);
        AnchorPane.setRightAnchor(buttonOpen, 280.0);

        Button buttonSave=new Button("Save");
        buttonSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Hello World!");
            }
        });
        rightPane.getChildren().add(buttonSave);
        AnchorPane.setTopAnchor(buttonSave, 450.0);
        AnchorPane.setRightAnchor(buttonSave, 280.0);
        */
        MainScreen.loadMainScreen();
    }


    public static void main(String[] args) {
        launch(args);
    }

}
