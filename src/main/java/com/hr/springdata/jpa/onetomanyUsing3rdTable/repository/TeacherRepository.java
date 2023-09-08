package com.hr.springdata.jpa.onetomanyUsing3rdTable.repository;

import com.hr.springdata.jpa.onetomanyUsing3rdTable.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {

//    @EntityGraph(attributePaths = "author")
    List<Teacher> findAll();


}
