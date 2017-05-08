package sample;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by danil on 04.05.2017.
 */
public class InfoWindow {
    private static Stage primaryStage;
    private static Scene scene;
    private static AnchorPane mainPane;
    public static Button InfoOKbutton;

    private static Text text;
    public static void loadInfoWindow(ObservableList data){//TODO сделать окно инфы не расширяющимся
        drawPanes(data);
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 550, 150);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
    public static void drawPanes(ObservableList data){
        mainPane=new AnchorPane();
        text = new Text("Тип коллекции: "+data.getClass()+"\nКоличество элементов: "+data.size());
        mainPane.getChildren().add(text);
        AnchorPane.setTopAnchor(text,20.0);
        AnchorPane.setLeftAnchor(text,20.0);

    }
    public static void drawButton(){
        InfoOKbutton =new Button("Ok");
        mainPane.getChildren().add(InfoOKbutton);
        AnchorPane.setRightAnchor(InfoOKbutton,220.0);
        AnchorPane.setLeftAnchor(InfoOKbutton,220.0);
        AnchorPane.setBottomAnchor(InfoOKbutton, 32.0);
    }
    private static void setCSS(){
        InfoOKbutton.setId("button");
    }
    private static void setControllers(){
        MainScreenController.InfoOKbutton(InfoOKbutton);
    }
}
