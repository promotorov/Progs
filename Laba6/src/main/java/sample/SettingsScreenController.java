package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

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
                SettingsWindow.setFont_size(Integer.parseInt(SettingsWindow.getFontSize().getText()));
                SettingsWindow.setColor(SettingsWindow.getPicker().getValue());
                if(!SettingsWindow.getEditEnable().isSelected()){
                    MainScreen.getTable().setEditable(false);
                    SettingsWindow.setEditable(false);
                }
                else {
                    MainScreen.getTable().setEditable(true);
                    SettingsWindow.setEditable(true);
                }
                Stage stage = (Stage) SettingsWindow.getButtonSave().getScene().getWindow();
                stage.close();
            }
        });
    }
    public static void buttonCancel(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("cancel");
                SettingsWindow.getFontSize().setText(String.valueOf(SettingsWindow.getFont_size()));
                SettingsWindow.getPicker().setValue(SettingsWindow.getColor());
                if(SettingsWindow.isEditable()){
                    SettingsWindow.getEditEnable().setSelected(true);
                }
                else {
                    SettingsWindow.getEditEnable().setSelected(false);;
                }
                MainScreen.getButtonInfo().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonChoose().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonClear().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonDelFiler().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonFiler().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonRemoveEl().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonSave().setTextFill(SettingsWindow.getPicker().getValue());

                MainScreen.getButtonInfo().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonChoose().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonClear().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonDelFiler().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonFiler().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonRemoveEl().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonSave().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
            }
        });
    }
    public static void buttonDefaultSettings(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("default");
                SettingsWindow.getFontSize().setText(String.valueOf(SettingsWindow.getFONT_SIZE()));
                SettingsWindow.getPicker().setValue(SettingsWindow.getCOLR());
                if(SettingsWindow.isEDITABLE()){
                    SettingsWindow.getEditEnable().setSelected(true);
                }
                else {
                    SettingsWindow.getEditEnable().setSelected(false);;
                }
                MainScreen.getButtonInfo().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonChoose().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonClear().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonDelFiler().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonFiler().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonRemoveEl().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonSave().setTextFill(SettingsWindow.getPicker().getValue());

                MainScreen.getButtonInfo().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonChoose().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonClear().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonDelFiler().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonFiler().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonRemoveEl().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonSave().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
            }
        });
    }
    public static void colorPicker(ColorPicker colorPicker){
        colorPicker.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MainScreen.getButtonInfo().setTextFill(colorPicker.getValue());
                MainScreen.getButtonChoose().setTextFill(colorPicker.getValue());
                MainScreen.getButtonClear().setTextFill(colorPicker.getValue());
                MainScreen.getButtonDelFiler().setTextFill(colorPicker.getValue());
                MainScreen.getButtonFiler().setTextFill(colorPicker.getValue());
                MainScreen.getButtonRemoveEl().setTextFill(colorPicker.getValue());
                MainScreen.getButtonSave().setTextFill(colorPicker.getValue());
            }
        });
    }
    public static void setFontSize(NumberTextfield textfield){
        textfield.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(!(Integer.parseInt(textfield.getText())>18 || Integer.parseInt(textfield.getText())<11)) {
                    MainScreen.getButtonInfo().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getButtonChoose().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getButtonClear().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getButtonDelFiler().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getButtonFiler().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getButtonRemoveEl().setStyle("-fx-font-size: " + textfield.getText());
                    MainScreen.getButtonSave().setStyle("-fx-font-size: " + textfield.getText());
                }
                else {
                    textfield.setText(String.valueOf(SettingsWindow.getFont_size()));
                }
            }
        });
    }

    public static void closeWindow(Stage stage){
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                event.consume();
                SettingsWindow.getFontSize().setText(String.valueOf(SettingsWindow.getFont_size()));
                SettingsWindow.getPicker().setValue(SettingsWindow.getColor());
                if(SettingsWindow.isEditable()){
                    SettingsWindow.getEditEnable().setSelected(true);
                }
                else {
                    SettingsWindow.getEditEnable().setSelected(false);;
                }
                MainScreen.getButtonInfo().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonChoose().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonClear().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonDelFiler().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonFiler().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonRemoveEl().setTextFill(SettingsWindow.getPicker().getValue());
                MainScreen.getButtonSave().setTextFill(SettingsWindow.getPicker().getValue());

                MainScreen.getButtonInfo().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonChoose().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonClear().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonDelFiler().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonFiler().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonRemoveEl().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());
                MainScreen.getButtonSave().setStyle("-fx-font-size: " +SettingsWindow.getFontSize().getText());

                stage.close();
            }
        });
    }
}
