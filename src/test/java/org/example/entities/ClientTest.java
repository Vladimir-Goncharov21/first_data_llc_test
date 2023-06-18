package org.example.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    Client client;

    @BeforeEach
    void setUp() {
        client = new Client("C1", 1000, new HashMap<>(Map.of("A", 130, "B", 240, "C", 760, "D", 320)));
    }

    @Test
    void testArrayConstructor() {
        String[] array = {"C1", "1000", "130", "240", "760", "320"};
        Client arrayClient = new Client(array);
        assertEquals(client, arrayClient);

    }

    @Test
    void updatePapersCountBuy() {
        client.updatePapersCount("A", 10);
        assertEquals(140, client.getPapersMap().get("A"));
    }

    @Test
    void updatePapersCountSell() {
        client.updatePapersCount("A", -10);
        assertEquals(120, client.getPapersMap().get("A"));
    }

    @Test
    void testToString() {
        assertEquals("C1\t1000\t130\t240\t760\t320", client.toString());
    }
}