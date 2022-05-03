package ru.itis.readl.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.readl.models.FileInfo;

import java.util.Optional;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {

    Optional<FileInfo> findByStorageFileName(String storageFileName);

}
