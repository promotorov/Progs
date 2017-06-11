package screens;

import controllers.SettingsScreenController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import any.NumberTextfield;

/**
 * Created by vladp on 07.05.2017.
 */
public class SettingsWindow {
    private final int FONT_SIZE=15;
    private final boolean EDITABLE=true;
    private final Color COLR=Color.web("#000000");

    private int font_size=15;
    private boolean editable=true;
    private Color color=Color.web("#000000");

    private Stage primaryStage;
    private AnchorPane mainPane;
    private Scene scene;
    private Text textColor;
    private ColorPicker picker;
    private HBox settingColorTable;
    private HBox settingEditTable;
    private Text textEdit;
    private RadioButton editEnable;
    private RadioButton editDisable;
    private ToggleGroup group;
    private HBox settingFontSize;
    private Text textSize;
    private NumberTextfield fontSize;
    private Button buttonSave;
    private Button buttonCancel;
    private Button buttonDefaultSettings;
    private HBox buttons;
    private HBox defaults;
    private static SettingsWindow settingsWindow;

    private SettingsWindow(){}
    public static synchronized SettingsWindow getInstance(){
        if(settingsWindow==null){
            settingsWindow=new SettingsWindow();
        }
        return settingsWindow;
    }

    private void drawPanes(){
        mainPane=new AnchorPane();
    }

    private void drawItems(){
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

    private void setControllers(){
        SettingsScreenController.buttonCancel(buttonCancel);
        SettingsScreenController.buttonDefaultSettings(buttonDefaultSettings);
        SettingsScreenController.buttonSave(buttonSave);
        SettingsScreenController.colorPicker(picker);
        SettingsScreenController.setFontSize(fontSize);
    }

    public void loadMainScreen(){
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getFont_size() {
        return font_size;
    }

    public void setFont_size(int font_size) {
        this.font_size = font_size;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    public ColorPicker getPicker() {
        return picker;
    }

    public void setPicker(ColorPicker picker) {
        this.picker = picker;
    }

    public RadioButton getEditEnable() {
        return editEnable;
    }

    public void setEditEnable(RadioButton editEnable) {
        this.editEnable = editEnable;
    }

    public NumberTextfield getFontSize() {
        return fontSize;
    }

    public void setFontSize(NumberTextfield fontSize) {
        this.fontSize = fontSize;
    }

    public boolean isEDITABLE() {
        return EDITABLE;
    }

    public Color getCOLR() {
        return COLR;
    }
    public int getFONT_SIZE(){
        return FONT_SIZE;
    }
    public Button getButtonSave(){return buttonSave;}
}
