package ru.itis.readl.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Set;

@ToString(exclude = {"chapters", "reviews"})
@EqualsAndHashCode(exclude = {"chapters", "reviews"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;

    @OneToOne
    @JoinColumn(name = "file_info_id", referencedColumnName = "id")
    private FileInfo image;

    @CreationTimestamp
    @Column(name = "date_of_adding")
    private Timestamp dateOfAdding;

    @Column(columnDefinition = "text", length = 3000)
    private String description;

    @ManyToMany
    @JoinTable(name = "book_genre",
            joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn( name = "genre_id", referencedColumnName = "id"))
    private Set<Genre> genres;

    @OneToMany(mappedBy = "book")
    private Set<Chapter> chapters;

    @OneToMany(mappedBy = "book")
    private Set<Review> reviews;

}
