package com.example.ncrbcommoner;

public class Appoint_data {
    String user;
    String commoner_name;
    String commoner_no;
    String reason;
    String status;

    public Appoint_data(){}

    public Appoint_data(String police_user, String commoner_name, String commoner_no, String reason, String status) {
        this.user = police_user;
        this.commoner_name = commoner_name;
        this.commoner_no = commoner_no;
        this.reason = reason;
        this.status = status;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String police_user) {
        this.user = police_user;
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
