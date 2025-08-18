package com.booklify.factory;

import com.booklify.domain.Address;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressFactoryTest {

    @Test
    void createAddress_withValidData_shouldReturnAddress() {
        Address address = AddressFactory.createAddress(
                "123",
                "Main Street",
                "Rosebank",
                "Cape Town",
                "Western Cape",
                "South Africa",
                "8001"
        );

        assertNotNull(address);
        assertEquals("123", address.getUnitNumber());
        assertEquals("Main Street", address.getStreet());
        assertEquals("Rosebank", address.getSuburb());
        assertEquals("Cape Town", address.getCity());
        assertEquals("Western Cape", address.getProvince());
        assertEquals("South Africa", address.getCountry());
        assertEquals("8001", address.getPostalCode());
        System.out.println(address);
    }

}