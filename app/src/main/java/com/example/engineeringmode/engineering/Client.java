package com.example.engineeringmode.engineering;

/**
 * @anthor ljt
 * Date: 1/29/21
 * Time: 2:45 PM
 * Description:
 */
public class Client {

    public static void main(String[] args) {

        ConcreteFactory concreteFactory = new ConcreteFactory();

        Product product = concreteFactory.createProduct();

        product.method();
    }
}
