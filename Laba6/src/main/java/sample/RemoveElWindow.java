package sample;

/**
 * Created by vladp on 05.05.2017.
 */

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.MainScreenController;

/**
 * Created by danil on 05.05.2017.
 */
public class RemoveElWindow {
    private static Stage primaryStage;
    private static Scene scene;
    private static AnchorPane mainPane;
    public static Button RemoveElOKbutton;
    public static Button RemoveElCancelButton;
    public static TextField textField;
    public static void loadRemoveElWindow(ObservableList data){
        drawPanes();
        drawButton();
        setControllers(data);
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 500, 140);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
    public static void drawPanes(){
        mainPane=new AnchorPane();
        textField=new TextField("Введите строку в формате json");
        mainPane.getChildren().add(textField);
        AnchorPane.setTopAnchor(textField,20.0);
        AnchorPane.setLeftAnchor(textField,20.0);
        AnchorPane.setRightAnchor(textField,20.0);
    }
    public static void drawButton(){
        RemoveElOKbutton =new Button("Ok");
        mainPane.getChildren().add(RemoveElOKbutton);
        AnchorPane.setRightAnchor(RemoveElOKbutton,160.0);
        AnchorPane.setLeftAnchor(RemoveElOKbutton,270.0);
        AnchorPane.setBottomAnchor(RemoveElOKbutton, 32.0);
        RemoveElCancelButton =new Button("Cancel");
        mainPane.getChildren().add(RemoveElCancelButton);
        AnchorPane.setRightAnchor(RemoveElCancelButton,270.0);
        AnchorPane.setLeftAnchor(RemoveElCancelButton,160.0);
        AnchorPane.setBottomAnchor(RemoveElCancelButton, 32.0);
    }
    private static void setCSS(){
        RemoveElOKbutton.setId("button");
        RemoveElCancelButton.setId("button");
    }
    private static void setControllers(ObservableList data){
        MainScreenController.RemoveElOKbutton(RemoveElOKbutton, data);
        MainScreenController.RemoveElCancelButton(RemoveElCancelButton);
    }
}

