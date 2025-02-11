package com.hassansaleem.ukbiobank.dto;

// This is a DTO class which allows the /average endpoint to be queried using an object rather than
// querying via the URL
public class AverageTemperatureRequestDto {

    private String deviceName;
    private String date;
    private int hour;

    public AverageTemperatureRequestDto() {}

    public AverageTemperatureRequestDto(String deviceName, String date, int hour) {
        this.deviceName = deviceName;
        this.date = date;
        this.hour = hour;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }
}
