package screens;

import controllers.MainScreenController;
import controllers.SaveFiltersController;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import items.FoodResidus;

/**
 * Created by danil on 08.05.2017.
 */
public class SetFiltersWindow {
    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainPane;
    private Button SetFiltersOKbutton;
    private Button SetFiltersCancelButton;
    private TextField textFieldName;
    private TextField textFieldWeight;
    private static SetFiltersWindow setFiltersWindow;

    private SetFiltersWindow(){}
    public static synchronized SetFiltersWindow getInstace(){
        if(setFiltersWindow==null){
            setFiltersWindow=new SetFiltersWindow();
        }
        return setFiltersWindow;
    }
    public void loadSetFiltersWindow(ObservableList data,ObservableList UnSeeingData, TableView<FoodResidus> table){
        drawPanes();
        drawButton();
        setControllers(data,UnSeeingData, table, textFieldName, textFieldWeight);
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        scene=new Scene(mainPane, 400, 160);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
    private void drawPanes(){
        mainPane=new AnchorPane();
        textFieldName=new TextField("Фильтр имени");
        mainPane.getChildren().add(textFieldName);
        AnchorPane.setTopAnchor(textFieldName,20.0);
        AnchorPane.setLeftAnchor(textFieldName,20.0);
        AnchorPane.setRightAnchor(textFieldName,20.0);

        textFieldWeight=new TextField("Фильтр веса");
        mainPane.getChildren().add(textFieldWeight);
        AnchorPane.setTopAnchor(textFieldWeight,60.0);
        AnchorPane.setLeftAnchor(textFieldWeight,20.0);
        AnchorPane.setRightAnchor(textFieldWeight,20.0);
    }

    private void drawButton(){
        SetFiltersOKbutton =new Button("Ok");
        mainPane.getChildren().add(SetFiltersOKbutton);
        AnchorPane.setRightAnchor(SetFiltersOKbutton,110.0);
        AnchorPane.setLeftAnchor(SetFiltersOKbutton,220.0);
        AnchorPane.setBottomAnchor(SetFiltersOKbutton, 20.0);

        SetFiltersCancelButton =new Button("Cancel");
        mainPane.getChildren().add(SetFiltersCancelButton);
        AnchorPane.setRightAnchor(SetFiltersCancelButton,220.0);
        AnchorPane.setLeftAnchor(SetFiltersCancelButton,110.0);
        AnchorPane.setBottomAnchor(SetFiltersCancelButton, 20.0);
    }
    private void setCSS(){
        SetFiltersOKbutton.setId("button");
        SetFiltersCancelButton.setId("button");
    }
    private void setControllers(ObservableList data,ObservableList UnSeeingData, TableView<FoodResidus> table, TextField textFieldName, TextField textFieldWeight){
        SaveFiltersController.SetFiltersOKbutton(SetFiltersOKbutton, data, UnSeeingData, table, textFieldName, textFieldWeight);
        SaveFiltersController.SetFiltersCancelButton(SetFiltersCancelButton);
    }

    public Button getSetFiltersOKbutton() {
        return SetFiltersOKbutton;
    }

    public Button getSetFiltersCancelButton() {
        return SetFiltersCancelButton;
    }
}
