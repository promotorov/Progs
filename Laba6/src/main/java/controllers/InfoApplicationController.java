package controllers;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import screens.InfoApplicationWindow;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by danil on 10.06.2017.
 */
public class InfoApplicationController {
    public static void buttonOkInfo(Button button, TextArea textArea) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stage = (Stage) InfoApplicationWindow.getInstace().getButtonOkInfo().getScene().getWindow();
                InfoApplicationWindow.getInstace().setTeext(textArea.getText());
                stage.close();
            }
        });
    }

    public static void autoComplet(TextArea textArea){;
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        String word;
                        String subWord;
                        boolean contains;
                        word = "";
                        subWord = "";
                        contains = false;
                        int newSize = textArea.getText().length();
                        if (newSize >= InfoApplicationWindow.getInstace().getOldsize()) {
                            //System.out.println(textArea.getText());
                            char prob = ' ';
                            int last = textArea.getText().lastIndexOf(prob);
                            String temp = textArea.getText().substring(last + 1);
                            HashSet set = new autoComplete.Dictionary().getDictionary();
                            Iterator<String> iterator = set.iterator();
                            while (iterator.hasNext()) {
                                word = iterator.next();
                                if (word.length() <= temp.length() || temp.length() == 0) continue;
                                subWord = word.substring(0, temp.length());
                                if (subWord.equals(temp)) {
                                    contains = true;
                                    break;
                                }
                            }
                            if (contains == true) {
                                textArea.appendText(word.substring(temp.length()));
                                int sub=0;
                                sub=textArea.getText().length()-word.substring(temp.length()).length();
                                textArea.selectRange(sub,textArea.getText().length());
                            }
                        }
                        InfoApplicationWindow.getInstace().setOldsize(textArea.getText().length());
                    }
                });
            }
        });
    }
}
