package ru.itis.readl.services;

import ru.itis.readl.dto.forms.AddChapterForm;
import ru.itis.readl.dto.ChapterDto;

public interface ChaptersService {

    ChapterDto getChapterById(Long id);

    void save(Long bookId, AddChapterForm chapterForm);

}
