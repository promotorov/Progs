package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import screens.InfoWindow;

/**
 * Created by danil on 10.06.2017.
 */
public class InfoController {
    public static void InfoOKbutton(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) InfoWindow.getInstace().getInfoOKbutton().getScene().getWindow();
                stage.close();
            }
        });
    }
}
