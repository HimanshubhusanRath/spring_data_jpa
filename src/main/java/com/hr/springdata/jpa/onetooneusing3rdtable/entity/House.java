package com.hr.springdata.jpa.onetooneusing3rdtable.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "parkingSpace")
public class House {

    @Id
    @SequenceGenerator(
            name = "house_id_seq",
            sequenceName = "house_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "house_id_seq"
    )
    private Long houseId;
    private String houseName;
    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinTable(
            name = "houseParking",
            joinColumns = {@JoinColumn(name = "house_id") },
            inverseJoinColumns = {@JoinColumn(name = "parking_id") }
    )
    private ParkingSpace parkingSpace;

}
