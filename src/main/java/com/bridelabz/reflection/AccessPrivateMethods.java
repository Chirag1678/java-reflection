package com.bridelabz.reflection;

import java.lang.reflect.Method;

// Class whose method is invoked
class Calculator {
    private int multiply(int a, int b) {
        return a*b;
    }
}

// Class to invoke private methods of a class using reflection
public class AccessPrivateMethods {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Load the Calculator class
            Calculator calculator = new Calculator();
            Class<?> cls = calculator.getClass();

            // Access private multiply method of class
            Method multiplyMethod = cls.getDeclaredMethod("multiply", int.class, int.class);

            // setAccessible to true
            multiplyMethod.setAccessible(true);

            // invoke method to calculate result
            System.out.println("Multiplication of 5 x 6 = " + multiplyMethod.invoke(calculator, 5, 6));

        } catch (NoSuchMethodException e) {
            System.out.println("No method named multiply exists.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Multiplication of 5 x 6 = 30