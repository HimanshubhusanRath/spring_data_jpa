package com.hr.springdata.jpa;

import com.hr.springdata.jpa.manytomanyUsing3rdTable.entity.Category;
import com.hr.springdata.jpa.manytomanyUsing3rdTable.entity.Item;
import com.hr.springdata.jpa.manytomanyUsing3rdTable.repository.CategoryRepository;
import com.hr.springdata.jpa.manytomanyUsing3rdTable.repository.ItemRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class ManyToManyUsing3rdTableTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Test
    void addCustomerWithItems()
    {
        final Category category = Category.builder()
                .categoryName("men-dresses")
                .build();
        final List<Category> categories = Arrays.asList(category);

        final Item item = Item.builder().itemTitle("D T-Shirt").build();
        item.setCategories(categories);

        final List<Item> items = Arrays.asList(item);

        category.setItems(items);
        categoryRepository.save(category);
    }

    @Test
    void addItemWithCategories()
    {
        final Category category = Category.builder()
                .categoryName("boys-dresses")
                .build();
        final List<Category> categories = Arrays.asList(category);

        final Item item = Item.builder().itemTitle("E T-shirt").build();
        item.setCategories(categories);

        final List<Item> items = Arrays.asList(item);

        category.setItems(items);
        itemRepository.save(item);
    }

    @Test
    @Transactional
    void getAllCategories()
    {
        final List<Category> categories = categoryRepository.findAll();
        System.out.println("Results : "+ categories);
        categories.forEach(c -> c.getItems().forEach(i->System.out.println("Item Title : "+ i.getItemTitle())));
    }

    @Test
    @Transactional
    void getAllItems()
    {
        final List<Item> items = itemRepository.findAll();
        System.out.println("Results : "+items);
        items.forEach(i -> i.getCategories().forEach(c->System.out.println("Category Name : "+ c.getCategoryName())));
    }
}
