package ru.itis.readl.services;

import ru.itis.readl.models.Genre;

import java.util.List;
import java.util.Set;

public interface GenresService {

    List<Genre> findAll();

    Set<Genre> getGenresByRequest(List<String> genres);

    Genre getGenreById(Integer id);

}
