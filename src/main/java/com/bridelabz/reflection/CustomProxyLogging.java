package com.bridelabz.reflection;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

// Custom proxy
interface Greeting {
    void sayHello();
}

class Hello implements Greeting {
    public void sayHello() {
        System.out.println("Hello from Proxy!!");
    }
}

// Class to handle object invocation
class CustomInvocationHandler implements InvocationHandler {
    private final Object target;

    CustomInvocationHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Name of the method:" + method.getName());
        return method.invoke(target, args);
    }
}

// Class to log custom proxies using reflection
public class CustomProxyLogging {
    public static void main(String[] args) {
        // Create Object of Greeting
        Greeting original = new Hello();

        // Create Proxy Instance
        Greeting proxyInstance = (Greeting) Proxy.newProxyInstance(Hello.class.getClassLoader(), new Class[]{Greeting.class}, new CustomInvocationHandler(original));

        proxyInstance.sayHello();
    }
}
// Sample Output ->
// Name of the method:sayHello
// Hello from Proxy!!