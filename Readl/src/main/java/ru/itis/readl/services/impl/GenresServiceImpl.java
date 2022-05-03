package ru.itis.readl.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.readl.models.Genre;
import ru.itis.readl.repositories.GenresRepository;
import ru.itis.readl.services.GenresService;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenresServiceImpl implements GenresService {

    private final GenresRepository genresRepository;

    @Override
    public List<Genre> findAll() {
        return genresRepository.findAll();
    }
}
