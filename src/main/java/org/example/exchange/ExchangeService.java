package org.example.exchange;

import org.example.entities.Client;
import org.example.entities.OperationType;
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
        return parsedClients.stream().map(array -> new Client(array[0],
                Integer.parseInt(array[1]),
                new HashMap<>(Map.of("A", Integer.parseInt(array[2]),
                        "B", Integer.parseInt(array[3]),
                        "C", Integer.parseInt(array[4]),
                        "D", Integer.parseInt(array[5]))))).toList();
    }

    public static List<Order> getOrdersFromFile(String uri) throws IOException {
        List<String[]> parsedOrders = parseFromFile(uri);
        return parsedOrders.stream().map(array -> new Order(array[0],
                OperationType.byShortString(array[1]),
                array[2],
                Integer.parseInt(array[3]),
                Integer.parseInt(array[4]))).toList();
    }

    public static void printClientsStatus(List<Client> clientList) throws IOException {
        String uri = "src/main/resources/result.txt";
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
