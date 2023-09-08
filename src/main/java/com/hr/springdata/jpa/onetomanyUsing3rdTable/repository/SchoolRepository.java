package com.hr.springdata.jpa.onetomanyUsing3rdTable.repository;

import com.hr.springdata.jpa.onetomanyUsing3rdTable.entity.School;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SchoolRepository extends JpaRepository<School, Long> {

//    @EntityGraph(attributePaths = "teachers")
    List<School> findAll();


}
