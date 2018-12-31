package com.ramdemo.locationInfo.service;

import com.ramdemo.locationInfo.model.LocationInfo;

import java.util.List;

/**
 * Created by Ram on 11/11/18.
 */
public interface LocationInfoService {

    String findByLocationInfoId(String serviceRequestId);

    List<LocationInfo> findAll();

    void save(LocationInfo locationInfo);

}
