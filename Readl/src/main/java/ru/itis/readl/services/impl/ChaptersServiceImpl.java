package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.readl.dto.forms.AddChapterForm;
import ru.itis.readl.dto.ChapterDto;
import ru.itis.readl.exceptions.ChapterNotFoundException;
import ru.itis.readl.exceptions.FileNotFoundException;
import ru.itis.readl.models.Chapter;
import ru.itis.readl.models.FileInfo;
import ru.itis.readl.repositories.ChaptersRepository;
import ru.itis.readl.services.BooksService;
import ru.itis.readl.services.ChaptersService;
import ru.itis.readl.services.FileInfoService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

import static ru.itis.readl.dto.ChapterDto.*;

@RequiredArgsConstructor
@Service
public class ChaptersServiceImpl implements ChaptersService {

    @Value("${storage.path}")
    private String STORAGE_PATH;

    private final ChaptersRepository chaptersRepository;
    private final BooksService booksService;

    private final FileInfoService filesService;

    @Override
    public ChapterDto getChapterById(Long id) {
        Chapter chapter = chaptersRepository
                .findById(id)
                .orElseThrow(ChapterNotFoundException::new);

        ChapterDto chapterDto = from(chapter);
        chapterDto.setContent(getChapterContent(chapter));

        return chapterDto;
    }

    @Transactional
    @Override
    public void save(Long bookId, AddChapterForm chapterForm) {
        FileInfo fileInfo = filesService
                .upload(chapterForm.getFile());

        chaptersRepository.save(Chapter.builder()
                .name(chapterForm.getName())
                .book(booksService.getById(bookId))
                .file(fileInfo)
                .build());
    }

    private String getChapterContent(Chapter chapter){

        Path path = Paths.get(STORAGE_PATH, chapter.getFile().getStorageFileName());

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(
                new FileInputStream(
                    path.toString()), StandardCharsets.UTF_8))){

            StringBuilder sb = new StringBuilder();
            int current;

            while ((current = br.read()) != -1){
                char a = (char)current;
                sb.append(a);
            }

            return sb.toString();
        }catch (IOException e){
            throw new FileNotFoundException();
        }
    }
}
