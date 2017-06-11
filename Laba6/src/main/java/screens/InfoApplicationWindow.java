package screens;

import controllers.InfoApplicationController;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import controllers.MainScreenController;

/**
 * Created by vladp on 07.05.2017.
 */
public class InfoApplicationWindow {
    private Stage primaryStage;
    private Scene scene;
    private int oldsize;
    private AnchorPane mainPane;
    private TextArea text;
    private Button buttonOkInfo;
    private String teext="Auto Complet";
    private static InfoApplicationWindow infoApplicationWindow;

    private InfoApplicationWindow(){}
    public static synchronized InfoApplicationWindow getInstace(){
        if(infoApplicationWindow==null){
            infoApplicationWindow=new InfoApplicationWindow();
        }
        return infoApplicationWindow;
    }

    private void drawPanes(){
        mainPane=new AnchorPane();
    }

    private void drawItems(){
        text=new TextArea();
        text.setText(teext);
        mainPane.getChildren().add(text);
        AnchorPane.setTopAnchor(text, 20.0);
        AnchorPane.setLeftAnchor(text, 20.0);
        AnchorPane.setRightAnchor(text, 20.0);
        AnchorPane.setBottomAnchor(text, 80.0);
        buttonOkInfo=new Button("Ok");
        mainPane.getChildren().add(buttonOkInfo);
        AnchorPane.setRightAnchor(buttonOkInfo,270.0);
        AnchorPane.setLeftAnchor(buttonOkInfo,270.0);
        AnchorPane.setBottomAnchor(buttonOkInfo, 32.0);
    }

    private void setControllers(){
        InfoApplicationController.buttonOkInfo(buttonOkInfo, text);
        InfoApplicationController.autoComplet(text);
    }

    public void loadInfoScreen(){
        drawPanes();
        drawItems();
        setControllers();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        scene=new Scene(mainPane, 600, 330);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int getOldsize() {
        return oldsize;
    }

    public void setOldsize(int oldsize) {
        this.oldsize = oldsize;
    }

    public String getTeext() {
        return teext;
    }

    public void setTeext(String teext) {
        this.teext = teext;
    }

    public Button getButtonOkInfo() {
        return buttonOkInfo;
    }
}
