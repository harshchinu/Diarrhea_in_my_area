package com.example.diarrheainmyarea;

public class cases {


    private String date;
    private String disease;
    private String location;
    private String age;
    private String gender;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public cases(String date, String disease, String location, String age, String gender) {
        this.date = date;
        this.disease = disease;
        this.location = location;
        this.age = age;
        this.gender = gender;
    }
}
