package com.bridelabz.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// Custom annotation
@Retention(RetentionPolicy.RUNTIME) // to use annotation at runtime
@Target(ElementType.TYPE) // to use it as a class annotation
@interface Author{
    String name();
}

// Create a class on which annotation is applied
@Author(name="J.K Rowling")
class Test {}

// Class to retrieve custom annotation using reflection
public class RetrieveCustomAnnotation {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Load class object
            Test test = new Test();
            Class<?> cls = test.getClass();

            // check if annotation is present
            if(cls.isAnnotationPresent(Author.class)) {
                // Get annotations details
                Author author = cls.getAnnotation(Author.class);

                System.out.println("Author name: " + author.name());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// Author name: J.K Rowling