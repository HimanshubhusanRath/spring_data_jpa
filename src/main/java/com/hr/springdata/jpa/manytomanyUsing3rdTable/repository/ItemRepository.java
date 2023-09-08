package com.hr.springdata.jpa.manytomanyUsing3rdTable.repository;

import com.hr.springdata.jpa.manytomanyUsing3rdTable.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

//    @EntityGraph(attributePaths = "author")
    List<Item> findAll();


}
