package com.hassansaleem.ukbiobank.model;

import java.time.LocalDateTime;

// This builder class creates a new temperature reading entity
public class TemperatureReadingBuilder {
    private Long id;
    private String deviceName;
    private String location;
    private Double temperature;
    private LocalDateTime readingTime;

    public TemperatureReadingBuilder() {
    }

    public TemperatureReadingBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public TemperatureReadingBuilder deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public TemperatureReadingBuilder location(String location) {
        this.location = location;
        return this;
    }

    public TemperatureReadingBuilder temperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public TemperatureReadingBuilder readingTime(LocalDateTime readingTime) {
        this.readingTime = readingTime;
        return this;
    }

    public TemperatureReadingEntity build() {
        return new TemperatureReadingEntity(id, deviceName, location, temperature, readingTime);
    }

    @Override
    public String toString() {
        return "id=" + id +
                ", deviceName='" + deviceName + '\'' +
                ", location='" + location + '\'' +
                ", temperature=" + temperature +
                ", readingTime=" + readingTime +
                '}';
    }
}