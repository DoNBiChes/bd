package com.example.bd;

public class Subj_lect { //surname, name, stipend, kurs, city, birthday, univer
    private String surname;
    private String name;
    private String city;
    private String univer;
    private String subj_name;
    private String hours;
    private String semester;
    //этот конструктор нужен отдельно

    public Subj_lect(String surname, String name, String city, String univer, String subj_name,
                     String hours, String semester) {
        this.surname = surname;
        this.name = name;
        this.city = city;
        this.univer = univer;
        this.subj_name = subj_name;
        this.hours = hours;
        this.semester = semester;
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
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getUniver() {
        return univer;
    }
    public void setUniver(String univer) {
        this.univer = univer;
    }
    public String getSubj_name() {
        return subj_name;
    }
    public void setSubj_name(String subj_name) {
        this.subj_name = subj_name;
    }
    public String getHours() {
        return hours;
    }
    public void setHours(String hours) {
        this.hours = hours;
    }
    public String getSemester() {
        return semester;
    }
    public void setSemester(String semester) {
        this.semester = semester;
    }
    @Override
    public String toString() {
        return "Subj_lect [surname=" + surname + ", name=" + name + ", city=" + city + ", univer=" + univer +
                ", subj_name=" + subj_name + ", hours=" + hours + ", semester=" + semester + "]";
    }
}