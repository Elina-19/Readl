package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.readl.models.Genre;

public interface GenresRepository extends JpaRepository<Genre, Integer> {
}
