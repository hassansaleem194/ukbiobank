package com.hassansaleem.ukbiobank.service;

import com.hassansaleem.ukbiobank.dto.TemperatureReadingDto;
import com.hassansaleem.ukbiobank.model.TemperatureReadingEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class TemperatureReadingServiceTest {

    private TemperatureReadingService underTest;

    @BeforeEach
    void setUp() {
        underTest = new TemperatureReadingService(null);
    }

    @Test
    public void shouldConvertEntityToDtoSuccessfully() {
        // Create a mock entity
        TemperatureReadingEntity entity = new TemperatureReadingEntity();
        entity.setDeviceName("AB123");
        entity.setLocation("Block A, Room 1");
        entity.setTemperature(11.5);
        entity.setReadingTime(LocalDateTime.of(2025, 1, 9, 7, 0));

        // Convert entity to DTO
        TemperatureReadingDto dto = underTest.convertToDto(entity);

        // Assertions to verify conversion
        assertNotNull(dto);
        assertEquals("AB123", dto.getDeviceName());
        assertEquals("Block A, Room 1", dto.getLocation());
        assertEquals(11.5, dto.getTemperature());
        assertEquals(LocalDateTime.of(2025, 1, 9, 7, 0), dto.getTime());
    }
}