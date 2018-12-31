package com.ramdemo.api.serviceRequest;

import com.ramdemo.locationInfo.model.LocationInfo;
import com.ramdemo.locationInfo.service.LocationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Ram on 11/11/18.
 */

@RestController
@RequestMapping("api/locationInfo")
public class LocationInfoEndPoint {

    @Autowired
    LocationInfoService locationInfoService;


    @CrossOrigin
    @RequestMapping(value = "/{locationInfoId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> get(@PathVariable String locationInfoId){
        return new ResponseEntity<>(locationInfoService.findByLocationInfoId(locationInfoId) , HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/getAll", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<LocationInfo>>  getAll() {
        return new ResponseEntity<>(locationInfoService.findAll(), HttpStatus.OK);

    }

    @CrossOrigin
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<LocationInfo> save(@RequestBody LocationInfo locationInfo){
        locationInfoService.save(locationInfo);
        return new ResponseEntity<>(locationInfo, HttpStatus.OK);

    }

}
