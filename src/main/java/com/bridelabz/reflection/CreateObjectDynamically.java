package com.bridelabz.reflection;

import java.lang.reflect.Constructor;

// Class whose object is created
class Student {
    // Attribute
    private final String name;
    private final int age;
    private final char gender;
    private final int roll_number;

    // Constructor
    Student(String name, int age, char gender, int roll_number) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.roll_number = roll_number;
    }

    // display student details
    public void displayDetails() {
        System.out.println("Student Name: " + name + "| Age: " + age +"| Gender: " + gender + "| Roll Number: " + roll_number);
    }
}

// Class to dynamically create object of a class using reflection without using new keyword
public class CreateObjectDynamically {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Load the class object
            Class<?> cls = Student.class;

            // Get constructor using reflection
            Constructor<?> studentConstructor = cls.getDeclaredConstructor(String.class, int.class, char.class, int.class);

            // Create instance of Student class
            Student s1 = (Student) studentConstructor.newInstance("Abc", 21, 'M', 2110991234);

            // Display student details
            s1.displayDetails();

        } catch (NoSuchMethodException e) {
            System.out.println("No constructor of Student class present.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Student Name: Abc| Age: 21| Gender: M| Roll Number: 2110991234