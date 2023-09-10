package com.hr.springdata.jpa.onetooneunidirectional.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserManual {

    @Id
    @SequenceGenerator(
            name = "user_manual_id_seq",
            sequenceName = "user_manual_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_manual_id_seq"
    )
    private Long userManualId;
    private String url;

}
