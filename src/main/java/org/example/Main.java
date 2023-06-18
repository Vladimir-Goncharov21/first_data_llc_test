package org.example;

import org.example.entities.Client;
import org.example.entities.Order;
import org.example.exchange.Exchange;
import org.example.exchange.ExchangeService;

import java.io.IOException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<Client> clientList = ExchangeService.getClientsFromFile("src/main/resources/clients.txt");
        List<Order> orderList = ExchangeService.getOrdersFromFile("src/main/resources/orders.txt");
        List<Client> resultList = Exchange.completeListOfOrders(clientList, orderList);
        ExchangeService.printClientsStatus(resultList, "src/main/resources/result.txt");
    }
}