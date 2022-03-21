package com.example.mlichal_malek_weather;

public class WeatherData {
    private String loc;
    private String time;
    private String temp;
    private String feel;
    private String hum;
    private String uv;
    private String desc;
    private String wind;
    private String vis;
    private String mornTemp;
    private String dayTemp;
    private String evenTemp;
    private String nightTemp;
    private String rise;
    private String set;
    private String highLow;
    private String rainPer;

    public WeatherData(String rainPre, String highLow, String loc, String time, String temp, String feel, String hum, String uv, String desc, String wind, String vis, String mornTemp, String dayTemp, String evenTemp, String nightTemp, String rise, String set) {
        this.loc = loc;
        this.time = time;
        this.temp = temp;
        this.feel = feel;
        this.hum = hum;
        this.uv = uv;
        this.desc = desc;
        this.wind = wind;
        this.vis = vis;
        this.mornTemp = mornTemp;
        this.dayTemp = dayTemp;
        this.evenTemp = evenTemp;
        this.nightTemp = nightTemp;
        this.rise = rise;
        this.set = set;
        this.rainPer = rainPer;
        this.highLow = highLow;
    }

    public String getRainPer(){
        return rainPer;
    }

    public void setRainPer(){
        this.rainPer = rainPer;
    }

    public String getHighLow(){
        return highLow;
    }

    public void setHighLow(){
        this.highLow = highLow;
    }

    public String getLoc() {
        return loc;
    }

    public void setLoc(String loc) {
        this.loc = loc;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getFeel() {
        return feel;
    }

    public void setFeel(String feel) {
        this.feel = feel;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getUv() {
        return uv;
    }

    public void setUv(String uv) {
        this.uv = uv;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getVis() {
        return vis;
    }

    public void setVis(String vis) {
        this.vis = vis;
    }

    public String getMornTemp() {
        return mornTemp;
    }

    public void setMornTemp(String mornTemp) {
        this.mornTemp = mornTemp;
    }

    public String getDayTemp() {
        return dayTemp;
    }

    public void setDayTemp(String dayTemp) {
        this.dayTemp = dayTemp;
    }

    public String getEvenTemp() {
        return evenTemp;
    }

    public void setEvenTemp(String evenTemp) {
        this.evenTemp = evenTemp;
    }

    public String getNightTemp() {
        return nightTemp;
    }

    public void setNightTemp(String nightTemp) {
        this.nightTemp = nightTemp;
    }

    public String getRise() {
        return rise;
    }

    public void setRise(String rise) {
        this.rise = rise;
    }

    public String getSet() {
        return set;
    }

    public void setSet(String set) {
        this.set = set;
    }
}
