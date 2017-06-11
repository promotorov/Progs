package main;

import javafx.application.Application;
import javafx.stage.Stage;
import screens.MainScreen;

public class MainClient extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception{
        MainScreen.getInstace().loadMainScreen();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
