package com.example.bd;

public class University {
    private String univer;
    private String rating;
    private String city;

    public University(String univer, String rating, String city) {
        this.univer = univer;
        this.rating = rating;
        this.city = city;
    }

    public String getUniver() {
        return univer;
    }

    public void setUniver(String univer) {
        this.univer = univer;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "University [univer=" + univer + ", rating=" + rating + ", city=" + city + "]";
    }
}

