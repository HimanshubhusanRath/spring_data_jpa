package com.hr.springdata.jpa;

import com.hr.springdata.jpa.onetoonebidirectional.entity.Employee;
import com.hr.springdata.jpa.onetoonebidirectional.entity.Laptop;
import com.hr.springdata.jpa.onetoonebidirectional.repository.EmployeeRepository;
import com.hr.springdata.jpa.onetoonebidirectional.repository.LaptopRepository;
import com.hr.springdata.jpa.onetooneunidirectional.entity.Product;
import com.hr.springdata.jpa.onetooneunidirectional.entity.UserManual;
import com.hr.springdata.jpa.onetooneunidirectional.repository.ProductRepository;
import com.hr.springdata.jpa.onetooneunidirectional.repository.UserManualRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class OneToOneBiDirectionalTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Test
    void addEmployeeWithLaptop()
    {

        final Laptop laptop = Laptop.builder()
                .brand("Apple")
                .build();

        final Employee employee = Employee.builder()
                .empName("Empl - 1")
                .laptop(laptop)
                .build();

        employeeRepository.save(employee);
    }

    @Test
    void addLaptopWithEmployee()
    {

        final Laptop laptop = Laptop.builder()
                .brand("HP")
                .build();

        final Employee employee = Employee.builder()
                .empName("Empl - 2")
                .laptop(laptop)
                .build();

        laptop.setEmployee(employee);
        laptopRepository.save(laptop);
    }

    @Test
    @Transactional
    void getAllEmployees()
    {
        final List<Employee> employees = employeeRepository.findAll();
        System.out.println("Employees are fetched");
        employees.forEach(e -> System.out.println("Laptop brand : "+e.getLaptop().getBrand()));
    }

    @Test
    void getAllLaptops()
    {
        final List<Laptop> laptops = laptopRepository.findAll();
        System.out.println("Laptops are fetched");
        laptops.forEach(l -> System.out.println("Employee ID : "+l.getEmployee().getEmpId()));
    }
}
