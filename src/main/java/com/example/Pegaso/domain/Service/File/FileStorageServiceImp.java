package com.example.Pegaso.domain.Service.File;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.Pegaso.Config.FileStorageConfig;
import com.example.Pegaso.exceptions.FileStorageException;

@Service
public class FileStorageServiceImp implements FileStorageService {

    private final Path fileStorageLocation;

    public FileStorageServiceImp(FileStorageConfig fileStorageConfig) {
        Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath().normalize();

        this.fileStorageLocation = path;
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            throw new FileStorageException(
                    "Could not create the directory where the uploaded files will be stored!", e);
        }

    }

    @Override
    public String storeFile(MultipartFile file) {

        String filename = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Verificações
            if (filename.contains("..") || filename.contains(".exe")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + filename);
            }
            // Cria um arquivo vazio para receber o arquivo
            Path targetLocation = this.fileStorageLocation.resolve(filename);

            // Gravar o arquivo recebido no arquivo local
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return filename;
        } catch (Exception e) {

            throw new FileStorageException("Could not store file " + filename + ". Please try again!", e);
        }

    }
}