package com.example.bd;

public class Exam_mark { //surname, name, stipend, kurs, city, birthday, univer
    private String surname;
    private String name;
    private String subj_name;
    private String mark;
    private String exam_date;
    //этот конструктор нужен отдельно

    public Exam_mark(String surname, String name, String subj_name,
                   String mark, String exam_date) {
        this.surname = surname;
        this.name = name;
        this.subj_name = subj_name;
        this.mark = mark;
        this.exam_date = exam_date;
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
    public String getSubj_name() {
        return subj_name;
    }
    public void setSubj_name(String subj_name) {
        this.subj_name = subj_name;
    }
    public String getMark() {
        return mark;
    }
    public void setMark(String mark) {
        this.mark = mark;
    }
    public String getExam_date() {
        return exam_date;
    }
    public void setExam_date(String exam_date) {
        this.exam_date = exam_date;
    }
    @Override
    public String toString() {
        return "Student [surname=" + surname + ", name=" + name + ", subj_name=" + subj_name + ", mark="+ mark + ", exam_date=" + exam_date + "]";
    }
}