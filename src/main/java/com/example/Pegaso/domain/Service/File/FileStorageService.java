package com.example.Pegaso.domain.Service.File;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {

    String storeFile(MultipartFile file) throws Exception;

}