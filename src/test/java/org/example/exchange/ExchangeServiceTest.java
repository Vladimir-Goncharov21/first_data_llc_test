package org.example.exchange;

import org.example.entities.Client;
import org.example.entities.OperationType;
import org.example.entities.Order;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeServiceTest {
    List<Client> clientList = new ArrayList<>(List.of(
            new Client("C1", 1000, new HashMap<>(Map.of("A", 130, "B", 240, "C", 760, "D", 320))),
            new Client("C2", 4350, new HashMap<>(Map.of("A", 370, "B", 120, "C", 950, "D", 560))),
            new Client("C3", 2760, new HashMap<>(Map.of("A", 0, "B", 0, "C", 0, "D", 0)))
    ));

    List<Order> orderList = new ArrayList<>(List.of(
            new Order("C1", OperationType.SELL, "C", 15, 3),
            new Order("C2", OperationType.BUY, "C", 13, 1),
            new Order("C2", OperationType.SELL, "C", 13, 1)
    ));


    @Test
    void getClientsFromFile() throws IOException {
        List<Client> clientListFromFile = ExchangeService.getClientsFromFile("src/test/resources/clientsTest.txt");
        assertEquals(clientList, clientListFromFile);
    }

    @Test
    void getOrdersFromFile() throws IOException {
        List<Order> orderListFromFile = ExchangeService.getOrdersFromFile("src/test/resources/ordersTest.txt");
        assertEquals(orderList, orderListFromFile);
    }

    @Test
    void printClientsStatus() throws IOException {
        String uri = "src/test/resources/result.txt";
        Files.deleteIfExists(Path.of(uri));
        ExchangeService.printClientsStatus(clientList, uri);
        List<Client> clientsFromResult = ExchangeService.getClientsFromFile(uri);
        assertEquals(clientList, clientsFromResult);
    }
}