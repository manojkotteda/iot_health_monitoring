package com.CMPE220.iot_health_monitoring.repository;

import com.CMPE220.iot_health_monitoring.modal.WearableSensorReading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface WearableSensorReadingRepo extends JpaRepository<WearableSensorReading, String> {

    String query1 = "select * from WEARABLE_SENSOR_READING where DEVICE_ID = 'DEVICE_1' order by TIME_STAMP DESC limit 1;";

    String query2 ="select * from WEARABLE_SENSOR_READING where TIME_STAMP >= now() - interval '10' minute;";

    @Query(value = query1, nativeQuery = true)
    List<WearableSensorReading> getLateatReadingOfDev1();

    @Query(value = query2, nativeQuery = true)
    List<WearableSensorReading> getReadingByTime();

}
