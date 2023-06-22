package org.example.exchange;

import org.example.entities.Client;
import org.example.entities.OperationType;
import org.example.entities.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ExchangeTest {

    List<Client> clientList;

    @BeforeEach
    void initClientList() {
        clientList = new ArrayList<>(List.of(
                new Client("C2", 13, new HashMap<>(Map.of("A", 370, "B", 0, "C", 950, "D", 560))),
                new Client("C3", 0, new HashMap<>(Map.of("A", 0, "B", 13, "C", 0, "D", 0)))
        ));
    }

    // обе заявки совпадают и выполняются
    @Test
    void fullMatchOrders() {
        List<Order> orderList = new ArrayList<>(List.of(
                new Order("C2", OperationType.BUY, "B", 13, 1),
                new Order("C3", OperationType.SELL, "B", 13, 1)
        ));

        List<Client> completedClientList = Exchange.completeListOfOrders(clientList, orderList);
        List<Client> expectedClientList = List.of(
                new Client("C2", 0, new HashMap<>(Map.of("A", 370, "B", 1, "C", 950, "D", 560))),
                new Client("C3", 13, new HashMap<>(Map.of("A", 0, "B", 12, "C", 0, "D", 0))));
        assertEquals(expectedClientList, completedClientList);
    }

    // заявки выполняются частично, ничего не должно поменяться у клиентов
    @Test
    void partialMatchOrder() {
        List<Order> orderList = new ArrayList<>(List.of(
                new Order("C2", OperationType.BUY, "B", 13, 1),
                new Order("C3", OperationType.SELL, "B", 11, 1)
        ));
        List<Client> completedClientList = Exchange.completeListOfOrders(clientList, orderList);
        assertEquals(clientList, completedClientList);
    }

    // не покупаются у самого себя
    @Test
    void selfBuy() {
        List<Order> orderList = new ArrayList<>(List.of(
                new Order("C3", OperationType.SELL, "A", 13, 1),
                new Order("C3", OperationType.BUY, "A", 13, 1)
        ));
        List<Client> completeList = Exchange.completeListOfOrders(clientList, orderList);
        // Количество бумаги А не изменится
        assertEquals(0, completeList.stream().filter(client -> "C3".equals(client.getName())).findAny().get().getPapersMap().get("A"));
    }

    // при двух заявках на покупку выполнится первая
    @Test
    void matchFirstOrder() {
        Map<String, Integer> clientBalanceMap = Map.of("A", 370, "B", 15, "C", 950, "D", 560);
        Client clientSameOrder = new Client("C1", 0, new HashMap<>(clientBalanceMap));
        clientList.add(clientSameOrder);
        List<Order> orderList = new ArrayList<>(List.of(
                new Order("C2", OperationType.BUY, "B", 11, 1),
                new Order("C3", OperationType.SELL, "B", 11, 1),
                new Order("C1", OperationType.SELL, "B", 11, 1)
        ));
        List<Client> completedList = Exchange.completeListOfOrders(clientList, orderList);
        // проверка на основании что его papersMap не изменится, если сделки не было
        // для достоверности, можно поменять 2 и 3 Order местами и тест провалится
        assertEquals(clientBalanceMap, completedList.stream().filter(client -> "C1".equals(client.getName())).findAny().get().getPapersMap());
    }
}