package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Order {
    private String clientName;
    private OperationType operationType;
    private String paperName;
    private int price; // цена заявки
    private int count; // количество бумаг

    public int sum() {
        return price * count; // итоговая сумма заявки
    }
}
