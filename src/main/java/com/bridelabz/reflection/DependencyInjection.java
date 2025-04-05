package com.bridelabz.reflection;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

// Custom annotation inject
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface Inject{}

// Create a DI container
class ClassLevelDIContainer {
    private Map<Class<?>, Object> instances = new HashMap<>();

    public <T> T getBean(Class<T> clazz) {
        try {
            // Check if instance is already created
            if(instances.containsKey(clazz)) {
                return clazz.cast(instances.get(clazz));
            }

            // Create Object
            T instance = clazz.getDeclaredConstructor().newInstance();
            instances.put(clazz, instance);

            // Inject dependencies (based on field type, only if @Inject is on class)
            for(Field field:clazz.getDeclaredFields()) {
                Class<?> dependencyType = field.getType();
                if(dependencyType.isAnnotationPresent(Inject.class)) {
                    Object dependency = getBean(dependencyType);
                    field.setAccessible(true);
                    field.set(instance, dependency);

                    System.out.println("Injected " + dependencyType.getSimpleName() + " into " + clazz.getSimpleName());
                }
            }
            return instance;
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return null;
    }
}

// Sample classes to test dependency Injection
@Inject
class Engine {
    public void start() {
        System.out.println("Engine started!");
    }
}

class Car {
    private Engine engine;

    public void drive() {
        engine.start();
        System.out.println("Car is driving...");
    }
}

// Class to inject dependency using recursion based on @Inject annotation
public class DependencyInjection {
    public static void main(String[] args) {
        ClassLevelDIContainer container = new ClassLevelDIContainer();
        Car car = container.getBean(Car.class);
        car.drive();
    }
}
// Sample Output ->
// Injected Engine into Car
// Engine started!
// Car is driving...