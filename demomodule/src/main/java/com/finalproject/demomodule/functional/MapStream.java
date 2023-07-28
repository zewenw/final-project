package com.finalproject.demomodule.functional;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MapStream {

    public static void main(String[] args) {
        Map<String, Integer> unsortedMap = new HashMap<>();
        unsortedMap.put("Apple", 3);
        unsortedMap.put("Banana", 1);
        unsortedMap.put("Orange", 5);
        unsortedMap.put("Grapes", 2);

        Map<String, Integer> sortedMap = new LinkedHashMap<>();
        unsortedMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .forEach(entry -> sortedMap.put(entry.getKey(), entry.getValue()));

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
