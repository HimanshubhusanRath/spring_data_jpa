package com.hr.springdata.jpa;

import com.hr.springdata.jpa.entity.Guardian;
import com.hr.springdata.jpa.entity.Student;
import com.hr.springdata.jpa.repository.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class StudentRepositoryTests {

	@Autowired
	private StudentRepository studentRepository;

	@Test
	void saveStudent() {
		final Student student = Student.builder()
				.emailId("hr_7@gmail.com")
				.firstName("HR")
				.lastName("Rath")
				.build();
		studentRepository.save(student);
	}

	@Test
	void saveStudentWithGuardian() {
		final Student student = Student.builder()
				.emailId("hr_8@gmail.com")
				.firstName("HR")
				.lastName("Rath")
				.guardian(new Guardian("Mother","mother@gmail.com","9987459454"))
				.build();
		studentRepository.save(student);
	}

	@Test
	void getAllStudents()
	{
		studentRepository.findAll().forEach(System.out::println);
	}

	@Test
	void findByFirstName()
	{
		final List<Student> students = studentRepository.findByFirstName("HR");
		System.out.println("Students with given first name : " + students.size());
	}

	@Test
	void findByFirstNameContaining()
	{
		final List<Student> students = studentRepository.findByFirstNameContaining("H");
		System.out.println("Result : " + students.size());
	}

	@Test
	void findByLastNameNotNull()
	{
		final List<Student> students = studentRepository.findByLastNameNotNull();
		System.out.println("Result : " + students.size());
	}

	@Test
	void findByGuardianName()
	{
		final List<Student> students = studentRepository.findByGuardianName("Mother");
		System.out.println("Result : " + students.size());
	}

	@Test
	void findByEmail()
	{
		final List<Student> students = studentRepository.getStudentsByEmail("hr5@gmail.com");
		System.out.println("Result : " + students.size());
	}

	@Test
	void findByEmailNative()
	{
		final List<Student> students = studentRepository.getStudentsByEmailNative("hr5@gmail.com");
		System.out.println("Result : " + students.size());
	}

	@Test
	void findByEmailNamedParam()
	{
		final List<Student> students = studentRepository.getStudentsByEmailNamedParam("hr5@gmail.com");
		System.out.println("Result : " + students.size());
	}

	@Test
	void findByEmailNamedParamNative()
	{
		final List<Student> students = studentRepository.getStudentsByEmailNamedParamNative("hr7@gmail.com");
		Assertions.assertTrue(students.size()==0);
	}

	@Test
	void updateFirstNameByEmail()
	{
		int result = studentRepository.updateFirstNameByEmail("Rajesh","hr5@gmail.com");
		System.out.println("Result : " + result);
	}

	@Test
	void updateFirstNameByEmailUsingNamedParams()
	{
		int result = studentRepository.updateFirstNameByEmail("Rajesh","hr5@gmail.com");
		System.out.println("Result : " + result);
	}
}

