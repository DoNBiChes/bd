package com.example.bd;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class FXMLdbWindowController {

    @FXML private BorderPane mainPane;

    @FXML
    private void handleShowStudents(ActionEvent event) throws IOException {
        System.out.println("StudentsTable!!"); //вывод в консоль для проверки срабатывания
//создаем объект класса Parent и в него загружаем fxml файл, который хотим показать
//файл этот создадим позже
        Parent root = FXMLLoader.load(getClass().getResource("Students.fxml"));
//создаем окно
        Stage stage=new Stage();
//в него помещаем содержимое root
        stage.setScene(new Scene(root));
//делаем окно модальным
        stage.initModality(Modality.WINDOW_MODAL);
//и задаем для него главное окно – окно, в котором находится mainPane
        stage.initOwner(mainPane.getScene().getWindow()); //mainPane - BorderPane
        stage.show(); //выводим созданное окно на экран
    }
}

