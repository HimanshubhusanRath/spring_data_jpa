package com.hr.springdata.jpa.onetooneusing3rdtable.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "house")
public class ParkingSpace {

    @Id
    @SequenceGenerator(
            name = "parking_id_seq",
            sequenceName = "parking_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "parking_id_seq"
    )
    private Long parkingSpaceId;
    private String parkingType;

    @OneToOne(
            mappedBy = "parkingSpace",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private House house;

}
