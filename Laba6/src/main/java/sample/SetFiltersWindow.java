package sample;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Created by danil on 08.05.2017.
 */
public class SetFiltersWindow {
    private static Stage primaryStage;
    private static Scene scene;
    private static AnchorPane mainPane;
    public static Button SetFiltersOKbutton;
    public static Button SetFiltersCancelButton;
    public static TextField textFieldName;
    public static TextField textFieldWeight;
    public static void loadSetFiltersWindow(){
        drawPanes();
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 400, 160);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
    public static void drawPanes(){
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

    public static void drawButton(){
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
    private static void setCSS(){
        SetFiltersOKbutton.setId("button");
        SetFiltersCancelButton.setId("button");
    }
    private static void setControllers(){
        //MainScreenController.SetFiltersOKbutton(SetFiltersOKbutton);
        //MainScreenController.SetFiltersCancelButton(SetFiltersCancelButton);
    }
}
