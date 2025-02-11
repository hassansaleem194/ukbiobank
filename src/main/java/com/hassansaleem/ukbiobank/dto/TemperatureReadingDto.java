package com.hassansaleem.ukbiobank.dto;

import java.time.LocalDateTime;

// This is a DTO class which creates temperature reading objects
public class TemperatureReadingDto {

    private String deviceName;
    private String location;
    private Double temperature;
    private LocalDateTime time;

    public TemperatureReadingDto(String deviceName, String location, Double temperature, LocalDateTime time) {
        this.deviceName = deviceName;
        this.location = location;
        this.temperature = temperature;
        this.time = time;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
