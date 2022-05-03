package ru.itis.readl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class FileInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mime_type")
    private String mimeType;

    private Long size;

    @Column(name = "original_file_name")
    private String originalFileName;

    @Column(name = "storage_file_name")
    private String storageFileName;
}
