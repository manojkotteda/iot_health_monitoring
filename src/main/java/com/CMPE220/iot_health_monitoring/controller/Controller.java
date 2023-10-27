package com.CMPE220.iot_health_monitoring.controller;

import com.CMPE220.iot_health_monitoring.service.CommonService;
import com.CMPE220.iot_health_monitoring.modal.ResponseTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
public class Controller {

    @Autowired
    private CommonService commonService;

    @RequestMapping(path = "/initData",method = RequestMethod.GET)
    public ResponseTemplate createDbData(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(), commonService.initialDbData());
    }

    @RequestMapping(path = "/initUser",method = RequestMethod.GET)
    public ResponseTemplate createUser(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(), commonService.initialUser());
    }

    @RequestMapping(path = "/getSensorData",method = RequestMethod.GET)
    public ResponseTemplate getAllReadings(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(), commonService.getAllReadings());
    }
    @RequestMapping(path = "/getSensorDataByTime",method = RequestMethod.GET)
    public ResponseTemplate getAllReadingsByTime(){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(), commonService.getReadingsByTime());
    }

    @RequestMapping(path = "/setThreshold",method = RequestMethod.GET)
    public ResponseTemplate getAllReadingsByTime(@RequestParam(name = "threshold") Double aDouble){
        return new ResponseTemplate(HttpStatus.OK.value(),HttpStatus.OK.toString(), commonService.setUserThreshold(aDouble));
    }


}
