package com.hr.springdata.jpa.manytomanyUsing3rdTable.repository;

import com.hr.springdata.jpa.manytomanyUsing3rdTable.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

//    @EntityGraph(attributePaths = "teachers")
    List<Customer> findAll();


}
