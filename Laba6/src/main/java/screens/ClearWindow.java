package screens;

import controllers.ClearController;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import controllers.MainScreenController;

/**
 * Created by danil on 05.05.2017.
 */
public class ClearWindow {
    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainPane;
    private Button ClearOKbutton;
    private Button ClearCancelButton;
    private  Text text;
    private static ClearWindow clearWindow;

    private ClearWindow(){}
    public static synchronized ClearWindow getInstace(){
        if(clearWindow==null){
            clearWindow=new ClearWindow();
        }
        return clearWindow;
    }
    public void loadClearWindow(ObservableList data){
        drawPanes();
        drawButton();
        setControllers(data);
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 400, 150);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
    public void drawPanes(){
        mainPane=new AnchorPane();
        text = new Text("Вы точено хотите удалить все элементы коллекции?");
        mainPane.getChildren().add(text);
        AnchorPane.setTopAnchor(text,20.0);
        AnchorPane.setLeftAnchor(text,20.0);
        AnchorPane.setRightAnchor(text,20.0);
    }
    public void drawButton(){
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
    private void setCSS(){
        ClearOKbutton.setId("button");
        ClearCancelButton.setId("button");
    }
    private void setControllers(ObservableList data){
        ClearController.ClearOKbutton(ClearOKbutton,data);
        ClearController.ClearCancelButton(ClearCancelButton);
    }

    public Button getClearOKbutton() {
        return ClearOKbutton;
    }

    public Button getClearCancelButton() {
        return ClearCancelButton;
    }
}
