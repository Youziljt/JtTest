package com.example.engineeringmode.builder;

/**
 * @anthor ljt
 * Date: 2/1/21
 * Time: 10:10 AM
 * Description: 构建建造者： 提供具体的创建方法，以此来构建对象。
 *              抽象的builder类
 *
 */
public abstract class Builder {

    //设置主机
    public abstract void buildBoard(String board);
    //设置显示器
    public abstract void buildDisplay(String display);
    //设置操作系统
    public abstract void buildOs();
    //创建Computer
    public abstract Computer create();

}
