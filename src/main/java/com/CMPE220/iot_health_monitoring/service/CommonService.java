package com.CMPE220.iot_health_monitoring.service;


import com.CMPE220.iot_health_monitoring.modal.User;
import com.CMPE220.iot_health_monitoring.modal.WearableSensorReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.CMPE220.iot_health_monitoring.repository.UserRepo;
import com.CMPE220.iot_health_monitoring.repository.WearableSensorReadingRepo;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@EnableScheduling
@Service
public class CommonService {

    @Autowired
    private WearableSensorReadingRepo sensorReadingRepo;

    @Autowired
    private UserRepo userRepo;

    public List<WearableSensorReading> initialDbData(){

        WearableSensorReading reading = new WearableSensorReading();
        reading.setDeviceId("DEVICE_1");
        reading.setDeviceBattery(100.00);
        reading.setspo2(0.00);
        reading.setLatitude(17.338055);
        reading.setLongitude(78.471354);
        reading.setTimeStamp(Date.from(Instant.now()));

        List<WearableSensorReading> wearableSensorReadings = new ArrayList<WearableSensorReading>();
        wearableSensorReadings.add(reading);

        List<WearableSensorReading> result = sensorReadingRepo.saveAll(wearableSensorReadings);

        return result;
    }

    public List<User> initialUser(){

        User user = new User();

        user.setUser_name("manoj");
        user.setUser_email("manoj@gmail.com");
        user.setConsumptionThreshold(0.00);
        user.setBillGenerated(100.00);
        user.setWearableSensor("DEVICE_1");

        List<User> wearableSensorReading = new ArrayList<User>();
        wearableSensorReading.add(user);

        List<User> result = userRepo.saveAll(wearableSensorReading);

        return result;
    }

    public List<WearableSensorReading> getAllReadings(){
        return sensorReadingRepo.findAll();
    }

    public List<WearableSensorReading> getReadingsByTime(){
        return sensorReadingRepo.getReadingByTime();
    }

    public User setUserThreshold(Double treshold) {
        User user = userRepo.findAll().get(0);
        user.setConsumptionThreshold(treshold);
        return userRepo.save(user);
    }

    @Scheduled(fixedRate = 60000)
    public void sensorData(){
        Random r = new Random();
        double randomValue;
        double number = Math.random() ;

        if(number > 0.39){
            randomValue = (double) Math.round(((Math.random() * 5) + 95)*100)/100;
        }else if(number > 0.59){
            //20% of the time returns amount between -75 & 90 cents
            randomValue = (double) Math.round(((Math.random() * -5) + 95)*100)/100;
        }else{
            randomValue = (double) Math.round(((Math.random() * -3) + 90)*100)/100;
        }

        List<WearableSensorReading> meterReadings = new ArrayList<>();
        List<WearableSensorReading> newMeterReadings = new ArrayList<>();

        /*meterReadings.add(meterReadingRepo.getLateatReadingOfDev1().get(0));
        for(WearableSensorReading oldReading:meterReadings){
            WearableSensorReading newReading = new WearableSensorReading();
            newReading.setDeviceId(oldReading.getDeviceId());
            newReading.setLongitude(oldReading.getLongitude());
            newReading.setLatitude(oldReading.getLatitude());
            newReading.setDeviceBattery(oldReading.getDeviceBattery()-0.01);
            newReading.setspo2(randomValue);

            newReading.setTimeStamp(Date.from(Instant.now()));
            newMeterReadings.add(newReading);
        }*/

        if(sensorReadingRepo.getLateatReadingOfDev1().isEmpty()){
            initialDbData();
            return;
        }

        WearableSensorReading oldReading = sensorReadingRepo.getLateatReadingOfDev1().get(0);
        WearableSensorReading newReading = new WearableSensorReading();
        newReading.setDeviceId(oldReading.getDeviceId());
        newReading.setLongitude(oldReading.getLongitude());
        newReading.setLatitude(oldReading.getLatitude());
        newReading.setDeviceBattery(oldReading.getDeviceBattery()-0.01);
        newReading.setspo2(randomValue);

        newReading.setTimeStamp(Date.from(Instant.now()));
        //meterReadingRepo.saveAll(newMeterReadings);
        sensorReadingRepo.save(newReading);
        System.out.println(newReading);

    }

}
