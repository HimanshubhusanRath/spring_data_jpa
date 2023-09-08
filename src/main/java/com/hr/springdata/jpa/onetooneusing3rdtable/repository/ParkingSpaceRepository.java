package com.hr.springdata.jpa.onetooneusing3rdtable.repository;

import com.hr.springdata.jpa.onetooneusing3rdtable.entity.ParkingSpace;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParkingSpaceRepository extends JpaRepository<ParkingSpace, Long> {

//    @EntityGraph(attributePaths = "house")
    List<ParkingSpace> findAll();


}
