package ru.itis.readl.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@ToString(exclude = {"followers", "following", "authorBooks", "favouriteBooks"})
@EqualsAndHashCode(exclude = {"followers", "following", "authorBooks", "favouriteBooks"})
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

//    private String imagePath;
    @OneToOne
    @JoinColumn(name = "file_info_id", referencedColumnName = "id")
    private FileInfo image;

//    private String uuid;
    private String token;

    @ManyToMany
    @JoinTable(name = "followers_following",
            joinColumns = @JoinColumn(name = "follower_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "following_id", referencedColumnName = "id"))
    private Set<Account> followers;

    @ManyToMany(mappedBy = "followers")
    private Set<Account> following;

    @OneToMany(mappedBy = "author")
    private Set<Book> authorBooks;

    @ManyToMany
    @JoinTable(name = "favourite",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"))
    private Set<Book> favouriteBooks;
}
