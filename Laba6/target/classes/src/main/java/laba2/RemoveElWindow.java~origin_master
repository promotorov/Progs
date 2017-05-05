package laba2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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
    public static void loadRemoveElWindow(){
        drawPanes();
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 700, 140);
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
        AnchorPane.setRightAnchor(RemoveElOKbutton,260.0);
        AnchorPane.setLeftAnchor(RemoveElOKbutton,370.0);
        AnchorPane.setBottomAnchor(RemoveElOKbutton, 32.0);
        RemoveElCancelButton =new Button("Cancel");
        mainPane.getChildren().add(RemoveElCancelButton);
        AnchorPane.setRightAnchor(RemoveElCancelButton,370.0);
        AnchorPane.setLeftAnchor(RemoveElCancelButton,260.0);
        AnchorPane.setBottomAnchor(RemoveElCancelButton, 32.0);
    }
    private static void setCSS(){
        RemoveElOKbutton.setId("RemoveElOKbutton");
        RemoveElCancelButton.setId("RemoveElCancelButton");
    }
    private static void setControllers(){
        MainScreenController.RemoveElOKbutton(RemoveElOKbutton);
        MainScreenController.RemoveElCancelButton(RemoveElCancelButton);
    }
}
