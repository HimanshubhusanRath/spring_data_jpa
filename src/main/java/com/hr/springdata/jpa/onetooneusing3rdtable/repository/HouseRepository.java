package com.hr.springdata.jpa.onetooneusing3rdtable.repository;

import com.hr.springdata.jpa.onetooneusing3rdtable.entity.House;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    @EntityGraph(attributePaths = "parkingSpace")
    List<House> findAll();
}
