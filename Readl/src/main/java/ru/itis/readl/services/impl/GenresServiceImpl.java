package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.readl.exceptions.GenreNotFoundException;
import ru.itis.readl.models.Genre;
import ru.itis.readl.repositories.GenresRepository;
import ru.itis.readl.services.GenresService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Service
public class GenresServiceImpl implements GenresService {

    private final GenresRepository genresRepository;

    @Override
    public List<Genre> findAll() {
        return genresRepository.findAll();
    }

    @Override
    public Set<Genre> getGenresByRequest(List<String> stringGenres) {

        Set<Genre> genres = new HashSet<>();

        for (String genre: stringGenres){
            genres.add(genresRepository
                    .findByName(genre)
                    .orElseThrow(GenreNotFoundException::new));
        }

        return genres;
    }

    @Override
    public Genre getGenreById(Integer id) {
        return genresRepository
                .findById(id)
                .orElseThrow(GenreNotFoundException::new);
    }
}
