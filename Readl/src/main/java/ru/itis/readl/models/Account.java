package ru.itis.readl.models;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@ToString(exclude = {"authorBooks", "favouriteBooks"})
@EqualsAndHashCode(exclude = {"authorBooks", "favouriteBooks"})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne
    @JoinColumn(name = "file_info_id", referencedColumnName = "id")
    private FileInfo image;

    private String token;

    @OneToMany(mappedBy = "author")
    private Set<Book> authorBooks;

    @ManyToMany
    @JoinTable(name = "favourite",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<Book> favouriteBooks;
}
