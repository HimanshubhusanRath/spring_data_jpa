package com.hr.springdata.jpa.onetomanyUsing3rdTable.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "teachers")
public class School {

    @Id
    @SequenceGenerator(
            name = "school_id_seq",
            sequenceName = "school_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "school_id_seq"
    )
    private Long schoolId;
    private String schoolName;

    @OneToMany(
            mappedBy = "school",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Teacher> teachers;

}
