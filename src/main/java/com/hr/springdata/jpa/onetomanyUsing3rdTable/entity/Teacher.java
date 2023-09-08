package com.hr.springdata.jpa.onetomanyUsing3rdTable.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "school")
public class Teacher {

    @Id
    @SequenceGenerator(
            name = "teacher_id_seq",
            sequenceName = "teacher_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "teacher_id_seq"
    )
    private Long teacherId;
    private String teacherName;
    @ManyToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name="school_teachers",
            joinColumns = {@JoinColumn(name = "teacher_id")},
            inverseJoinColumns = {@JoinColumn(name = "school_id")}
    )
    private School school;

}
