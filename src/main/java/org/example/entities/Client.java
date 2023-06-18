package org.example.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class Client {
    private String name;
    private int balance; // баланс в долларах
    // балансы по ценным бумагам, ключ название бумаги, значение её количество
    private Map<String, Integer> papersMap;
    // если количество бумаг выросло, передать дельту с +, если уменьшилось, то с минус
    public void updatePapersCount(String key, Integer delta) {
        papersMap.put(key, papersMap.get(key) + delta);
    }

}

