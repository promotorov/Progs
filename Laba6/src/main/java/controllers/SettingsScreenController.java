package controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import screens.MainScreen;
import any.NumberTextfield;
import screens.SettingsWindow;

/**
 * Created by vladp on 08.05.2017.
 */
public class SettingsScreenController {

    private static int color=0;

    public static void buttonSave(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("save");
                SettingsWindow.getInstance().setFont_size(Integer.parseInt(SettingsWindow.getInstance().getFontSize().getText()));
                SettingsWindow.getInstance().setColor(SettingsWindow.getInstance().getPicker().getValue());
                if(!SettingsWindow.getInstance().getEditEnable().isSelected()){
                    MainScreen.getInstace().getTable().setEditable(false);
                    SettingsWindow.getInstance().setEditable(false);
                }
                else {
                    MainScreen.getInstace().getTable().setEditable(true);
                    SettingsWindow.getInstance().setEditable(true);
                }
                Stage stage = (Stage) SettingsWindow.getInstance().getButtonSave().getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void buttonCancel(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("cancel");
                SettingsWindow.getInstance().getFontSize().setText(String.valueOf(SettingsWindow.getInstance().getFont_size()));
                SettingsWindow.getInstance().getPicker().setValue(SettingsWindow.getInstance().getColor());
                if(SettingsWindow.getInstance().isEditable()){
                    SettingsWindow.getInstance().getEditEnable().setSelected(true);
                }
                else {
                    SettingsWindow.getInstance().getEditEnable().setSelected(false);;
                }
                MainScreen.getInstace().getButtonInfo().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonChoose().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonClear().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonFilter().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonRemoveEl().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonSave().setTextFill(SettingsWindow.getInstance().getPicker().getValue());

                MainScreen.getInstace().getButtonInfo().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonChoose().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonClear().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonFilter().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonRemoveEl().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonSave().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
            }
        });
    }
    public static void buttonDefaultSettings(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("default");
                SettingsWindow.getInstance().getFontSize().setText(String.valueOf(SettingsWindow.getInstance().getFONT_SIZE()));
                SettingsWindow.getInstance().getPicker().setValue(SettingsWindow.getInstance().getCOLR());
                if(SettingsWindow.getInstance().isEDITABLE()){
                    SettingsWindow.getInstance().getEditEnable().setSelected(true);
                }
                else {
                    SettingsWindow.getInstance().getEditEnable().setSelected(false);;
                }
                MainScreen.getInstace().getButtonInfo().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonChoose().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonClear().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonFilter().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonRemoveEl().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonSave().setTextFill(SettingsWindow.getInstance().getPicker().getValue());

                MainScreen.getInstace().getButtonInfo().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonChoose().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonClear().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonFilter().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonRemoveEl().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonSave().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
            }
        });
    }
    public static void colorPicker(ColorPicker colorPicker){
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainScreen.getInstace().getButtonInfo().setTextFill(colorPicker.getValue());
                MainScreen.getInstace().getButtonChoose().setTextFill(colorPicker.getValue());
                MainScreen.getInstace().getButtonClear().setTextFill(colorPicker.getValue());
                MainScreen.getInstace().getButtonFilter().setTextFill(colorPicker.getValue());
                MainScreen.getInstace().getButtonRemoveEl().setTextFill(colorPicker.getValue());
                MainScreen.getInstace().getButtonSave().setTextFill(colorPicker.getValue());
            }
        });
    }
    public static void setFontSize(NumberTextfield textfield){
        textfield.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!(Integer.parseInt(textfield.getText())>18 || Integer.parseInt(textfield.getText())<11)) {
                    MainScreen.getInstace().getButtonInfo().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getInstace().getButtonChoose().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getInstace().getButtonClear().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getInstace().getButtonFilter().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getInstace().getButtonRemoveEl().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getInstace().getButtonSave().setStyle("-fx-font-size: " + textfield.getText());
                }
                else {
                    textfield.setText(String.valueOf(SettingsWindow.getInstance().getFont_size()));
                }
            }
        });
    }

    public static void closeWindow(Stage stage){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                SettingsWindow.getInstance().getFontSize().setText(String.valueOf(SettingsWindow.getInstance().getFont_size()));
                SettingsWindow.getInstance().getPicker().setValue(SettingsWindow.getInstance().getColor());
                if(SettingsWindow.getInstance().isEditable()){
                    SettingsWindow.getInstance().getEditEnable().setSelected(true);
                }
                else {
                    SettingsWindow.getInstance().getEditEnable().setSelected(false);;
                }
                MainScreen.getInstace().getButtonInfo().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonChoose().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonClear().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonFilter().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonRemoveEl().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonSave().setTextFill(SettingsWindow.getInstance().getPicker().getValue());
                MainScreen.getInstace().getButtonInfo().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonChoose().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonClear().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonFilter().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonRemoveEl().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                MainScreen.getInstace().getButtonSave().setStyle("-fx-font-size: " +SettingsWindow.getInstance().getFontSize().getText());
                stage.close();
            }
        });
    }
}
