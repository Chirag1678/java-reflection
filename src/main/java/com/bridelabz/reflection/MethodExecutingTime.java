package com.bridelabz.reflection;

import java.lang.reflect.Method;

// Sample class whose method is tested
class Testing {
    public void task() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}

// Class to measure execution time of a method
public class MethodExecutingTime {
    // main method
    public static void main(String[] args) {
        measureTime(Testing.class, "task");
    }

    // method to check execution time of a method using reflection
    public static void measureTime(Class<?> clazz, String methodName) {
        // Use try-catch to handle exceptions
        try {
            // Create instance of class
            Object instance = clazz.getDeclaredConstructor().newInstance();

            // Get the method
            Method method = clazz.getDeclaredMethod(methodName);
            method.setAccessible(true);

            // Start timer
            long startTime = System.nanoTime();

            // Invoke the method
            method.invoke(instance);

            // End timer
            long endTime = System.nanoTime();

            // Display execution time
            System.out.println("Execution time of " + methodName + " in " + clazz.getSimpleName() + ": " + (endTime-startTime)/1e6 + " ms.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Execution time of task in Testing: 500.123 ms.