package com.CMPE220.iot_health_monitoring.modal;

import org.hibernate.annotations.GenericGenerator;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "_User")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String user_UUID;
    private String user_name;
    private String user_email;
    private Double consumptionThreshold;
    private Double billGenerated;
    private String wearableSensor;

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "user_UUID='" + user_UUID + '\'' +
                ", user_name='" + user_name + '\'' +
                ", user_email='" + user_email + '\'' +
                ", consumptionThreshold=" + consumptionThreshold +
                ", billGenerated=" + billGenerated +
                '}';
    }

    public String getUser_UUID() {
        return user_UUID;
    }

    public void setUser_UUID(String user_UUID) {
        this.user_UUID = user_UUID;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public Double getConsumptionThreshold() {
        return consumptionThreshold;
    }

    public void setConsumptionThreshold(Double consumptionThreshold) {
        this.consumptionThreshold = consumptionThreshold;
    }

    public Double getBillGenerated() {
        return billGenerated;
    }

    public void setBillGenerated(Double billGenerated) {
        this.billGenerated = billGenerated;
    }

    public String getWearableSensor() {
        return wearableSensor;
    }

    public void setWearableSensor(String wearableSensorReading) {
        this.wearableSensor = wearableSensorReading;
    }
}
