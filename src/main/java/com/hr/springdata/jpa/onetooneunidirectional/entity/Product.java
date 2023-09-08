package com.hr.springdata.jpa.onetooneunidirectional.entity;

import com.hr.springdata.jpa.entity.CourseMaterial;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "userManual")
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_id_seq",
            sequenceName = "product_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_id_seq"
    )
    private Long productId;
    private String title;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name="fk_user_manual_id"
    )
    private UserManual userManual;

}
