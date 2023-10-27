package com.CMPE220.iot_health_monitoring.modal;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "wearable_sensor_reading")
public class WearableSensorReading implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String readingId;
    @Column
    private String deviceId;
    @Column
    private Double spo2;
    @Column
    private Date timeStamp;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column
    private Double deviceBattery;



    public WearableSensorReading() {
    }

    @Override
    public String toString() {
        return "WearableSensorReading{" +
                "readingId='" + readingId + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", spo2=" + spo2 +
                ", timeStamp=" + timeStamp +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", deviceBattery=" + deviceBattery +
                '}';
    }

    public String getReadingId() {
        return readingId;
    }

    public void setReadingId(String readingId) {
        this.readingId = readingId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Double getspo2() {
        return spo2;
    }

    public void setspo2(Double spo2) {
        this.spo2 = spo2;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getDeviceBattery() {
        return deviceBattery;
    }

    public void setDeviceBattery(Double deviceBattery) {
        this.deviceBattery = deviceBattery;
    }


}
