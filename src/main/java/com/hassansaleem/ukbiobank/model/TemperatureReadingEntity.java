package com.hassansaleem.ukbiobank.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

import java.time.LocalDateTime;

@Entity
@Table(name = "TEMPERATURE_READINGS")
public class TemperatureReadingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String deviceName;

    @Column(nullable = false)
    private String location;

    @Column(nullable = true)
    private Double temperature;

    @Column(nullable = false)
    private LocalDateTime readingTime;

    public TemperatureReadingEntity() {

    }

    public TemperatureReadingEntity(Long id, String deviceName, String location, Double temperature, LocalDateTime readingTime) {
        this.id = id;
        this.deviceName = deviceName;
        this.location = location;
        this.temperature = temperature;
        this.readingTime = readingTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getReadingTime() {
        return readingTime;
    }

    public void setReadingTime(LocalDateTime readingTime) {
        this.readingTime = readingTime;
    }

}
