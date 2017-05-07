package sample;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by danil on 07.05.2017.
 */
public class SaveWindow {
    private static Stage primaryStage;
    private static Scene scene;
    private static AnchorPane mainPane;
    public static Button SaveDefaultButton;
    public static Button SaveChooseButton;

    private static Text text;
    public static void loadSaveWindow(){
        drawPanes();
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 390, 150);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
    public static void drawPanes(){
        mainPane=new AnchorPane();
        text = new Text("Выберете файл или сохраните по умолчанию?");
        mainPane.getChildren().add(text);
        AnchorPane.setTopAnchor(text,20.0);
        AnchorPane.setLeftAnchor(text,20.0);
        AnchorPane.setRightAnchor(text,20.0);
    }
    public static void drawButton(){
        SaveDefaultButton =new Button("По умолчанию");
        mainPane.getChildren().add(SaveDefaultButton);
        AnchorPane.setRightAnchor(SaveDefaultButton,55.0);
        AnchorPane.setLeftAnchor(SaveDefaultButton,210.0);
        AnchorPane.setBottomAnchor(SaveDefaultButton, 32.0);
        SaveChooseButton =new Button("Выбрать");
        mainPane.getChildren().add(SaveChooseButton);
        AnchorPane.setRightAnchor(SaveChooseButton,210.0);
        AnchorPane.setLeftAnchor(SaveChooseButton,55.0);
        AnchorPane.setBottomAnchor(SaveChooseButton, 32.0);
    }
    private static void setCSS(){
        SaveDefaultButton.setId("button");
        SaveChooseButton.setId("button");
    }
    private static void setControllers(){
        MainScreenController.SaveDefaultButton(SaveDefaultButton);
        MainScreenController.SaveChooseButton(SaveChooseButton);
    }
}
