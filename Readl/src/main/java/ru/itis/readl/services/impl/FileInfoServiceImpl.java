package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.readl.exceptions.FileNotFoundException;
import ru.itis.readl.models.FileInfo;
import ru.itis.readl.repositories.FileInfoRepository;
import ru.itis.readl.services.FileInfoService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileInfoServiceImpl implements FileInfoService {

    @Value("${storage.path}")
    private String STORAGE_PATH;

    private final FileInfoRepository fileInfoRepository;

    @Transactional
    @Override
    public FileInfo upload(MultipartFile file) {
        if (file == null){
            return null;
        }

        String extension = file.getOriginalFilename().
                substring(file.getOriginalFilename().lastIndexOf("."));

        String storageFileName = UUID.randomUUID() + extension;

        FileInfo fileInfo = FileInfo.builder()
                .mimeType(file.getContentType())
                .originalFileName(file.getOriginalFilename())
                .storageFileName(storageFileName)
                .size(file.getSize())
                .build();

        try{
            Files.copy(file.getInputStream(), Paths.get(STORAGE_PATH, fileInfo.getStorageFileName()));
        }catch (IOException e){
            throw new IllegalArgumentException("Can not handle file", e);
        }

        return fileInfoRepository.save(fileInfo);
    }

    @Override
    public void addFileToResponse(String fileName, HttpServletResponse response) {
        FileInfo fileInfo = fileInfoRepository.findByStorageFileName(fileName)
                .orElseThrow(FileNotFoundException::new);

        response.setContentLength(fileInfo.getSize().intValue());
        response.setContentType(fileInfo.getMimeType());
        response.setHeader("Content-Disposition", "filename=\"" + fileInfo.getOriginalFileName() + "\"");

        try{
            Files.copy(Paths.get(
                      STORAGE_PATH + fileInfo.getStorageFileName()), response.getOutputStream());
            response.flushBuffer();
        }catch (IOException e){
            throw new IllegalArgumentException(e);
        }
    }
}
