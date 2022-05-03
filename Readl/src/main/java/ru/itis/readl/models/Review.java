package ru.itis.readl.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(nullable = false)
    private String content;

    @Column(name = "is_deleted", columnDefinition = "bool default false")
    private Boolean isDeleted;
}
