package com.bridelabz.reflection;

import java.lang.reflect.Field;

// Class with fields and values
class Vehicle {
    // Attributes
    private String type;
    private int tyres;
    private double mileage;
    private int capacity;

    // Constructor
    Vehicle(String type, int tyres, double mileage, int capacity) {
        this.type = type;
        this.tyres = tyres;
        this.mileage = mileage;
        this.capacity = capacity;
    }
}

// class to display JSON Representation of class fields and values
public class DisplayJSONRepresentation {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Create Vehicle class object and load class object
            Vehicle car = new Vehicle("Car", 4, 25.5, 4);
            Class<?> cls = car.getClass();

            // Get class fields
            Field[] fields = cls.getDeclaredFields();

            System.out.println("JSON Representation of Vehicle: ");
            System.out.println("{");
            for(Field field:fields) {
                field.setAccessible(true);
                System.out.printf("  \"%s\": %s,\n", field.getName(), field.get(car));
            }
            System.out.println("}");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// JSON Representation of Vehicle:
// {
//   "type": Car,
//   "tyres": 4,
//   "mileage": 25.5,
//   "capacity": 4,
// }