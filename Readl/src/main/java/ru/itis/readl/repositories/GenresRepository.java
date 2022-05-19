package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.readl.models.Genre;

import java.util.Optional;

public interface GenresRepository extends JpaRepository<Genre, Integer> {

    Optional<Genre> findByName(String name);

}
