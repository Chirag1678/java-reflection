package com.bridelabz.reflection;

import java.lang.reflect.Method;
import java.util.Scanner;

// Class whose methods are invoked
class MathOperations {
    // method to add two numbers
    public int add(int a, int b) {
        return a+b;
    }

    // method to subtract a number from another
    public int subtract(int a, int b) {
        return Math.max(a,b)-Math.min(a,b);
    }

    // method to multiply two numbers
    public int multiply(int a, int b) {
        return a*b;
    }
}

// Class to invoke methods dynamically based on user input
public class InvokeMethodDynamically {
    public static void main(String[] args) {
        // Create a Scanner Object
        Scanner input = new Scanner(System.in);

        // Take user input
        System.out.print("Enter operation name: ");
        String operation = input.next();

        System.out.print("Enter first number: ");
        int n1 = input.nextInt();

        System.out.print("Enter second number: ");
        int n2 = input.nextInt();

        // Use try-catch to handle exceptions
        try {
            // Load the class object
            MathOperations operations = new MathOperations();
            Class<?> cls = operations.getClass();

            // Get desired method by user
            Method userMethod = cls.getDeclaredMethod(operation, int.class, int.class);

            // Invoke method to calculate result
            int result = (int) userMethod.invoke(operations, n1, n2);

            switch(operation) {
                case "add":
                    System.out.printf("Addition of %d and %d is: %d", n1, n2, result);
                    break;

                case "subtract":
                    System.out.printf("Subtraction of %d from %d is: %d", n1, n2, result);
                    break;

                case "multiply":
                    System.out.printf("Multiplication of %d and %d is: %d", n1, n2, result);
                    break;

                default:
                    System.out.println("Invalid method try again.");
            }
        } catch (NoSuchMethodException e) {
            System.out.println("No such method exists.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        input.close();
    }
}
// Sample Output ->
// Enter operation name: add
// Enter first number: 5
// Enter second number: 6
// Addition of 5 and 6 is: 11

// Enter operation name: subtract
// Enter first number: 5
// Enter second number: 6
// Subtraction of 6 from 5 is: 1

// Enter operation name: multiply
// Enter first number: 5
// Enter second number: 6
// Multiplication of 5 and 6 is: 30