package sample;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import laba2.FoodResidus;
import laba2.XMLworker;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by vladp on 07.05.2017.
 */
public class InfoApplicationWindow {
    private static Stage primaryStage;
    private static Scene scene;
    private static AnchorPane mainPane;
    private static Text text;
    public static Button buttonOkInfo;

    private static void drawPanes(){
        mainPane=new AnchorPane();
    }

    public static void drawItems(){
        text=new Text("Правьте, корректируйте и переписывайте заново — для максимального эффекта.\nПроверьте, как описание будет выглядеть на экране iPhone или iPad.\nРаботайте до тех пор, пока оно не станет гладким, отточенным и привлекательным.");
        mainPane.getChildren().add(text);
        AnchorPane.setTopAnchor(text, 40.0);
        AnchorPane.setLeftAnchor(text, 20.0);
        buttonOkInfo=new Button("Ok");
        mainPane.getChildren().add(buttonOkInfo);
        AnchorPane.setRightAnchor(buttonOkInfo,310.0);
        AnchorPane.setLeftAnchor(buttonOkInfo,310.0);
        AnchorPane.setBottomAnchor(buttonOkInfo, 32.0);
    }

    public static void setControllers(){
        MainScreenController.buttonOkInfo(buttonOkInfo);
    }

    public static void loadInfoScreen(){
        drawPanes();
        drawItems();
        setControllers();
        primaryStage=new Stage();
        primaryStage.setResizable(false);
        scene=new Scene(mainPane, 700, 170);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
