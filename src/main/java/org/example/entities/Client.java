package org.example.entities;

import lombok.*;
import org.example.exchange.ExchangeService;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Client {
    private String name;
    private int balance; // баланс в долларах
    // балансы по ценным бумагам, ключ название бумаги, значение её количество
    private Map<String, Integer> papersMap;

    public Client(String[] array) {
        this.name = array[0];
        this.balance = Integer.parseInt(array[1]);
        this.papersMap = new HashMap<>() {{
            put("A", Integer.parseInt(array[2]));
            put("B", Integer.parseInt(array[3]));
            put("C", Integer.parseInt(array[4]));
            put("D", Integer.parseInt(array[5]));
        }};
    }

    // если количество бумаг выросло, передать дельту с +, если уменьшилось, то с минус
    public void updatePapersCount(String key, Integer delta) {
        papersMap.put(key, papersMap.get(key) + delta);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(name);
        stringBuilder.append(ExchangeService.SPLITTER).append(balance).append(ExchangeService.SPLITTER);
        for (Integer integer : papersMap.values()) {
            stringBuilder.append(integer).append(ExchangeService.SPLITTER);
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1); // убираем последний SPLITTER
        return stringBuilder.toString();
    }

}
