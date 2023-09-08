package com.hr.springdata.jpa.onetoonebidirectional.repository;

import com.hr.springdata.jpa.onetoonebidirectional.entity.Employee;
import com.hr.springdata.jpa.onetoonebidirectional.entity.Laptop;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

//    @EntityGraph(attributePaths = "laptop")
    List<Employee> findAll();


}
