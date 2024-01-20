package com.example.bd;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLdbWindowController.fxml"));
        BorderPane root = loader.load();
        FXMLdbWindowController controller = loader.getController();
        //controller.setStage(stage);
        stage.setTitle("Hello World"); //создаем окно с заголовком
        stage.setScene(new Scene(root, 600, 400)); //вставляем в окно сцену
        stage.show(); //показываем окно
    }

    public static void main(String[] args) {
        launch(args);
    }
}
