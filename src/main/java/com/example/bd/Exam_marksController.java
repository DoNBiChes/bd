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

public class Exam_marksController implements Initializable {
    //объявляем все элементы с интерфейса Students.fxml и связываем их с классом Student
    @FXML
    TableView<Exam_mark> exam_marksTable;
    @FXML TableColumn<Exam_mark,String> surnameCol;
    @FXML
    TableColumn<Exam_mark,String> nameCol;
    @FXML TableColumn<Exam_mark,String> subjectCol;
    @FXML TableColumn<Exam_mark,String> markCol;
    @FXML TableColumn<Exam_mark,String> exam_dateCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//создаем ArrayList, в котором будут храниться все объекты класса Student, загруженные из БД
        ArrayList<Exam_mark> exam_marks=new ArrayList<Exam_mark>();
        Connection conn=null; //для соединения с БД
        Statement stmt=null; //для формирования выражений SQL
        Statement stmt1=null; //для формирования выражений SQL
        ResultSet rs=null; //для результатов выполнения команд SQL
//связываем колонки таблицы с полями класса Student
        surnameCol.setCellValueFactory(new PropertyValueFactory<Exam_mark, String>("surname"));
        nameCol.setCellValueFactory(new PropertyValueFactory<Exam_mark, String>("name"));
        subjectCol.setCellValueFactory(new PropertyValueFactory<Exam_mark, String>("subj_name"));
        markCol.setCellValueFactory(new PropertyValueFactory<Exam_mark, String>("mark"));
        exam_dateCol.setCellValueFactory(new PropertyValueFactory<Exam_mark, String>("exam_date"));
//связываем предпочитаемую ширину колонок с шириной окна, задавая пропорции изменения колонок
        surnameCol.prefWidthProperty().bind(exam_marksTable.widthProperty().divide(6));
        nameCol.prefWidthProperty().bind(exam_marksTable.widthProperty().divide(6));
        subjectCol.prefWidthProperty().bind(exam_marksTable.widthProperty().divide(8));
        markCol.prefWidthProperty().bind(exam_marksTable.widthProperty().divide(7));
        exam_dateCol.prefWidthProperty().bind(exam_marksTable.widthProperty().divide(6));
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
                rs=stmt.executeQuery("select student.surname, student.name, subject.subj_name," +
                        "exam_marks.mark, exam_marks.exam_date from student, subject, exam_marks" +
                        " where exam_marks.student_id=student.student_id and exam_marks.subj_id=subject.subj_id");
// цикл по всем записям из SQL-запроса
                while (rs.next()) {
//записываем в массив students объекты класса Student с результатами запроса из БД
                    exam_marks.add(new Exam_mark(rs.getString("surname"),rs.getString("name"),
                            rs.getString("subj_name"),rs.getString("mark"),
                            rs.getString("exam_date")));
                }
            } catch (SQLException ex) { //обрабатываем ошибки при работе с БД
                Logger.getLogger(Exam_marksController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) { //обрабатываем исключение для загрузки H2
            Logger.getLogger(Exam_marksController.class.getName()).log(Level.SEVERE, null, ex);
        } finally { //по окончании работы корректно закрываем соединение с БД
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(Exam_marksController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
//создаем список типа ObservableList, в который записываем все содержимое массива Exam_marks
        ObservableList<Exam_mark> data= FXCollections.observableArrayList(exam_marks);
        exam_marksTable.setItems(data);//загружаем все из data в таблицу на окне
    }
}