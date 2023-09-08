package com.hr.springdata.jpa.manytomanyUsing3rdTable.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "customers")
public class Item {

    @Id
    @SequenceGenerator(
            name = "item_id_seq",
            sequenceName = "item_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "item_id_seq"
    )
    private Long itemId;
    private String itemTitle;
    @ManyToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name="customer_items",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "customer_id")}
    )
    private List<Customer> customers;

}
