package ru.itis.readl.models;

import lombok.*;

import javax.persistence.*;

@ToString(exclude = {"chapters", "reviews"})
@EqualsAndHashCode(exclude = {"chapters", "reviews"})
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

}
