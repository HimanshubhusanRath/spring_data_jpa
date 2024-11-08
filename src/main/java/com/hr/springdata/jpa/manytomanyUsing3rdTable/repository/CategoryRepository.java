package com.hr.springdata.jpa.manytomanyUsing3rdTable.repository;

import com.hr.springdata.jpa.manytomanyUsing3rdTable.entity.Category;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

//    @EntityGraph(attributePaths = "items")
    List<Category> findAll();


}
