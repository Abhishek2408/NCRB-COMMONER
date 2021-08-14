package com.example.ncrbcommoner.ui;

public class Fir_getNotify {
    String name,email,phone,statement,suspect,area,date,time,status,evid;

    public  Fir_getNotify(){}

    public Fir_getNotify(String name, String email, String phone, String statement, String suspect, String area, String date, String time, String status, String evid) {
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.statement = statement;
        this.suspect = suspect;
        this.area = area;
        this.date = date;
        this.time = time;
        this.status = status;
        this.evid = evid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatement() {
        return statement;
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEvid() {
        return evid;
    }

    public void setEvid(String evid) {
        this.evid = evid;
    }
}
