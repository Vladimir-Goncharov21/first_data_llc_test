package org.example.entities;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@Getter
@Setter
public class Client {
    private String name;
    private int balance; // баланс в долларах
    // балансы по ценным бумагам, ключ название бумаги, значение её количество
    private Map<String, Integer> papersMap;

    // если количество бумаг выросло, передать дельту с +, если уменьшилось, то с минус
    public void updatePapersCount(String key, Integer delta) {
        papersMap.put(key, papersMap.get(key) + delta);
    }

    @Override
    public String toString() {
        return String.format("%s\t%d\t%s\t%s\t%s\t%s", name, balance, papersMap.get("A"), papersMap.get("B"), papersMap.get("C"), papersMap.get("D"));
    }
}
