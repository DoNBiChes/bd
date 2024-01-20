package com.example.bd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Загрузка корневого элемента из FXML-файла
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLdbWindow.fxml"));
            Parent root = loader.load();

            // Получение контроллера из загрузчика
            FXMLdbWindowController controller = loader.getController();

            // Настройка основной сцены
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Ваше приложение");
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
