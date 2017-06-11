package screens;

import controllers.MainScreenController;
import controllers.SaveController;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Created by danil on 07.05.2017.
 */
public class SaveWindow {
    private Stage primaryStage;
    private Scene scene;
    private AnchorPane mainPane;
    private Button SaveDefaultButton;
    private Button SaveChooseButton;
    private static SaveWindow saveWindow;

    private SaveWindow(){}
    public static synchronized SaveWindow getInstace(){
        if(saveWindow==null){
            saveWindow=new SaveWindow();
        }
        return saveWindow;
    }

    private Text text;
    public void loadSaveWindow(ObservableList data){
        drawPanes();
        drawButton();
        setControllers(data);
        setCSS();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        //primaryStage.getIcons().add(new Image("file:icon.png"));//TODO определить иконку
        scene=new Scene(mainPane, 390, 150);
        primaryStage.setScene(scene);
        primaryStage.getScene().getStylesheets().add("css/Main.css");
        primaryStage.show();
    }
    private void drawPanes(){
        mainPane=new AnchorPane();
        text = new Text("Выберете файл или сохраните по умолчанию?");
        mainPane.getChildren().add(text);
        AnchorPane.setTopAnchor(text,20.0);
        AnchorPane.setLeftAnchor(text,20.0);
        AnchorPane.setRightAnchor(text,20.0);
    }
    private void drawButton(){
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
    private void setCSS(){
        SaveDefaultButton.setId("button");
        SaveChooseButton.setId("button");
    }
    private void setControllers(ObservableList data){
        SaveController.SaveDefaultButton(SaveDefaultButton,data);
        SaveController.SaveChooseButton(SaveChooseButton,data);
    }

    public Button getSaveDefaultButton() {
        return SaveDefaultButton;
    }

    public Button getSaveChooseButton() {
        return SaveChooseButton;
    }
}
