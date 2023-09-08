package com.hr.springdata.jpa.repository;

import com.hr.springdata.jpa.entity.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    /**
     * Find the students whose first name matches with the given first name
     *
     * @param firstName
     * @return
     */
    List<Student> findByFirstName(final String firstName);

    /**
     * Find the students whose first name contains the given pattern string
     *
     * @param pattern
     * @return
     */
    List<Student> findByFirstNameContaining(final String pattern);

    /**
     * Find the students whose last name is not null
     *
     * @param lastName
     * @return
     */
    List<Student> findByLastNameNotNull();

    /**
     * Find the students whose guardian's name matches the given name
     *
     * Note: here the method name is constructed like the below.
     *
     * 1. guardian is the attribute in Student class
     * 2. name is the attribute in Guardian class
     *
     * So, the name becomes guardian + name = guardianName
     *
     * @param name
     * @return
     */
    List<Student> findByGuardianName(final String name);

    /**
     * Find the students based on the given query.
     * JPQL (works on class name and attributes) query is used here.
     *
     * Note: Here we mention 'select s from Student s' not 'select *'
     *
     * @param email
     * @return
     */
    @Query("select s from Student s where s.emailId = ?1")
    List<Student> getStudentsByEmail(final String email);

    /**
     * Find the students based on the given query.
     * Native query (works on table name and column names) is used here.
     *
     * @param email
     * @return
     */
    @Query(value = "select * from student s where s.email = ?1", nativeQuery = true)
    List<Student> getStudentsByEmailNative(final String email);


    /**
     * Find the students using the given query with named parameters
     *
     * @param email
     * @return
     */
    @Query(value = "select s from Student s where s.emailId = :email")
    List<Student> getStudentsByEmailNamedParam(@Param("email") final String email);

    /**
     * Updates the first name of the student as given name for the given email
     *
     * Note: without @Modifying and @Transactional, the update is done but with errors.
     * So, these two annotation should be used when there is an update to the table.
     *
     * @param firstName
     * @param email
     * @return
     */
    @Modifying
    @Transactional
    @Query(value = "update student s set first_name = ?1 where s.email = ?2", nativeQuery = true)
    int updateFirstNameByEmail(final String firstName, final String email);
}
