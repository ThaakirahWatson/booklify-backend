package com.booklify.service.impl;

import com.booklify.domain.Address;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AddressServiceTest {

    @Autowired
    private AddressService addressService;
    private Address address1;

    @BeforeEach
    void setUp() {
        address1 = new Address.Builder()
                .setUnitNumber("246")
                .setStreet("Buitenkant")
                .setCity("Cape Town")
                .setPostalCode("8001")
                .setProvince("Western Cape")
                .build();
    }


    @Test
    @Order(1)
    void save() {
        Address saved= addressService.save(address1);
        assertNotNull(saved);
        System.out.println("Saved: " + saved);
    }

    @Test
    @Order(2)
    void findById() {
        addressService.save(address1);
        Address found = addressService.findById("8001");
        assertNotNull(found);
        assertEquals("Buitenkant", found.getStreet());
        System.out.println("Found: " + found);
    }

    @Test
    @Order(3)
    void update() {
        Address saved = addressService.save(address1);
        Address updated = new Address.Builder().copy(saved)
                .setStreet("Buitenkant")
                .build();

        Address result = addressService.update(updated);
        assertNotNull(result);
        assertEquals("Updated Street", result.getStreet());
        System.out.println("Updated: " + result);
    }

    @Test
    @Order(4)
    void deleteById() {
        Address saved = addressService.save(address1);
        addressService.deleteById("2000");

        Address deleted = addressService.findById("2000");
        assertNull(deleted);
        System.out.println("Deleted address with postal code: " + saved.getPostalCode());
    }

    @Test
    @Order(5)
    void getAll() {
        addressService.save(address1);
        List<Address> all = addressService.getAll();
        assertFalse(all.isEmpty());
        System.out.println("All addresses: " + all);
    }

    @Test
    @Order(6)
    void deleteAll() {
        addressService.save(address1);
        addressService.deleteAll();
        List<Address> all = addressService.getAll();
        assertTrue(all.isEmpty());
        System.out.println("All addresses deleted");
    }
}