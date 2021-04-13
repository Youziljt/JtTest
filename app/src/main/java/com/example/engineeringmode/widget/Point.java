package com.example.engineeringmode.widget;

/**
 * @author ljt
 * Date: 3/30/21
 * Time: 10:00 AM
 * Description:
 */
public class Point {
    //x坐标
    private float x;
    //y坐标
    private float y;
    public Point(float x,float y){
        this.x=x;
        this.y=y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
