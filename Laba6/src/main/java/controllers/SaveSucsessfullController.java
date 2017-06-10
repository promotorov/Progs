package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import screens.SaveSucsessfullWindow;

/**
 * Created by danil on 10.06.2017.
 */
public class SaveSucsessfullController {
    public static void buttonOkSucsessfull(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) SaveSucsessfullWindow.getInstace().getButtonOkInfo().getScene().getWindow();
                stage.close();
            }
        });
    }
}
