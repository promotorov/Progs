package screens;

import controllers.InfoController;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import controllers.MainScreenController;

/**
 * Created by danil on 04.05.2017.
 */
public class InfoWindow {
    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainPane;
    private Button InfoOKbutton;
    private Text text;
    private static InfoWindow infoWindow;

    private InfoWindow(){}
    public static synchronized InfoWindow getInstace(){
        if(infoWindow==null){
            infoWindow=new InfoWindow();
        }
        return infoWindow;
    }
    public void loadInfoWindow(ObservableList data){
        drawPanes(data);
        drawButton();
        setControllers();
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        scene=new Scene(mainPane, 550, 150);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
    public void drawPanes(ObservableList data){
        mainPane=new AnchorPane();
        text = new Text("Тип коллекции: "+data.getClass()+"\nКоличество элементов: "+data.size());
        mainPane.getChildren().add(text);
        AnchorPane.setTopAnchor(text,20.0);
        AnchorPane.setLeftAnchor(text,20.0);

    }
    public void drawButton(){
        InfoOKbutton =new Button("Ok");
        mainPane.getChildren().add(InfoOKbutton);
        AnchorPane.setRightAnchor(InfoOKbutton,220.0);
        AnchorPane.setLeftAnchor(InfoOKbutton,220.0);
        AnchorPane.setBottomAnchor(InfoOKbutton, 32.0);
    }
    private void setCSS(){
        InfoOKbutton.setId("button");
    }
    private void setControllers(){
        InfoController.InfoOKbutton(InfoOKbutton);
    }

    public Button getInfoOKbutton() {
        return InfoOKbutton;
    }
}
