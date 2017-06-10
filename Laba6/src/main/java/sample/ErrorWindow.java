package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by vladp on 15.05.2017.
 */
public class ErrorWindow {
    private static boolean error=false;
    private static Stage primaryStage;
    private static Scene scene;
    private static AnchorPane mainPane;
    private static Text text;
    public static Button buttonOkInfo;
    private static HBox hBox;

    private static void drawPanes(){
        mainPane=new AnchorPane();
    }

    public static void drawItems(String s){
        text=new Text(s);
        hBox=new HBox();
        hBox.getChildren().add(text);
        hBox.setAlignment(Pos.CENTER);
        AnchorPane.setTopAnchor(hBox, 40.0);
        AnchorPane.setLeftAnchor(hBox, 20.0);
        AnchorPane.setRightAnchor(hBox,20.0);
        buttonOkInfo=new Button("Ok");
        mainPane.getChildren().add(buttonOkInfo);
        mainPane.getChildren().add(hBox);
        AnchorPane.setRightAnchor(buttonOkInfo,270.0);
        AnchorPane.setLeftAnchor(buttonOkInfo,270.0);
        AnchorPane.setBottomAnchor(buttonOkInfo, 32.0);
    }

    public static void setControllers(){
        MainScreenController.buttonOkError(buttonOkInfo);
    }

    public static void loadInfoScreen(String s){
        drawPanes();
        drawItems(s);
        setControllers();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        scene=new Scene(mainPane, 600, 130);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static boolean isError() {
        return error;
    }

    public static void setError(boolean error) {
        ErrorWindow.error = error;
    }

    public static Button getButtonOkInfo() {
        return buttonOkInfo;
    }
}

