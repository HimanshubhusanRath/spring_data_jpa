package com.hr.springdata.jpa.onetooneunidirectional.repository;

import com.hr.springdata.jpa.entity.Course;
import com.hr.springdata.jpa.entity.CourseMaterial;
import com.hr.springdata.jpa.onetooneunidirectional.entity.Product;
import com.hr.springdata.jpa.onetooneunidirectional.entity.UserManual;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

//    @EntityGraph(attributePaths = "userManual")
    List<Product> findAll();
}
