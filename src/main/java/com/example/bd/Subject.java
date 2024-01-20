package com.example.bd;

public class Subject {
    private String name;
    private String hours;
    private String semester;

    public Subject(String name, String hours, String semester) {
        this.name = name;
        this.hours = hours;
        this.semester = semester;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "Subject [name=" + name + ", hours=" + hours + ", semester=" + semester + "]";
    }
}

