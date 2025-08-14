package com.booklify.service;

import com.booklify.domain.Order;
import com.booklify.domain.RegularUser;
import com.booklify.repository.RegularUserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RegularUserRepository regularUserRepository;

    private Order order;
    private RegularUser regularUser;

    @BeforeEach
    void setUp() {
        // Use a random email to avoid unique constraint violation
        String randomEmail = "testUser2_" + UUID.randomUUID() + "@gmail.com";
        regularUser = new RegularUser.RegularUserBuilder()
                .setFullName("Test User 2")
                .setEmail(randomEmail)
                .setPassword("password2")
                .setDateJoined(LocalDateTime.now())
                .build();
        regularUser = regularUserRepository.save(regularUser);
        assertNotNull(regularUser.getId(), "Regular User ID should not be null after saving");
        // Create and save an order for each test
        order = new Order.OrderBuilder()
                .setRegularUser(regularUser)
                .setOrderDate(LocalDateTime.now())
                .build();

    }

    @Test
    @org.junit.jupiter.api.Order(1)
    void save() {
        // Create a new order with the initialized regular user
        order = new Order.OrderBuilder()
                .setRegularUser(regularUser)
                .setOrderDate(LocalDateTime.now())
                .build();

        // Save the order using the service
        Order savedOrder = orderService.save(order);

        // Validate that the saved order is not null and has an ID
        assertNotNull(savedOrder, "Saved order should not be null");
        assertNotNull(savedOrder.getOrderId(), "Saved order ID should not be null");
    }

    @Test
    @org.junit.jupiter.api.Order(2)
    void findById() {
        // Ensure the order is saved before finding it
        assertNotNull(order, "Order should not be null before finding by ID");

        // Find the order by ID
        Order foundOrder = orderService.findById(order.getOrderId());

        // Validate that the found order matches the original order
        assertNotNull(foundOrder, "Found order should not be null");
        assertEquals(order.getOrderId(), foundOrder.getOrderId(), "Found order ID should match the original order ID");
    }

    @Test
    @org.junit.jupiter.api.Order(3)
    void update() {
        // Ensure the order is saved before updating it
        assertNotNull(order, "Order should not be null before updating");

        // Update the order's order date
     Order updatedOrder = new Order.OrderBuilder()
                .copy(order)
                .setOrderDate(LocalDateTime.now().plusDays(1)) // Update to a new date
                .build();

        // Update the order using the service
        Order savedUpdatedOrder = orderService.update(updatedOrder);

        // Validate that the updated order is not null and has the new order date
        assertNotNull(savedUpdatedOrder, "Updated order should not be null");
        assertEquals(updatedOrder.getOrderDate(), savedUpdatedOrder.getOrderDate(),
                     "Updated order date should match the new date");
        assertEquals(order.getOrderId(), savedUpdatedOrder.getOrderId(),
                     "Updated order ID should match the original order ID");
        assertEquals(order.getRegularUser().getId(), savedUpdatedOrder.getRegularUser().getId(),
                     "Updated order's regular user ID should match the original regular user ID");
    }

    @Test
    @org.junit.jupiter.api.Order(4)
    void getAll() {
        // Retrieve all orders
        var orders = orderService.getAll();

        // Validate that the list of orders is not null and contains at least one order
        assertNotNull(orders, "Orders list should not be null");
        assertFalse(orders.isEmpty(), "Orders list should not be empty");
        assertTrue(orders.stream().anyMatch(o -> o.getOrderId().equals(order.getOrderId())),
                   "Orders list should contain the saved order");
    }

    @Test
    @org.junit.jupiter.api.Order(5)
    void findByRegularUserId() {
        // Ensure the regular user is saved before finding orders by user ID
        assertNotNull(regularUser.getId(), "Regular User ID should not be null before finding orders");

        // Find orders by regular user ID
        var orders = orderService.findByRegularUserId(regularUser.getId());

        // Validate that the list of orders is not null and contains the saved order
        assertNotNull(orders, "Orders list should not be null");
        assertFalse(orders.isEmpty(), "Orders list should not be empty");
        assertTrue(orders.stream().anyMatch(o -> o.getOrderId().equals(order.getOrderId())),
                   "Orders list should contain the saved order for the regular user");
    }

    @Test
    @org.junit.jupiter.api.Order(6)
    void findByOrderDate() {
        // Ensure the order is saved before finding by order date
        assertNotNull(order, "Order should not be null before finding by order date");

        // Find orders by order date
        var orders = orderService.findByOrderDate(order.getOrderDate());

        // Validate that the list of orders is not null and contains the saved order
        assertNotNull(orders, "Orders list should not be null");
        assertFalse(orders.isEmpty(), "Orders list should not be empty");
        assertTrue(orders.stream().anyMatch(o -> o.getOrderId().equals(order.getOrderId())),
                   "Orders list should contain the saved order for the specified order date");
    }

    @Test
    @org.junit.jupiter.api.Order(7)
    void deleteById() {
        // Ensure the order is saved before deleting it
        assertNotNull(order, "Order should not be null before deletion");

        // Delete the order by ID
        boolean isDeleted = orderService.deleteById(order.getOrderId());

        // Validate that the order was deleted successfully
        assertTrue(isDeleted, "Order should be deleted successfully");

        // Attempt to find the deleted order by ID
        assertThrows(RuntimeException.class, () -> orderService.findById(order.getOrderId()),
                     "Finding a deleted order should throw an exception");
    }
}