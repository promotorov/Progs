package screens;

import controllers.MainScreenController;
import controllers.SaveSucsessfullController;
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
public class SaveSucsessfullWindow {
    private boolean error=false;
    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainPane;
    private Text text;
    private Button buttonOkInfo;
    private HBox hBox;
    private static SaveSucsessfullWindow saveSucsessfullWindow;

    private SaveSucsessfullWindow(){}
    public static synchronized SaveSucsessfullWindow getInstace(){
        if(saveSucsessfullWindow==null){
            saveSucsessfullWindow=new SaveSucsessfullWindow();
        }
        return saveSucsessfullWindow;
    }

    private void drawPanes(){
        mainPane=new AnchorPane();
    }

    private void drawItems(){
        text=new Text("Сохранено.");
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

    private void setControllers(){
        SaveSucsessfullController.buttonOkSucsessfull(buttonOkInfo);
    }

    public void loadInfoScreen(){
        drawPanes();
        drawItems();
        setControllers();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        scene=new Scene(mainPane, 600, 130);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public Button getButtonOkInfo() {
        return buttonOkInfo;
    }

}

