package com.hr.springdata.jpa.onetoonebidirectional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "laptop")
public class Employee {

    @Id
    @SequenceGenerator(
            name = "emp_id_seq",
            sequenceName = "emp_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "emp_id_seq"
    )
    private Long empId;
    private String empName;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="fk_laptop_id"
    )
    private Laptop laptop;

}
