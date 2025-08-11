package com.booklify.service;

import com.booklify.domain.Book;
import com.booklify.domain.RegularUser;
import com.booklify.domain.enums.BookCondition;
import com.booklify.factory.BookFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceTest {

    @Autowired
    private BookService bookService;

    private Book book1, book2;
    private ByteArrayOutputStream outputStream;
    private BufferedImage image;
    private byte[] imageBytes;
//    private RegularUser seller;

    @BeforeEach
    void setUp() {
        String url = "C:\\Users\\hp\\Downloads\\cput log.png";

        try {
            image = ImageIO.read(new File(url));
            outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "png", outputStream);
            imageBytes = outputStream.toByteArray();
        } catch (IOException ex) {
            System.out.println("Image read error: " + ex.getMessage());
            imageBytes = new byte[]{1, 2, 3}; // fallback dummy image
        }

//        seller = new RegularUser.RegularUserBuilder()
//                .setFullName("Test Seller")
//                .setEmail("test@example.com")
//                .setPassword("test123")
//                .build();

        book1 = BookFactory.createBook(
                "9780061122415",
                "The Alchemist",
                "Paulo Coelho",
                "HarperOne",
                BookCondition.EXCELLENT,
                180.0,
                "Fiction classic about destiny.",
//                seller,
                imageBytes
        );

        book2 = BookFactory.createBook(
                "9780451524935",
                "1984",
                "George Orwell",
                "Secker & Warburg",
                BookCondition.ACCEPTABLE,
                150.0,
                "Dystopian novel set in totalitarian regime.",
//                seller,
                imageBytes
        );

        System.out.println("Book 1: " + book1);
        System.out.println("Book 2: " + book2);
    }

    @Test
    @Order(1)
    void save() {
        Book saved1 = bookService.save(book1);
        Book saved2 = bookService.save(book2);

        assertNotNull(saved1);
        assertNotNull(saved2);
        System.out.println("Saved: " + saved1);
    }

    @Test
    @Order(2)
    void findById() {
        Book saved = bookService.save(book1);
        Book found = bookService.findById(saved.getBookID());

        assertNotNull(found);
        assertEquals(saved.getBookID(), found.getBookID());
        System.out.println("Found: " + found);
    }

    @Test
    @Order(3)
    void update() {
        Book saved = bookService.save(book1);
        Book updated = new Book.Builder().copy(saved)
                .setPrice(199.99)
                .build();
        Book result = bookService.update(updated);

        assertNotNull(result);
        assertEquals(199.99, result.getPrice());
        System.out.println("Updated: " + result);
    }

    @Test
    @Order(4)
    void deleteById() {
        Book saved = bookService.save(book2);
        bookService.deleteById(saved.getBookID());

        Book deleted = bookService.findById(saved.getBookID());
        assertNull(deleted);
        System.out.println("Deleted book with ID: " + saved.getBookID());
    }

    @Test
    @Order(5)
    void getAll() {
        bookService.save(book1);
        bookService.save(book2);

        assertFalse(bookService.getAll().isEmpty());
        System.out.println("All books: " + bookService.getAll());
    }

    @Test
    @Order(6)
    void deleteAll() {
        bookService.save(book1);
        bookService.save(book2);
        bookService.deleteAll();

        assertTrue(bookService.getAll().isEmpty());
        System.out.println("All books deleted");
    }
}
