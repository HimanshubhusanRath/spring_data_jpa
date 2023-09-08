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
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_id_seq",
            sequenceName = "customer_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_seq"
    )
    private Long customerId;
    private String customerName;

    @ManyToMany(
            mappedBy = "customers",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Item> items;

}
