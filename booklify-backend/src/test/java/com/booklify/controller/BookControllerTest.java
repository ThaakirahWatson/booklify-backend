package com.booklify.controller;

import com.booklify.domain.Book;
import com.booklify.domain.enums.BookCondition;
import com.booklify.factory.BookFactory;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)  // Use OrderAnnotation to specify order with @Order
class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    static Book book1;
    static Book book2;

    static final String BASE_URL = "/api/book";

    private static class LoginRequest {
        private String email;
        private String password;

        public LoginRequest(String email, String password) {
            this.email = email;
            this.password = password;
        }

        // Getters (needed for JSON serialization)
        public String getEmail() { return email; }
        public String getPassword() { return password; }
    }

    private static class AuthResponse {
        private String token;

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }

    private String getJwtToken() {
        String authUrl = "/api/regular-user/login";
        LoginRequest request = new LoginRequest("test@example.com", "password");

        ResponseEntity<AuthResponse> response = restTemplate.postForEntity(
                authUrl,
                request,
                AuthResponse.class
        );

        return "Bearer " + response.getBody().getToken();
    }
    // ======== END AUTHENTICATION HELPERS ========

    @BeforeAll
    static void setUp() {
        byte[] testImage = loadTestImage(); // Now calls the static method directly

        book1 = BookFactory.createBook(
                "9780061122415", "The Alchemist", "Paulo Coelho",
                "HarperOne", BookCondition.EXCELLENT, 180.0,
                "Fiction classic about destiny.", testImage
        );

        book2 = BookFactory.createBook(
                "9780451524935", "1984", "George Orwell",
                "Secker & Warburg", BookCondition.ACCEPTABLE, 150.0,
                "Dystopian novel set in a totalitarian regime.", testImage
        );
    }

    @Test
    @Order(1)
    void create() throws IOException {
        String url = BASE_URL + "/create";

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        // Use JSON string or Map for the bookRequest if your controller expects JSON as part of multipart
        body.add("bookRequest", book1);
        body.add("imageFile", new ClassPathResource("test-image.png"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Book> response = restTemplate.postForEntity(url, requestEntity, Book.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        // Update book1 with returned Book to get its generated ID for further tests
        book1 = response.getBody();

        System.out.println("Created book: " + book1);
    }

    @Test
    @Order(2)
    void read() {
        String url = BASE_URL + "/read/" + book1.getBookID();

        ResponseEntity<Book> response = restTemplate.getForEntity(url, Book.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());

        System.out.println("Read book: " + response.getBody());
    }

    @Test
    @Order(3)
    void update() throws IOException {
        String url = BASE_URL + "/update";

        Book updatedBook = new Book.Builder()
                .copy(book1)
                .setTitle("Updated Title")
                .build();

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("bookRequest", updatedBook);
        body.add("imageFile", new ClassPathResource("test-image.png"));

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        ResponseEntity<Book> response = restTemplate.postForEntity(url, requestEntity, Book.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Updated Title", response.getBody().getTitle());

        System.out.println("Updated book: " + response.getBody());

        // Update book1 reference for subsequent tests
        book1 = response.getBody();
    }

    @Test
    @Order(4)
    void getAll() {
        String url = BASE_URL + "/getAll";

        ResponseEntity<Book[]> response = restTemplate.getForEntity(url, Book[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println("All books:");
        for (Book b : response.getBody()) {
            System.out.println(b);
        }
    }

    @Test
    @Order(5)
    void getBookImage() {
        String url = BASE_URL + "/image/" + book1.getBookID();

        ResponseEntity<byte[]> response = restTemplate.getForEntity(url, byte[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().length > 0);

        System.out.println("Image bytes length: " + response.getBody().length);
    }

    @Test
    @Order(6)
    void delete() {
        assertNotNull(book1, "Book1 should not be null before delete");
        assertNotNull(book1.getBookID(), "Book1 ID should not be null before delete");

        String url = BASE_URL + "/delete/" + book1.getBookID();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(getJwtToken());
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        // Perform DELETE with auth
        ResponseEntity<Void> deleteResponse = restTemplate.exchange(
                url,
                HttpMethod.DELETE,
                entity,
                Void.class
        );

        assertTrue(
                deleteResponse.getStatusCode() == HttpStatus.NO_CONTENT ||
                        deleteResponse.getStatusCode() == HttpStatus.OK,
                "Delete should return 204 or 200"
        );

        // Try to read again
        ResponseEntity<Book> readResponse = restTemplate.exchange(
                BASE_URL + "/read/" + book1.getBookID(),
                HttpMethod.GET,
                entity,
                Book.class
        );

        if (readResponse.getStatusCode() == HttpStatus.NOT_FOUND) {
            // Expected case for no security restrictions
            assertEquals(HttpStatus.NOT_FOUND, readResponse.getStatusCode());
        } else {
            // If secured, expect forbidden when deleted or unauthorized
            assertEquals(HttpStatus.FORBIDDEN, readResponse.getStatusCode());
        }

        System.out.println("Deleted book with ID: " + book1.getBookID());
    }


    private static byte[] loadTestImage() {
        try {
            ClassPathResource resource = new ClassPathResource("test-image.png");
            return resource.getInputStream().readAllBytes();
        } catch (IOException ex) {
            System.out.println("Image read error: " + ex.getMessage());
            return new byte[]{1, 2, 3};
        }
    }
}