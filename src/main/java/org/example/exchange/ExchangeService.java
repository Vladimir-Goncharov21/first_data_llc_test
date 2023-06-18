package org.example.exchange;

import org.example.entities.Client;
import org.example.entities.Order;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class ExchangeService {
    public static final String SPLITTER = "\t";

    private static List<String[]> parseFromFile(String uri) throws IOException {
        List<String> stringList = Files.readAllLines(Path.of(uri));
        return stringList.stream().map(string -> string.split(SPLITTER)).toList();
    }

    public static List<Client> getClientsFromFile(String uri) throws IOException {
        List<String[]> parsedClients = parseFromFile(uri);
        return parsedClients.stream().map(Client::new).toList();
    }

    public static List<Order> getOrdersFromFile(String uri) throws IOException {
        List<String[]> parsedOrders = parseFromFile(uri);
        return parsedOrders.stream().map(Order::new).toList();
    }

    public static void printClientsStatus(List<Client> clientList, String uri) throws IOException {
        Path resultPath = Paths.get(uri);
        if (!resultPath.toFile().exists()) {
            Files.createFile(resultPath);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Client client : clientList) {
            stringBuilder.append(client.toString());
            stringBuilder.append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1); // убираем последний \n
        Files.writeString(resultPath, stringBuilder.toString());
    }
}
