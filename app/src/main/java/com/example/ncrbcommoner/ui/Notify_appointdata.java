package com.example.ncrbcommoner.ui;

public class Notify_appointdata {
    String user;
    String commoner_name;
    String commoner_no;
    String reason;
    String status;
    String date;
    String time;

    public Notify_appointdata(){}

    public Notify_appointdata(String user, String commoner_name, String commoner_no, String reason, String status, String date, String time) {
        this.user = user;
        this.commoner_name = commoner_name;
        this.commoner_no = commoner_no;
        this.reason = reason;
        this.status = status;
        this.date = date;
        this.time = time;
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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCommoner_name() {
        return commoner_name;
    }

    public void setCommoner_name(String commoner_name) {
        this.commoner_name = commoner_name;
    }

    public String getCommoner_no() {
        return commoner_no;
    }

    public void setCommoner_no(String commoner_no) {
        this.commoner_no = commoner_no;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
