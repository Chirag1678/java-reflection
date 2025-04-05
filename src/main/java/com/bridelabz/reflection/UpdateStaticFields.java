package com.bridelabz.reflection;

import java.lang.reflect.Field;

// Class for operations
class Configuration {
    // Attribute
    private static String API_KEY="Secret_Key";
}

// Class to update static fields of other class using reflection
public class UpdateStaticFields {
    public static void main(String[] args) {
        // Use try-catch to handle exceptions
        try {
            // Load class object
            Configuration configuration = new Configuration();
            Class<?> cls = configuration.getClass();

            // Get desired field
            Field keyField = cls.getDeclaredField("API_KEY");

            // setAccessible to true to access and modify private fields
            keyField.setAccessible(true);

            System.out.println(keyField.getName() + " value before update: " + keyField.get(configuration));

            // update the field
            keyField.set(configuration, "No_More_Secrets");

            System.out.println(keyField.getName() + " value after update: " + keyField.get(configuration));
        } catch (NoSuchFieldException e) {
            System.out.println("No such field exists.");
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
// Sample Output ->
// API_KEY value before update: Secret_Key
// API_KEY value after update: No_More_Secrets