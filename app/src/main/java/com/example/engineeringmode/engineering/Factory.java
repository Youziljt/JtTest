package com.example.engineeringmode.engineering;

/**
 * @anthor ljt
 * Date: 1/29/21
 * Time: 2:41 PM
 * Description: 鸡肋： 返回的是鸡肋的产品
 */
public abstract class Factory {

    /**
     *
     * 抽象工厂：
     * 具体生产什么由子类去实现
     */

    public abstract Product createProduct();
}
