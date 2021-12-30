package com.coffee.covidtrace.Ui.notification;

public class notifications {

    private String notifi_name, notifi_detail;

    public notifications(String notifi_name, String notifi_detail) {
        this.notifi_name = notifi_name;
        this.notifi_detail = notifi_detail;
    }


    public String getnotifi_name() {
        return notifi_name;
    }

    public void setnotifi_name(String notifi_name) {
        this.notifi_name = notifi_name;
    }

    public String getnotifi_detail() {
        return notifi_detail;
    }

    public void setnotifi_detail(String notifi_detail) {
        this.notifi_detail = notifi_detail;
    }

    @Override
    public String toString() {
        return "notifi{" +
                "notifi='" + notifi_name + '\'' +
                ", notifi_detail='" + notifi_detail + '\'' +
                '}';
    }


}