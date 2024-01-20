package com.example.bd;

public class Lecturer {
    private String surname;
    private String name;
    private String city;
    private String univer;

    public Lecturer(String surname, String name, String city, String univer) {
        this.surname = surname;
        this.name = name;
        this.city = city;
        this.univer = univer;
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

    @Override
    public String toString() {
        return "Lecturer [surname=" + surname + ", name=" + name +
                ", city=" + city + ", univer=" + univer + "]";
    }
}

