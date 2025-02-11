package com.hassansaleem.ukbiobank.service;

import com.hassansaleem.ukbiobank.controller.TemperatureController;
import com.hassansaleem.ukbiobank.dto.TemperatureReadingDto;
import com.hassansaleem.ukbiobank.model.TemperatureReadingEntity;
import com.hassansaleem.ukbiobank.repository.TemperatureReadingRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemperatureReadingService {

    private static final Logger logger = LoggerFactory.getLogger(TemperatureController.class);

    private final TemperatureReadingRepository repository;

    public TemperatureReadingService(TemperatureReadingRepository repository) {
        this.repository = repository;
    }

    // This method converts temperature reading entity to DTOs so they can be persisted in the database
    public TemperatureReadingDto convertToDto(TemperatureReadingEntity entity) {
        return new TemperatureReadingDto(
                entity.getDeviceName(),
                entity.getLocation(),
                entity.getTemperature(),
                entity.getReadingTime()
        );
    }

    // This method returns a list of all readings as a list of DTOs
    public List<TemperatureReadingDto> getAllReadings() {
        return repository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    // This method calculates average temperature based and rounds to 2 decimal places
    public Double getAverageTemperature(String deviceName, LocalDateTime date, int hour) {
        LocalDateTime start = date.withHour(hour).truncatedTo(ChronoUnit.HOURS);
        LocalDateTime end = start.plusHours(1);

        List<TemperatureReadingEntity> readings = repository.findReadingsByDeviceAndHour(deviceName, start, end);

        if (readings.isEmpty()) {
            throw new RuntimeException("No readings found for device: " + deviceName + " at " + start);
        }
        // Log data for debugging
        System.out.println("Found " + readings.size() + " readings for device " + deviceName + " at " + start);

        double averageReading = readings.stream()
                .mapToDouble(TemperatureReadingEntity::getTemperature)
                .average()
                .orElse(Double.NaN);

        return BigDecimal.valueOf(averageReading)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    // Convert DTO to a list of entity that can be saved in the database
    @Transactional
    public void saveAllReadings(List<TemperatureReadingDto> readingDTOs) {
        List<TemperatureReadingEntity> readings = readingDTOs.stream()
                .filter(dto -> dto.getTemperature() != null)  // Ignore missing temperature readings
                .map(dto -> new TemperatureReadingEntity(null, dto.getDeviceName(), dto.getLocation(), dto.getTemperature(), dto.getTime()))
                .collect(Collectors.toList());

        if (!readings.isEmpty()) {
            repository.saveAll(readings);
            logger.info("{} temperature readings saved successfully.", readings.size());
        }
    }
}
