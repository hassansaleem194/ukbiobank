package com.hassansaleem.ukbiobank.repository;

import com.hassansaleem.ukbiobank.model.TemperatureReadingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

// This repository is what finds the readings by device and hour that allows the average temperature to be calculated
@Repository
public interface  TemperatureReadingRepository extends JpaRepository<TemperatureReadingEntity, Long> {

    @Query("SELECT t FROM TemperatureReadingEntity t WHERE t.deviceName = :deviceName AND t.readingTime BETWEEN :start AND :end")
    List<TemperatureReadingEntity> findReadingsByDeviceAndHour(@Param("deviceName")String deviceName,
                                                               @Param("start")LocalDateTime start,
                                                               @Param("end")LocalDateTime end);

}
