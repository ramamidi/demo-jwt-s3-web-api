package com.ramdemo.api.storage;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ramdemo.storage.service.StorageService;
import org.springframework.web.multipart.MultipartFile;

/**
 * End-point for storing/retrieving media from S3.
 */
@RestController
@RequestMapping("api/storage")
public class StorageEndPoint {
	
	@Autowired
    private StorageService s3Service;

    @CrossOrigin
    @RequestMapping(value = "/fileNames/{locationInfoId}", method = RequestMethod.GET)
    public ResponseEntity<Set<String>> getImageNames(@PathVariable String locationInfoId){
        Set<String> thermal = s3Service.retrieveFileNames(locationInfoId);
        return new ResponseEntity<>(thermal, HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "/file/{locationInfoId}/{fileName}/", method = RequestMethod.GET)
    public ResponseEntity<byte[]> getThermalImage(@PathVariable String locationInfoId,
                                                        @PathVariable String fileName) throws  IOException{
        CacheControl cacheControl = CacheControl.maxAge(30, TimeUnit.MINUTES);
        byte[] image = s3Service.retrieveFile(locationInfoId, fileName);
        return ResponseEntity.ok()
                .cacheControl(cacheControl)
                .body(image);
    }

    @CrossOrigin
    @RequestMapping(value = "/file/{locationInfoId}/upload/", method = RequestMethod.POST)
    public String uploadImage(@RequestParam("file") MultipartFile file, @PathVariable String locationInfoId) throws  IOException{
        return s3Service.uploadFile(file, locationInfoId);
    }
}
