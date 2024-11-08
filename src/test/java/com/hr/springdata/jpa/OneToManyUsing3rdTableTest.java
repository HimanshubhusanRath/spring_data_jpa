package com.hr.springdata.jpa;

import com.hr.springdata.jpa.onetomanyUsing3rdTable.entity.School;
import com.hr.springdata.jpa.onetomanyUsing3rdTable.entity.Teacher;
import com.hr.springdata.jpa.onetomanyUsing3rdTable.repository.SchoolRepository;
import com.hr.springdata.jpa.onetomanyUsing3rdTable.repository.TeacherRepository;
import com.hr.springdata.jpa.onetomanybidirectional.entity.Author;
import com.hr.springdata.jpa.onetomanybidirectional.entity.Book;
import com.hr.springdata.jpa.onetomanybidirectional.repository.AuthorRepository;
import com.hr.springdata.jpa.onetomanybidirectional.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OneToManyUsing3rdTableTest {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Test
    void addSchoolWithTeachers()
    {
        final School school = School.builder()
                .schoolName("Puri Zila School")
                .build();

        final List<Teacher> teachers = new ArrayList<>();
        teachers.add(Teacher.builder().teacherName("Rajesh Sir").school(school).build());
        school.setTeachers(teachers);
        schoolRepository.save(school);
    }

    @Test
    void addTeacherWithSchool()
    {
        final School school = School.builder()
                .schoolName("VSV Sunabeda")
                .build();

        final Teacher teacher = Teacher.builder().teacherName("Amrit Sir").school(school).build();

        teacherRepository.save(teacher);
    }

    @Test
    @Transactional
    void getAllSchools()
    {
        final List<School> schools = schoolRepository.findAll();
        System.out.println("Results : "+schools);
        schools.forEach(s -> s.getTeachers().forEach(t -> System.out.println("Teacher Name : "+t.getTeacherName())));
    }

    @Test
    @Transactional
    void getAllTeachers()
    {
        final List<Teacher> teachers = teacherRepository.findAll();
        System.out.println("Results : "+teachers);
        teachers.forEach(s -> System.out.println("Teacher Name : "+s.getSchool()));
    }

//
//    @Test
//    void getAllBooks()
//    {
//        final List<Book> books = bookRepository.findAll();
//        System.out.println("Results : "+books);
//    }
}
