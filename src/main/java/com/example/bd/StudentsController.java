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

public class StudentsController implements Initializable {
    //объявляем все элементы с интерфейса Students.fxml и связываем их с классом Student
    @FXML
    TableView<Student> studentsTable;
    @FXML TableColumn<Student,String> surnameCol;
    @FXML
    TableColumn<Student,String> nameCol;
    @FXML TableColumn<Student,Integer> stipendCol;
    @FXML TableColumn<Student,Integer> kursCol;
    @FXML TableColumn<Student,String> cityCol;
    @FXML TableColumn<Student,String> birthdayCol;
    @FXML TableColumn<Student,String> univerCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//создаем ArrayList, в котором будут храниться все объекты класса Student, загруженные из БД
        ArrayList<Student> students=new ArrayList<Student>();
        Connection conn=null; //для соединения с БД
        Statement stmt=null; //для формирования выражений SQL
        Statement stmt1=null; //для формирования выражений SQL
        ResultSet rs=null; //для результатов выполнения команд SQL
//связываем колонки таблицы с полями класса Student
        surnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        stipendCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("stipend"));
        kursCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("kurs"));
        cityCol.setCellValueFactory(new PropertyValueFactory<Student, String>("city"));
        birthdayCol.setCellValueFactory(new PropertyValueFactory<Student, String>("birthday"));
        univerCol.setCellValueFactory(new PropertyValueFactory<Student, String>("univer"));
//связываем предпочитаемую ширину колонок с шириной окна, задавая пропорции изменения колонок
        surnameCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(6));
        nameCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(6));
        stipendCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(8));
        kursCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(15));
        cityCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(9));
        birthdayCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(7));
        univerCol.prefWidthProperty().bind(studentsTable.widthProperty().divide(6));
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
                rs=stmt.executeQuery("select student.surname, student.name, student.stipend," +
                        "student.kurs, student.city, student.birthday,university.univ_name" +
                        " from student, university where student.univ_id=university.univ_id");
// цикл по всем записям из SQL-запроса
                while (rs.next()) {
//записываем в массив students объекты класса Student с результатами запроса из БД
                    students.add(new Student(rs.getString("surname"),rs.getString("name"),
                            rs.getString("stipend"), rs.getString("kurs"), rs.getString("city"),
                            rs.getString("birthday"),rs.getString("univ_name")));
                }
            } catch (SQLException ex) { //обрабатываем ошибки при работе с БД
                Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) { //обрабатываем исключение для загрузки H2
            Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
        } finally { //по окончании работы корректно закрываем соединение с БД
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentsController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
//создаем список типа ObservableList, в который записываем все содержимое массива students
        ObservableList<Student> data= FXCollections.observableArrayList(students);
        studentsTable.setItems(data);//загружаем все из data в таблицу на окне
    }
}