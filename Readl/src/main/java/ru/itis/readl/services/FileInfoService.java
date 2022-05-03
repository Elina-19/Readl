package ru.itis.readl.services;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.readl.models.FileInfo;

import javax.servlet.http.HttpServletResponse;

public interface FileInfoService {

    FileInfo upload(MultipartFile file);

    void addFileToResponse(String fileName, HttpServletResponse response);

}
