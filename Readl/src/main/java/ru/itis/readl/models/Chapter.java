package ru.itis.readl.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@ToString(exclude = {"chapters", "reviews", "comments"})
@EqualsAndHashCode(exclude = {"chapters", "reviews", "comments"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @OneToOne
    @JoinColumn(name = "file_info_id", referencedColumnName = "id")
    private FileInfo file;
//    private String content;
//    private String contentPath;
//    private Long size;
//    private String mimeType;
//
//    @OneToMany(mappedBy = "chapter")
//    private List<Comment> comments;
}
