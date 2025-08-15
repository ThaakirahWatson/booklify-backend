package com.booklify.controller;

import com.booklify.domain.Book;
import com.booklify.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/book")
@CrossOrigin(origins = {"http://localhost:3000", "http://127.0.0.1:3000", "http://localhost:5500"})
public class BookController {

    @Autowired
    private BookService service;

    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Book> create(@RequestPart("bookRequest") Book book,
                                       @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            book.setImage(imageFile.getBytes());
        }
        Book created = service.save(book);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<Book> read(@PathVariable Long id) {
        Book book = service.findById(id);
        return ResponseEntity.ok(book);
    }

    @PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Book> update(@RequestPart("bookRequest") Book book,
                                       @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            book.setImage(imageFile.getBytes());
        }
        Book updated = service.update(book);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<Book>> getAll() {
        List<Book> books = service.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<byte[]> getBookImage(@PathVariable Long id) {
        Book book = service.findById(id);
        if (book != null && book.getImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return new ResponseEntity<>(book.getImage(), headers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }


}
