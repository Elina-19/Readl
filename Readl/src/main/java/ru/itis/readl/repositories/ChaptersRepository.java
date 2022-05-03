package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.readl.models.Chapter;

public interface ChaptersRepository extends JpaRepository<Chapter, Long> {
}
