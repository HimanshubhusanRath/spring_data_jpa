package com.hr.springdata.jpa.onetomanybidirectional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "author")
public class Book {

    @Id
    @SequenceGenerator(
            name = "book_id_seq",
            sequenceName = "book_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_id_seq"
    )
    private Long bookId;
    private String title;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="fk_author_id"
    )
    private Author author;

}
