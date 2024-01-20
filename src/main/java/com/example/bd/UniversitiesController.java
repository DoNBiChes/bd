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

public class UniversitiesController implements Initializable {
    //объявляем все элементы с интерфейса Students.fxml и связываем их с классом Student
    @FXML
    TableView<University> universitiesTable;
    @FXML
    TableColumn<University,String> univerCol;
    @FXML TableColumn<University,String> ratingCol;
    @FXML TableColumn<University,String> cityCol;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//создаем ArrayList, в котором будут храниться все объекты класса Student, загруженные из БД
        ArrayList<University> universities=new ArrayList<University>();
        Connection conn=null; //для соединения с БД
        Statement stmt=null; //для формирования выражений SQL
        Statement stmt1=null; //для формирования выражений SQL
        ResultSet rs=null; //для результатов выполнения команд SQL
//связываем колонки таблицы с полями класса Student
        univerCol.setCellValueFactory(new PropertyValueFactory<University, String>("univer"));
        ratingCol.setCellValueFactory(new PropertyValueFactory<University, String>("rating"));
        cityCol.setCellValueFactory(new PropertyValueFactory<University, String>("city"));
//связываем предпочитаемую ширину колонок с шириной окна, задавая пропорции изменения колонок
        univerCol.prefWidthProperty().bind(universitiesTable.widthProperty().divide(6));
        ratingCol.prefWidthProperty().bind(universitiesTable.widthProperty().divide(9));
        cityCol.prefWidthProperty().bind(universitiesTable.widthProperty().divide(6));
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
                rs=stmt.executeQuery("select university.univ_name, university.rating," +
                        " university.city from university");
// цикл по всем записям из SQL-запроса
                while (rs.next()) {
//записываем в массив students объекты класса Student с результатами запроса из БД
                    universities.add(new University(rs.getString("univ_name"),
                            rs.getString("rating"), rs.getString("city")));
                }
            } catch (SQLException ex) { //обрабатываем ошибки при работе с БД
                Logger.getLogger(UniversitiesController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) { //обрабатываем исключение для загрузки H2
            Logger.getLogger(UniversitiesController.class.getName()).log(Level.SEVERE, null, ex);
        } finally { //по окончании работы корректно закрываем соединение с БД
            if (conn!=null){
                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(UniversitiesController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
//создаем список типа ObservableList, в который записываем все содержимое массива students
        ObservableList<University> data= FXCollections.observableArrayList(universities);
        universitiesTable.setItems(data);//загружаем все из data в таблицу на окне
    }
}
