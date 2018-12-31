package com.ramdemo.storage.service;

import java.io.*;
import java.util.*;

import com.amazonaws.services.s3.model.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.amazonaws.services.s3.AmazonS3;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AWSS3StorageService implements StorageService {

    @Autowired
    private AmazonS3 amazonS3;

    @Value("${aws.s3.bucket}")
    private String bucket;

    @Value("${server.host.name}")
    private String hostname;

    @Value("${aws.s3.url}")
    private String s3Url;

    private static final String DELIMITER = "/";


    @Override
    public byte[] retrieveFile(String locationInfoId, String fileName) throws IOException {
        return retrieveFile(String.join(DELIMITER, locationInfoId, fileName));
    }


    public byte[] retrieveFile(String key) throws IOException {
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);
        S3Object s3Object = amazonS3.getObject(getObjectRequest);
        S3ObjectInputStream objectInputStream = s3Object.getObjectContent();
        return IOUtils.toByteArray(objectInputStream);
    }

    public void createDirectory(String locationInfoId) {
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(0);
        InputStream emptyContent = new ByteArrayInputStream(new byte[0]);
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, locationInfoId + DELIMITER, emptyContent, metadata);
        amazonS3.putObject(putObjectRequest);
    }

    public Set<String> retrieveFileNames(String serviceRequestId) {

        ListObjectsRequest request = new ListObjectsRequest()
                .withBucketName(bucket)
                .withPrefix(serviceRequestId + DELIMITER)
                .withDelimiter(DELIMITER);
        ObjectListing objectListing = amazonS3.listObjects(request);
        List<String>  keys = new ArrayList();

        for(S3ObjectSummary summary: objectListing.getObjectSummaries()){
            keys.add(summary.getKey());
        }

        return getFileNames(keys, serviceRequestId);
    }

    @Override
    public String uploadFile(MultipartFile multipartFile, String locationInfoId) {
        String fileUrl = "";
        try {
            File file = convertMultiPartToFile(multipartFile);
            String fileName = generateFileName(multipartFile);
            uploadFileTos3bucket(fileName, file, locationInfoId);
            fileUrl = s3Url + DELIMITER + bucket + DELIMITER + locationInfoId + DELIMITER + fileName;
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fileUrl;
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(file.getOriginalFilename());
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename().replace(" ", "_");
    }

    private void uploadFileTos3bucket(String fileName, File file, String locationInfoId) {
        amazonS3.putObject(new PutObjectRequest(bucket, locationInfoId + DELIMITER + fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
    }


    private Set<String> getFileNames(List<String> keys, String serviceRequestId){
        String search = serviceRequestId + DELIMITER;
        String replace = "";
        Set<String> fileNames = new HashSet<>();
        for(String key : keys){
           String filename = key.replace(search, replace);
           if(!filename.isEmpty()){
               fileNames.add(filename);
           }
        }
        return fileNames;
    }

}
