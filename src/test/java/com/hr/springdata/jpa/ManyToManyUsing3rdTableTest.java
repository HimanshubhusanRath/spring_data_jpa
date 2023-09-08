package com.hr.springdata.jpa;

import com.hr.springdata.jpa.manytomanyUsing3rdTable.entity.Customer;
import com.hr.springdata.jpa.manytomanyUsing3rdTable.entity.Item;
import com.hr.springdata.jpa.manytomanyUsing3rdTable.repository.CustomerRepository;
import com.hr.springdata.jpa.manytomanyUsing3rdTable.repository.ItemRepository;
import com.hr.springdata.jpa.onetomanyUsing3rdTable.entity.School;
import com.hr.springdata.jpa.onetomanyUsing3rdTable.entity.Teacher;
import com.hr.springdata.jpa.onetomanyUsing3rdTable.repository.SchoolRepository;
import com.hr.springdata.jpa.onetomanyUsing3rdTable.repository.TeacherRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ManyToManyUsing3rdTableTest {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void addCustomerWithItems()
    {
        final Customer customer = Customer.builder()
                .customerName("D d")
                .build();
        final List<Customer> customers = Arrays.asList(customer);

        final Item item = Item.builder().itemTitle("D T-Shirt").build();
        item.setCustomers(customers);

        final List<Item> items = Arrays.asList(item);

        customer.setItems(items);
        customerRepository.save(customer);
    }

    @Test
    void addItemWithCustomers()
    {
        final Customer customer = Customer.builder()
                .customerName("E e Mishra")
                .build();
        final List<Customer> customers = Arrays.asList(customer);

        final Item item = Item.builder().itemTitle("E T-shirt").build();
        item.setCustomers(customers);

        final List<Item> items = Arrays.asList(item);

        customer.setItems(items);
        itemRepository.save(item);
    }

    @Test
    void getAllCustomers()
    {
        final List<Customer> customers = customerRepository.findAll();
        System.out.println("Results : "+customers);
    }

    @Test
    void getAllItems()
    {
        final List<Item> items = itemRepository.findAll();
        System.out.println("Results : "+items);
    }
}
