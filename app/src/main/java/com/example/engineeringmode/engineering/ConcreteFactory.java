package com.example.engineeringmode.engineering;

/**
 * @anthor ljt
 * Date: 1/29/21
 * Time: 2:43 PM
 * Description:  工厂具体实现：具体的逻辑业务
 */
public  class ConcreteFactory extends Factory{
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}
