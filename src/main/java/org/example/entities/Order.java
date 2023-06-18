package org.example.entities;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Order {
    private String clientName;
    private OperationType operationType;
    private String paperName;
    private int price; // цена заявки
    private int count; // количество бумаг

    public Order(String[] array) {
        this.clientName = array[0];
        this.operationType = OperationType.byShortString(array[1]);
        this.paperName = array[2];
        this.price = Integer.parseInt(array[3]);
        this.count = Integer.parseInt(array[4]);
    }

    public int sum() {
        return price * count; // итоговая сумма заявки
    }
}
