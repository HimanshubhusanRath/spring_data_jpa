package com.hr.springdata.jpa.onetoonebidirectional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "employee")
public class Laptop {

    @Id
    @SequenceGenerator(
            name = "laptop_id_seq",
            sequenceName = "laptop_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "laptop_id_seq"
    )
    private Long laptopId;
    private String brand;

    @OneToOne(
            mappedBy = "laptop",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private Employee employee;

}
