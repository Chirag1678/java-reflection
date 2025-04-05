package com.bridelabz.reflection;

import java.lang.reflect.Field;

// Class on which we use reflection
class Person {
    // Attributes
    private String name;
    private int age;

    // Constructor
    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // getter methods
    public String getName() { return name; }
    public int getAge() { return age; }

    // setter methods
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
}

// Class to modify private field of another class using reflection
public class ModifyPrivateFields {
    public static void main(String[] args) {
        // Use try-catch to handle exception
        try {
            // Create class object and Load the class
            Person person = new Person("Abc", 21);
            Class<?> cls = person.getClass();

            // get class fields
            Field[] fields = cls.getDeclaredFields();
            //loop to get the desired field
            for(Field field:fields) {
                if(field.getName().equalsIgnoreCase("age")) {
                    // setAccessible to true to access and update private fields
                    field.setAccessible(true);
                    System.out.println("Value of age before updating: " + field.get(person));
                    field.set(person, 13);
                    System.out.println("Value of age after updating: " + field.get(person));
                }
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Value of age before updating: 21
// Value of age after updating: 13