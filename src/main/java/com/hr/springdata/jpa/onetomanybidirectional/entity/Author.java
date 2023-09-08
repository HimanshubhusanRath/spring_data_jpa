package com.hr.springdata.jpa.onetomanybidirectional.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "books")
public class Author {

    @Id
    @SequenceGenerator(
            name = "author_id_seq",
            sequenceName = "author_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "author_id_seq"
    )
    private Long authorId;
    private String authorName;

    @OneToMany(
            mappedBy = "author",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Book> books;

}
