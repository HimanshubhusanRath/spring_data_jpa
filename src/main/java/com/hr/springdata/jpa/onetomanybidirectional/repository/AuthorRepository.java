package com.hr.springdata.jpa.onetomanybidirectional.repository;

import com.hr.springdata.jpa.onetomanybidirectional.entity.Author;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

//    @EntityGraph(attributePaths = "books")
    List<Author> findAll();


}
