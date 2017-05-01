package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Created by vladp on 30.04.2017.
 */
public class MainScreenController {
    public static void buttonFiltr(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Устанавливаем фильтры");
            }
        });
    }
    public static void buttonDelFiltr(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Удаляем фильтры");
            }
        });
    }
    public static void buttonInfo(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Выводим инфу");
            }
        });
    }
    public static void buttonRemoveEl(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Удаляем элементы");
            }
        });
    }
    public static void buttonChoose(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Выбираем элемент");
            }
        });
    }
    public static void buttonSave(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Сохраняем");
            }
        });
    }
    public static void buttonClear(Button button){
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("Очищаем");
            }
        });
    }
}
