package com.hr.springdata.jpa.onetooneunidirectional.repository;

import com.hr.springdata.jpa.onetooneunidirectional.entity.Product;
import com.hr.springdata.jpa.onetooneunidirectional.entity.UserManual;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserManualRepository extends JpaRepository<UserManual, Long> {

}
