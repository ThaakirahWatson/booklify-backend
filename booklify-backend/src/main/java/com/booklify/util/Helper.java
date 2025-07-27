package com.booklify.util;

import java.util.regex.Pattern;

public class Helper {

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static boolean isValidDateTime(java.time.LocalDateTime createdAt) {
        return createdAt != null && !createdAt.isAfter(java.time.LocalDateTime.now());
    }

    public static boolean isValidSellerRating(double rating) {
        return rating >= 0.0 && rating <= 5.0;
    }

    public static boolean isValidEmail(String email) {
        if (isNullOrEmpty(email)) return false;
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPassword(String password) {
        if (isNullOrEmpty(password)) return false;
        String passwordRegex = "^(?=.*[A-Za-z])(?=.*\\d).{6,}$";
        return Pattern.matches(passwordRegex, password);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        if (isNullOrEmpty(phoneNumber)) return false;
        String phoneRegex = "^\\+?[0-9]{10,15}$"; // Example regex for international phone numbers
        return Pattern.matches(phoneRegex, phoneNumber);
    }

    public static boolean isValidISBN(String isbn) {
        if (isNullOrEmpty(isbn)) return false;
        String isbnRegex = "^(97(8|9))?\\d{9}(\\d|X)$"; // Regex for ISBN-10 and ISBN-13
        return Pattern.matches(isbnRegex, isbn);
    }
}
