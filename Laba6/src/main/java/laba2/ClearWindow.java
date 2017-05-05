package laba2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import sample.MainScreenController;

/**
 * Created by danil on 05.05.2017.
 */
public class ClearWindow {
    private static Stage primaryStage;
    private static Scene scene;
    private static AnchorPane mainPane;
    public static Button ClearOKbutton;
    public static Button ClearCancelButton;

    private static Text text;
    public static void loadClearWindow(){
        drawPanes();
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 400, 150);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
    public static void drawPanes(){
        mainPane=new AnchorPane();
        text = new Text("Вы точено хотите удалить все элементы коллекции?");
        mainPane.getChildren().add(text);
        AnchorPane.setTopAnchor(text,20.0);
        AnchorPane.setLeftAnchor(text,20.0);
        AnchorPane.setRightAnchor(text,20.0);
    }
    public static void drawButton(){
        ClearOKbutton =new Button("Ok");
        mainPane.getChildren().add(ClearOKbutton);
        AnchorPane.setRightAnchor(ClearOKbutton,80.0);
        AnchorPane.setLeftAnchor(ClearOKbutton,220.0);
        AnchorPane.setBottomAnchor(ClearOKbutton, 32.0);
        ClearCancelButton =new Button("Cancel");
        mainPane.getChildren().add(ClearCancelButton);
        AnchorPane.setRightAnchor(ClearCancelButton,220.0);
        AnchorPane.setLeftAnchor(ClearCancelButton,80.0);
        AnchorPane.setBottomAnchor(ClearCancelButton, 32.0);
    }
    private static void setCSS(){
        ClearOKbutton.setId("ClearOKbutton");
        ClearCancelButton.setId("ClearCancelButton");
    }
    private static void setControllers(){
        MainScreenController.ClearOKbutton(ClearOKbutton);
        MainScreenController.ClearCancelButton(ClearCancelButton);
    }
}
