package com.hr.springdata.jpa;

import com.hr.springdata.jpa.onetomanybidirectional.entity.Author;
import com.hr.springdata.jpa.onetomanybidirectional.entity.Book;
import com.hr.springdata.jpa.onetomanybidirectional.repository.AuthorRepository;
import com.hr.springdata.jpa.onetomanybidirectional.repository.BookRepository;
import com.hr.springdata.jpa.onetoonebidirectional.entity.Employee;
import com.hr.springdata.jpa.onetoonebidirectional.entity.Laptop;
import com.hr.springdata.jpa.onetoonebidirectional.repository.EmployeeRepository;
import com.hr.springdata.jpa.onetoonebidirectional.repository.LaptopRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class OneToManyBiDirectionalTest {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    void addAuthorWithBooks()
    {
        final Author author = Author.builder()
                .authorName("Sher Shah")
                .build();

        final List<Book> books = new ArrayList<>();
        books.add(Book.builder().title("Ramayan").author(author).build());
        books.add(Book.builder().title("Bedtime Stories").author(author).build());

        author.setBooks(books);
        authorRepository.save(author);
    }

    @Test
    void addBookWithAuthor()
    {
        final Author author = Author.builder()
                .authorName("John Ryan")
                .build();

        final Book book = Book.builder()
                .title("The Sweet Love")
                .author(author).build();

        bookRepository.save(book);
    }

    @Test
    @Transactional
    void getAllAuthors()
    {
        final List<Author> authors = authorRepository.findAll();
        System.out.println("Results : "+authors);
        authors.forEach(a -> a.getBooks().forEach(b -> System.out.println("Book Name : "+b.getTitle())));
    }

    @Test
    @Transactional
    void getAllBooks()
    {
        final List<Book> books = bookRepository.findAll();
        System.out.println("Results : "+books);
        books.forEach(b -> System.out.println("Author Name : "+b.getAuthor().getAuthorName()));
    }
}
