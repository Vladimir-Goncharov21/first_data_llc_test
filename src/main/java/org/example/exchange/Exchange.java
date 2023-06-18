package org.example.exchange;

import org.example.entities.Client;
import org.example.entities.OperationType;
import org.example.entities.Order;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Exchange {
    public static List<Client> completeListOfOrders(List<Client> clientList, List<Order> orderList) {

        //  собираем клиентов в мапу по имени, что бы проще и быстрее их доставать
        Map<String, Client> clientHashMap = clientList.stream().collect(Collectors.toMap(Client::getName, Function.identity()));
        // делим список заявок в мапу с покупками и продажами
        Map<OperationType, List<Order>> sortedOrdersMap = orderList.stream().collect(Collectors.groupingBy(Order::getOperationType));
        for (Order buyOrder : sortedOrdersMap.get(OperationType.BUY)) {
            Optional<Order> optionalOrder = sortedOrdersMap.get(OperationType.SELL).stream().filter(sellOrder -> !sellOrder.getClientName().equals(buyOrder.getClientName())
                    && sellOrder.getPaperName().equals(buyOrder.getPaperName())
                    && sellOrder.getPrice() == buyOrder.getPrice()
                    && sellOrder.getCount() == buyOrder.getCount()).findAny();
            if (optionalOrder.isPresent()) {
                Order sellOrder = optionalOrder.get();
                sortedOrdersMap.get(OperationType.SELL).remove(sellOrder);
                // обработка покупки
                Client buyer = clientHashMap.get(buyOrder.getClientName());
                clientHashMap.put(buyer.getName(), updateClientFromOrder(buyer, buyOrder));

                // обработка продажи
                Client seller = clientHashMap.get(sellOrder.getClientName());
                clientHashMap.put(seller.getName(), updateClientFromOrder(seller, sellOrder));
            }
        }
        return clientHashMap.values().stream().sorted(Comparator.comparing(Client::getName)).toList();
    }

    private static Client updateClientFromOrder(Client client, Order order) {
        if (order.getOperationType() == OperationType.BUY) {
            client.setBalance(client.getBalance() - order.sum());
            client.updatePapersCount(order.getPaperName(), order.getCount());
        } else {
            client.setBalance(client.getBalance() + order.sum());
            client.updatePapersCount(order.getPaperName(), -order.getCount());
        }
        return client;
    }
}
