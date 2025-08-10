package com.booklify.domain;

import com.booklify.domain.enums.BookCondition;
import com.booklify.domain.enums.BookStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookID;

    @Column(length = 13)
    private String isbn;

    @Column(nullable = false)
    private String title;

    private String author;
    private String publisher;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_condition", nullable = false)
    private BookCondition condition;

    @Column(nullable = false)
    private Double price;

    @Column(length = 100)
    private String description;

    @Column(nullable = false, updatable = false)
    private LocalDateTime uploadedDate = LocalDateTime.now();

    @Lob
    @Column(name = "image_data", columnDefinition = "LONGBLOB")
    private byte[] image;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "seller_id", nullable = false)
//    private RegularUser seller;

    protected Book(){

    }

    private Book(Builder builder){
        this.bookID = builder.bookID;
        this.isbn = builder.isbn;
        this.title = builder.title;
        this.author = builder.author;
        this.publisher = builder.publisher;
        this.condition = builder.condition;
        this.price = builder.price;
        this.description = builder.description;
        this.uploadedDate = builder.uploadedDate;
//        this.seller = builder.seller;
        this.image = builder.image;


    }

    public byte[] getImage() {
        return image;
    }

    public Long getBookID() {
        return bookID;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }


    public BookCondition getCondition() {
        return condition;
    }

    public Double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getUploadedDate() {
        return uploadedDate;
    }


//    public RegularUser getSeller() {
//        return seller;
//    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(bookID, book.bookID) && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(publisher, book.publisher) && condition == book.condition && Objects.equals(price, book.price) && Objects.equals(description, book.description) && Objects.equals(uploadedDate, book.uploadedDate) && Objects.deepEquals(image, book.image );
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookID, isbn, title, author, publisher, condition, price, description, uploadedDate, Arrays.hashCode(image));
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookID=" + bookID +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", condition=" + condition +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", uploadedDate=" + uploadedDate +
                ", image=" + Arrays.toString(image) +
                '}';
    }

    public static class Builder {
        private Long bookID;
        private String isbn;
        private String title;
        private String author;
        private String publisher;
        private BookCondition condition;
        private Double price;
        private String description;
        private LocalDateTime uploadedDate;
//        private RegularUser seller;
                private byte[] image;


        public Builder setImage(byte[] image) {
            this.image = image;
            return this;
        }

        public Builder setBookID(Long bookID) {
            this.bookID = bookID;
            return this;
        }

        public Builder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setAuthor(String author) {
            this.author = author;
            return this;
        }

        public Builder setPublisher(String publisher) {
            this.publisher = publisher;
            return this;
        }


        public Builder setCondition(BookCondition condition) {
            this.condition = condition;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setUploadedDate(LocalDateTime uploadedDate) {
            this.uploadedDate = uploadedDate;
            return this;
        }

//
//        public Builder setSeller(RegularUser seller) {
//            this.seller = seller;
//            return this;
//        }


        public Builder copy(Book book){
            this.bookID = book.bookID;
            this.isbn = book.isbn;
            this.title = book.title;
            this.author = book.author;
            this.publisher = book.publisher;
            this.condition = book.condition;
            this.price = book.price;
            this.description = book.description;
            this.uploadedDate = book.uploadedDate;
//            this.seller = book.seller;
            this.image = book.image;
            return this;
        }
        public Book build(){
            return new Book(this);
        }
    }
}
