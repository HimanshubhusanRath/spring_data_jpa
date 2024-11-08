package com.hr.springdata.jpa.manytomanyUsing3rdTable.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "items")
public class Category {

    @Id
    @SequenceGenerator(
            name = "category_id_seq",
            sequenceName = "category_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "category_id_seq"
    )
    private Long categoryId;
    private String categoryName;

    @ManyToMany(
            mappedBy = "categories",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Item> items;

}
