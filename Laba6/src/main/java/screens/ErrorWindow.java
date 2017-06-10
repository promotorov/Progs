package screens;

import controllers.ErrorController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import controllers.MainScreenController;

/**
 * Created by vladp on 15.05.2017.
 */
public class ErrorWindow {
    private boolean error=false;
    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainPane;
    private Text text;
    public Button buttonOkInfo;
    private HBox hBox;
    private static ErrorWindow errorWindow;

    private ErrorWindow(){}
    public static synchronized ErrorWindow getInstace(){
        if(errorWindow==null){
            errorWindow=new ErrorWindow();
        }
        return errorWindow;
    }

    private void drawPanes(){
        mainPane=new AnchorPane();
    }

    public void drawItems(String s){
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

    public void setControllers(){
        ErrorController.buttonOkError(buttonOkInfo);
    }

    public void loadInfoScreen(String s){
        drawPanes();
        drawItems(s);
        setControllers();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        scene=new Scene(mainPane, 600, 130);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Button getButtonOkInfo() {
        return buttonOkInfo;
    }
}

