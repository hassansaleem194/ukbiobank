package com.hassansaleem.ukbiobank.controller;

import com.hassansaleem.ukbiobank.dto.AverageTemperatureRequestDto;
import com.hassansaleem.ukbiobank.dto.TemperatureReadingDto;
import com.hassansaleem.ukbiobank.service.TemperatureReadingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/temperature")
public class TemperatureController {

    private static final Logger logger = LoggerFactory.getLogger(TemperatureController.class);
    // Controller is dependent on the temperatureReadingService which holds the business logic for the endpoints
    private final TemperatureReadingService temperatureReadingService;

    public TemperatureController(TemperatureReadingService temperatureReadingService) {
        this.temperatureReadingService = temperatureReadingService;
    }

    // Gets all readings in the db and returns them
    // I did not add a response entity for this as this end point was a quick way to check all readings
    @GetMapping("/readings")
    public List<TemperatureReadingDto> getAllReadings() {
        List<TemperatureReadingDto> readings = temperatureReadingService.getAllReadings();
        return readings;
    }

    // Accepts a JSON object containing temperature readings etc. and persists them to the db but will ignore incomplete records
    @PostMapping("/process")
    public ResponseEntity<String> processTemperatureReadings(@RequestBody List<TemperatureReadingDto> readings) {
        logger.info("Received {} temperature readings for processing.", readings.size());
        temperatureReadingService.saveAllReadings(readings);
        return ResponseEntity.ok("Temperature readings processed successfully.");
    }

    // Calculated the average temperature of the readings you pass in based on device name, date, and hour
    // Returns a different response entity based on the outcome of the try/catch
    @PostMapping("/average")
    public ResponseEntity<?> getAverageTemperature(@RequestBody AverageTemperatureRequestDto request) {
        try {

            LocalDateTime dateTime = getDateTimeAtMidnight(request);
            Double averageTemperature = temperatureReadingService.getAverageTemperature(request.getDeviceName(), dateTime, request.getHour());

            // returns log info in the console to confirm outcome of querying the endpoint
            logger.info("Returning average temperature for device {} at {}: {}°C", request.getDeviceName(), dateTime, averageTemperature);

            return ResponseEntity.ok(averageTemperature);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }
    }

    // This private method takes the parsed datetime from request, takes the date string from this, then converts it to LocalDateTime type
    // with the time set to midnight to ensure all temperatures for that day are processed
    private static LocalDateTime getDateTimeAtMidnight(AverageTemperatureRequestDto request) {
        LocalDate parsedDate = LocalDate.parse(request.getDate());
        LocalDateTime dateTime = parsedDate.atStartOfDay();
        return dateTime;
    }
}
