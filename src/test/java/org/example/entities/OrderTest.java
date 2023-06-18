package org.example.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {
    Order order;

    @BeforeEach
    void setUp() {
        order = new Order("C1", OperationType.SELL, "C", 15, 3);
    }

    @Test
    void testArrayConstructor() {
        String[] array = {"C1", "s", "C", "15", "3"};
        Order arrayOrder = new Order(array);
        assertEquals(order, arrayOrder);
    }

    @Test
    void sum() {
        assertEquals(45, order.sum());
    }
}