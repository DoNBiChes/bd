package com.example.bd;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.sql.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SubjectsController implements Initializable {
    //объявляем все элементы с интерфейса Students.fxml и связываем их с классом Student
    @FXML
    TableView<Subject> subjectsTable;
    @FXML
    TableColumn<Subject,String> nameCol;
    @FXML TableColumn<Subject,String> hoursCol;
    @FXML TableColumn<Subject,String> semesterCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//создаем ArrayList, в котором будут храниться все объекты класса Student, загруженные из БД
        ArrayList<Subject> subjects=new ArrayList<Subject>();
        Connection conn=null; //для соединения с БД
        Statement stmt=null; //для формирования выражений SQL
        Statement stmt1=null; //для формирования выражений SQL
        ResultSet rs=null; //для результатов выполнения команд SQL
//связываем колонки таблицы с полями класса Student
        nameCol.setCellValueFactory(new PropertyValueFactory<Subject, String>("name"));
        hoursCol.setCellValueFactory(new PropertyValueFactory<Subject, String>("hours"));
        semesterCol.setCellValueFactory(new PropertyValueFactory<Subject, String>("semester"));
//связываем предпочитаемую ширину колонок с шириной окна, задавая пропорции изменения колонок
        nameCol.prefWidthProperty().bind(subjectsTable.widthProperty().divide(6));
        hoursCol.prefWidthProperty().bind(subjectsTable.widthProperty().divide(9));
        semesterCol.prefWidthProperty().bind(subjectsTable.widthProperty().divide(6));
        try { //работа с БД ведется только внутри блока try ... catch
            Class.forName("org.h2.Driver"); //подгружаем драйвер для H2
            try { //еще один блок try ... catch
// получаем доступ к БД jdbc:h2:file:university1 – находится в текущем каталоге проекта
                conn=DriverManager.getConnection("jdbc:h2:C:/Users/РБТ/Downloads/bd/myBD","sa","");
//получаем объект для выполнения команд SQL
                stmt=conn.createStatement();
// еще один объект для выполнения команд SQL
                stmt1=conn.createStatement();
//объекту rs присваиваем результат выполнения команды SQL
                rs=stmt.executeQuery("select subject.subj_name, subject.hours," +
                        " subject.semester from subject");
// цикл по всем записям из SQL-запроса
                while (rs.next()) {
//записываем в массив students объекты класса Student с результатами запроса из БД
                    subjects.add(new Subject(rs.getString("subj_name"),
                            rs.getString("hours"), rs.getString("semester")));
                }
            } catch (SQLException ex) { //обрабатываем ошибки при работе с БД
                Logger.getLogger(SubjectsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) { //обрабатываем исключение для загрузки H2
            Logger.getLogger(SubjectsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally { //по окончании работы корректно закрываем соединение с БД
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(SubjectsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
//создаем список типа ObservableList, в который записываем все содержимое массива students
        ObservableList<Subject> data= FXCollections.observableArrayList(subjects);
        subjectsTable.setItems(data);//загружаем все из data в таблицу на окне
    }
}
