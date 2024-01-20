package com.example.bd;

public class Student { //surname, name, stipend, kurs, city, birthday, univer
    private String surname;
    private String name;
    private int stipend;
    private int kurs;
    private String city;
    private String birthday;
    private String univer;
    //этот конструктор нужен отдельно
    public Student(String surname, String name, String stipend, String kurs, String city,
                   String birthday, String univer) {
        this.surname = surname;
        this.name = name;
        this.stipend = Integer.parseInt(stipend);
        this.kurs = Integer.parseInt(kurs);
        this.city = city;
        this.birthday = birthday;
        this.univer = univer;
    }
    public Student(String surname, String name, int stipend, int kurs, String city,
                   String birthday, String univer) {
        this.surname = surname;
        this.name = name;
        this.stipend = stipend;
        this.kurs = kurs;
        this.city = city;
        this.birthday = birthday;
        this.univer = univer;
    }
    public int getKurs() {
        return kurs;
    }
    public void setKurs(int kurs) {
        this.kurs = kurs;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStipend() {
        return stipend;
    }
    public void setStipend(int stipend) {
        this.stipend = stipend;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getBirthday() {
        return birthday;
    }
    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
    public String getUniver() {
        return univer;
    }
    public void setUniver(String univer) {
        this.univer = univer;
    }
    @Override
    public String toString() {
        return "Student [surname=" + surname + ", name=" + name + ", stipend="+ stipend
                +", kurs=" +kurs +", city=" + city + ", birthday="+ birthday + ", univer=" + univer + "]";
    }
}