package com.example.engineeringmode.IteratorPattern;

/**
 * @anthor ljt
 * Date: 2/24/21
 * Time: 2:31 PM
 * Description:
 */
public class Client {

    public static void main(String[] args) {
        ConcreteHandler concreteHandler = new ConcreteHandler();

        ConcreteHandler2 concreteHandler2 = new ConcreteHandler2();

        concreteHandler.successor = concreteHandler2;

        concreteHandler2.successor = concreteHandler;

        concreteHandler.handlerRequest("ConcreteHandler2");

    }
}
