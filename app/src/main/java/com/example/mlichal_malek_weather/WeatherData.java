package com.example.mlichal_malek_weather;

public class WeatherData {
    private String time;
    private String icon;
    private String temp;
    private String desc;

    public WeatherData(String time, String icon, String temp, String desc) {
        this.time = time;
        this.icon = icon;
        this.temp = temp;
        this.desc = desc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
