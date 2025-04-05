package com.bridelabz.reflection;
import java.lang.reflect.*;
import java.util.Scanner;

// Class to get Person class information using reflection
public class DisplayClassInformation {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Take user input
        System.out.print("Enter an in-build class name along with package (e.g., java.util.ArrayList): ");
        String className = input.nextLine();

        // Use try-catch to handle exceptions
        try {
            // Load class using className
            Class<?> cls = Class.forName(className);

            // Display class name
            System.out.println("Class: " + cls.getName());

            // Display class constructors
            System.out.println("\nConstructors: ");
            Constructor<?>[] constructors = cls.getDeclaredConstructors();
            for(Constructor<?> constructor:constructors) {
                System.out.println(" " + constructor);
            }

            // Display class fields
            System.out.println("\nFields: ");
            Field[] fields = cls.getDeclaredFields();
            for(Field field:fields) {
                System.out.println(" " + field);
            }

            // Display class methods
            System.out.println("\nMethods: ");
            Method[] methods = cls.getDeclaredMethods();
            for(Method method:methods) {
                System.out.println(" " + method);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        input.close();
    }
}
// Sample Output ->
// Enter an in-build class name along with package (e.g., java.util.ArrayList): java.util.ArrayList
// Class: java.util.ArrayList
// Constructors:
//  public java.util.ArrayList()
//  public java.util.ArrayList(int)
//  public java.util.ArrayList(int,java.lang.Object)
//  public java.util.ArrayList(java.util.Collection)...

// Fields:
//  private static final int DEFAULT_CAPACITY
//  private static final java.lang.Object[] EMPTY_ELEMENTDATA
//  private static final java.lang.Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA
//  private java.lang.Object[] elementData...

// Methods:
//  public boolean add(java.lang.Object)
//  public void add(int,java.lang.Object)
//  public boolean addAll(java.util.Collection)
//  public boolean addAll(int,java.util.Collection)...
