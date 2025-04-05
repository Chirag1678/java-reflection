package com.bridelabz.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

class Human {
    // Attributes
    private String name;
    private int age;
    private char gender;

    // Display details of Human
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
    }
}

// Class to map fields of a class from a map using reflection
public class CustomObjectMapper {
    public static void main(String[] args) {
        // Create a map to store fields, values of a class
        Map<String, Object> properties = new HashMap<>();
        // put values in map
        properties.put("name", "Abc");
        properties.put("age", 21);
        properties.put("gender", 'M');

        // Create instance using method
        Human p1 = toObject(Human.class, properties);

        // Display result
        if(p1!=null) p1.displayDetails();
    }

    // method to get values from map for class fields
    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) {
        // Use try-catch to handle exceptions
        try {
            // Get class constructor
            Constructor<T> constructor = clazz.getDeclaredConstructor();

            // Create instance of Human class
            T instance = constructor.newInstance();

            // Iterate in map to find fields and values
            for(Map.Entry<String, Object>entry:properties.entrySet()) {
                // get fields and values
                String fieldName = entry.getKey();
                Object fieldValue = entry.getValue();

                try {
                    // Get field by field name
                    Field field = clazz.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(instance, fieldValue);
                } catch (NoSuchFieldException e) {
                    System.out.println("No field with " + fieldName + " exists.");
                }
            }
            return instance;
        } catch (NoSuchMethodException e) {
            System.out.println("No such constructor exits.");
        } catch (Exception e) {
            throw new RuntimeException("An error occurred: " + e.getMessage());
        }
        return null;
    }
}
// Sample Output ->
// Name: Abc
// Age: 21
// Gender: M