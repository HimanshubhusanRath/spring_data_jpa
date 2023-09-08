package com.hr.springdata.jpa.onetomanybidirectional.repository;

import com.hr.springdata.jpa.onetomanybidirectional.entity.Author;
import com.hr.springdata.jpa.onetomanybidirectional.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

//    @EntityGraph(attributePaths = "author")
    List<Book> findAll();


}
