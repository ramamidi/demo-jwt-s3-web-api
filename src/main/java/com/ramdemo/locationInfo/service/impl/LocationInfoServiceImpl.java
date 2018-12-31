package com.ramdemo.locationInfo.service.impl;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ramdemo.locationInfo.model.LocationInfo;
import com.ramdemo.locationInfo.repository.LocationInfoRepository;
import com.ramdemo.locationInfo.service.LocationInfoService;
import com.ramdemo.storage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;

/**
 * Created by Ram on 11/11/18.
 */
@Service
public class LocationInfoServiceImpl implements LocationInfoService {

    @Autowired
    LocationInfoRepository locationInfoRepository;

    @Autowired
    private StorageService s3Service;


    @Override
    public String findByLocationInfoId(String locationInfoId) {
        LocationInfo locationInfo = locationInfoRepository.findByServiceRequestId(locationInfoId);
        Gson gson = new GsonBuilder().create();
        JsonParser jsonParser = new JsonParser();
        JsonObject jsonObject = (JsonObject)jsonParser.parse(gson.toJson(locationInfo));
        return gson.toJson(jsonObject);

    }

    @Override
    public List<LocationInfo> findAll() {
        List<LocationInfo> srList = Lists.newArrayList(locationInfoRepository.findAll());
        return srList;
    }

    @Override
    public void save(LocationInfo locationInfo) {
        String locationInfoId = generateLocationInfoId();
        locationInfo.setLocationInfoId(locationInfoId);
        locationInfoRepository.save(locationInfo);
        s3Service.createDirectory(locationInfoId);
    }

    private String generateLocationInfoId(){
        SecureRandom random = new SecureRandom();
        int num = random.nextInt(100000);
        StringBuilder sb = new StringBuilder(String.format("%05d", num));
        return sb.insert(0, "LOC").toString();
    }


}
