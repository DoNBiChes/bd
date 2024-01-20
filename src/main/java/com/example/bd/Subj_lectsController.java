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

public class Subj_lectsController implements Initializable {
    //объявляем все элементы с интерфейса Students.fxml и связываем их с классом Student
    @FXML
    TableView<Subj_lect> subj_lectsTable;
    @FXML TableColumn<Subj_lect,String> surnameCol;
    @FXML TableColumn<Subj_lect,String> nameCol;
    @FXML TableColumn<Subj_lect,String> cityCol;
    @FXML TableColumn<Subj_lect,String> univerCol;
    @FXML TableColumn<Subj_lect,String> subjectCol;
    @FXML TableColumn<Subj_lect,String> hoursCol;
    @FXML TableColumn<Subj_lect,String> semesterCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//создаем ArrayList, в котором будут храниться все объекты класса Student, загруженные из БД
        ArrayList<Subj_lect> subj_lects=new ArrayList<Subj_lect>();
        Connection conn=null; //для соединения с БД
        Statement stmt=null; //для формирования выражений SQL
        Statement stmt1=null; //для формирования выражений SQL
        ResultSet rs=null; //для результатов выполнения команд SQL
//связываем колонки таблицы с полями класса Student
        surnameCol.setCellValueFactory(new PropertyValueFactory<Subj_lect, String>("surname"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Subj_lect, String>("name"));
        cityCol.setCellValueFactory(new PropertyValueFactory<Subj_lect, String>("city"));
        univerCol.setCellValueFactory(new PropertyValueFactory<Subj_lect, String>("univer"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<Subj_lect, String>("subj_name"));
        hoursCol.setCellValueFactory(new PropertyValueFactory<Subj_lect, String>("hours"));
        semesterCol.setCellValueFactory(new PropertyValueFactory<Subj_lect, String>("semester"));
//связываем предпочитаемую ширину колонок с шириной окна, задавая пропорции изменения колонок
        surnameCol.prefWidthProperty().bind(subj_lectsTable.widthProperty().divide(6));
        nameCol.prefWidthProperty().bind(subj_lectsTable.widthProperty().divide(6));
        cityCol.prefWidthProperty().bind(subj_lectsTable.widthProperty().divide(9));
        univerCol.prefWidthProperty().bind(subj_lectsTable.widthProperty().divide(6));
        subjectCol.prefWidthProperty().bind(subj_lectsTable.widthProperty().divide(6));
        hoursCol.prefWidthProperty().bind(subj_lectsTable.widthProperty().divide(9));
        semesterCol.prefWidthProperty().bind(subj_lectsTable.widthProperty().divide(6));
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
                rs=stmt.executeQuery("select lecturer.surname, lecturer.name, lecturer.city, " +
                        "university.univ_name, subject.subj_name, subject.hours, subject.semester " +
                        "from subj_lect, lecturer, university, subject where subj_lect.lecturer_id=lecturer.lecturer_id " +
                        "and subj_lect.subj_id=subject.subj_id and lecturer.univ_id=university.univ_id");
// цикл по всем записям из SQL-запроса
                while (rs.next()) {
//записываем в массив students объекты класса Student с результатами запроса из БД
                    subj_lects.add(new Subj_lect(rs.getString("surname"),rs.getString("name"),
                            rs.getString("city"),rs.getString("univ_name"),rs.getString("subj_name"),
                            rs.getString("hours"),rs.getString("semester")));
                }
            } catch (SQLException ex) { //обрабатываем ошибки при работе с БД
                Logger.getLogger(Subj_lectsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) { //обрабатываем исключение для загрузки H2
            Logger.getLogger(Subj_lectsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally { //по окончании работы корректно закрываем соединение с БД
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Subj_lectsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
//создаем список типа ObservableList, в который записываем все содержимое массива students
        ObservableList<Subj_lect> data= FXCollections.observableArrayList(subj_lects);
        subj_lectsTable.setItems(data);//загружаем все из data в таблицу на окне
    }
}