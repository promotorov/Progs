package sample;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import laba2.FoodResidus;
import laba2.XMLworker;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by vladp on 07.05.2017.
 */
public class SettingsWindow {
    private static final int FONT_SIZE=15;
    private static final boolean EDITABLE=true;
    private static final Color COLR=Color.web("#000000");

    private static int font_size=15;
    private static boolean editable=true;
    private static Color color=Color.web("#000000");

    private static Stage primaryStage;
    private static AnchorPane mainPane;
    private static Scene scene;
    private static Text textColor;
    private static ColorPicker picker;
    private static HBox settingColorTable;
    private static HBox settingEditTable;
    private static Text textEdit;
    private static RadioButton editEnable;
    private static RadioButton editDisable;
    private static ToggleGroup group;
    private static HBox settingFontSize;
    private static Text textSize;
    private static NumberTextfield fontSize;
    private static Button buttonSave;
    private static Button buttonCancel;
    private static Button buttonDefaultSettings;
    private static HBox buttons;
    private static HBox defaults;

    private static void drawPanes(){
        mainPane=new AnchorPane();
    }

    private static void drawItems(){
        picker=new ColorPicker(color);
        textColor=new Text("Цвет текста кнопок: ");
        settingColorTable=new HBox(textColor, picker);
        settingColorTable.setPadding(new Insets(0, 28, 0, 0));
        settingColorTable.setAlignment(Pos.CENTER_LEFT);
        settingColorTable.setSpacing(40);
        mainPane.getChildren().add(settingColorTable);
        AnchorPane.setTopAnchor(settingColorTable, 22.0);
        AnchorPane.setLeftAnchor(settingColorTable, 10.0);
        AnchorPane.setRightAnchor(settingColorTable, 10.0);

        editDisable=new RadioButton();
        editEnable=new RadioButton();
        group=new ToggleGroup();
        editDisable.setToggleGroup(group);
        editDisable.setText("Нет");
        editEnable.setToggleGroup(group);
        editEnable.setText("Да");
        if(editable) editEnable.setSelected(true);
        else editDisable.setSelected(true);
        textEdit=new Text("Редактирование таблицы: ");
        settingEditTable=new HBox(textEdit, editEnable, editDisable);
        settingEditTable.setAlignment(Pos.CENTER_LEFT);
        settingEditTable.setSpacing(15);
        mainPane.getChildren().add(settingEditTable);
        AnchorPane.setTopAnchor(settingEditTable, 65.0);
        AnchorPane.setLeftAnchor(settingEditTable, 10.0);
        AnchorPane.setRightAnchor(settingEditTable, 10.0);
        fontSize=new NumberTextfield();
        fontSize.setText(String.valueOf(font_size));
        textSize=new Text("Размер шрифта кнопок: ");
        settingFontSize=new HBox(textSize, fontSize);
        settingFontSize.setPadding(new Insets(0, 28, 0, 0));
        settingFontSize.setAlignment(Pos.CENTER_LEFT);
        settingFontSize.setSpacing(10);
        mainPane.getChildren().add(settingFontSize);
        AnchorPane.setTopAnchor(settingFontSize, 98.0);
        AnchorPane.setLeftAnchor(settingFontSize, 10.0);
        AnchorPane.setRightAnchor(settingFontSize, 10.0);
        buttonSave=new Button("Сохранить");
        buttonCancel=new Button("Отменить");
        buttonDefaultSettings=new Button("По умолчанию");
        buttons=new HBox(buttonSave, buttonCancel);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(30);
        mainPane.getChildren().add(buttons);
        AnchorPane.setLeftAnchor(buttons, 0.0);
        AnchorPane.setTopAnchor(buttons, 140.0);
        AnchorPane.setRightAnchor(buttons, 0.0);
        defaults=new HBox(buttonDefaultSettings);
        defaults.setAlignment(Pos.CENTER);
        mainPane.getChildren().add(defaults);
        AnchorPane.setLeftAnchor(defaults, 0.0);
        AnchorPane.setTopAnchor(defaults, 190.0);
        AnchorPane.setRightAnchor(defaults, 0.0);
    }

    private static void setControllers(){
        SettingsScreenController.buttonCancel(buttonCancel);
        SettingsScreenController.buttonDefaultSettings(buttonDefaultSettings);
        SettingsScreenController.buttonSave(buttonSave);
        SettingsScreenController.colorPicker(picker);
        SettingsScreenController.setFontSize(fontSize);
    }

    public static void loadMainScreen(){
        drawPanes();
        drawItems();
        setControllers();
        System.out.println(COLR.toString());
        primaryStage=new Stage();
        scene=new Scene(mainPane, 350, 230);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        SettingsScreenController.closeWindow(primaryStage);
    }

    public static Color getColor() {
        return color;
    }

    public static void setColor(Color color) {
        SettingsWindow.color = color;
    }

    public static int getFont_size() {
        return font_size;
    }

    public static void setFont_size(int font_size) {
        SettingsWindow.font_size = font_size;
    }

    public static boolean isEditable() {
        return editable;
    }

    public static void setEditable(boolean editable) {
        SettingsWindow.editable = editable;
    }

    public static ColorPicker getPicker() {
        return picker;
    }

    public static void setPicker(ColorPicker picker) {
        SettingsWindow.picker = picker;
    }

    public static RadioButton getEditEnable() {
        return editEnable;
    }

    public static void setEditEnable(RadioButton editEnable) {
        SettingsWindow.editEnable = editEnable;
    }

    public static NumberTextfield getFontSize() {
        return fontSize;
    }

    public static void setFontSize(NumberTextfield fontSize) {
        SettingsWindow.fontSize = fontSize;
    }

    public static boolean isEDITABLE() {
        return EDITABLE;
    }

    public static Color getCOLR() {
        return COLR;
    }
    public static int getFONT_SIZE(){
        return FONT_SIZE;
    }
}
