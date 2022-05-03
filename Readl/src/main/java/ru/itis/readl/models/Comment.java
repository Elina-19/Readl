//package ru.itis.readl.models;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.persistence.*;
//import java.sql.Date;
//import java.sql.Timestamp;
//import java.util.List;
//
//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//@Entity
//public class Comment {
//
//    public enum Type{
//        BOOK, CHAPTER
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    private Timestamp date;
//
//    @Enumerated(EnumType.STRING)
//    private Type type;
//
//    @ManyToOne
//    @JoinColumn(name = "author_id", referencedColumnName = "id")
//    private Account author;
//
//    @Column(nullable = false)
//    private String content;

//    private Integer numberOfPage;

//    private List<Comment> innerComments;
//}
