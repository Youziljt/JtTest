package com.example.engineeringmode.widget;

import android.animation.TypeEvaluator;

/**
 * @author ljt
 * Date: 3/30/21
 * Time: 10:01 AM
 * Description:
 */
public class MyTypeValued implements TypeEvaluator<Point> {
    @Override
    public Point evaluate(float fraction, Point startValue, Point endValue) {
        //计算X,Y的插值
        float deltaX=endValue.getX()-startValue.getX();
        float deltaY=endValue.getY()-startValue.getY();
        //计算当前point的数值
        Point point=new Point(fraction*deltaX+startValue.getX(),fraction*deltaY+startValue.getY());
        return point;
    }
}
