package com.finalproject.demomodule.play.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

//    public static void main(String[] args) {
//        Map<String, Integer> ageMap = new HashMap<>();
//        ageMap.put("Alice", 25);
//        ageMap.put("Bob", 30);
//        ageMap.put("Charlie", 28);
//
//        int valueToCheck = 30;
//        boolean containsValue = ageMap.containsValue(valueToCheck);
//
//        if (containsValue) {
//            System.out.println("The map contains a mapping with value " + valueToCheck);
//        } else {
//            System.out.println("The map does not contain a mapping with value " + valueToCheck);
//        }
//    }

    public static void main(String[] args) {
        // Create a HashMap with some key-value pairs
        Map<String, Integer> ageMap = new HashMap<>();
        ageMap.put("Alice", 25);
        ageMap.put("Bob", 30);

        // Compute a value for a new key using a function
        String newKey = "Charlie";
        ageMap.computeIfAbsent(newKey, key -> calculateAge(key));

        // Print the updated map
        System.out.println(ageMap);
    }

    // A sample function to calculate age based on the length of the name
    private static Integer calculateAge(String name) {
        return name.length() * 2;
    }
}
