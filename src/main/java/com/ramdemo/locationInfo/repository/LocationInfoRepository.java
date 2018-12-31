package com.ramdemo.locationInfo.repository;

import com.ramdemo.locationInfo.model.LocationInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


/**
 * Created by Ram on 11/11/18.
 */
public interface LocationInfoRepository extends CrudRepository<LocationInfo, Long> {

    @Query("SELECT li FROM LocationInfo li where locationInfoId = :locationInfoId")
    LocationInfo findByServiceRequestId(@Param("locationInfoId") String locationInfoId);
}
