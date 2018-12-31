package com.ramdemo.storage.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface StorageService {

	byte[] retrieveFile(String locationInfoId, String fileName) throws IOException;

	void createDirectory (String serviceRequestId);

	Set<String> retrieveFileNames(String locationInfoId);

	String uploadFile(MultipartFile file, String locationInfoId);

}
