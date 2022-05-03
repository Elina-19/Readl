package ru.itis.readl.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.readl.services.FileInfoService;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
@RequestMapping("/files")
public class FilesController {

    private final FileInfoService filesService;

    @GetMapping("/{file-name:.*}")
    public void getFile(@PathVariable("file-name") String fileName, HttpServletResponse response){
        filesService.addFileToResponse(fileName, response);
    }
}
