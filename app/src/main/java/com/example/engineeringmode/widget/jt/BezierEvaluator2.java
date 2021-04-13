package com.example.engineeringmode.widget.jt;

import android.animation.TypeEvaluator;
import android.graphics.Point;
import android.graphics.PointF;

/**
 * @author ljt
 * Date: 3/30/21
 * Time: 10:36 AM
 * Description:
 */
public class BezierEvaluator2 implements TypeEvaluator{


    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.x + fraction * (endPoint.x - startPoint.x);

        float y = (float) (Math.sin(x * Math.PI / 180) * 100) + endPoint.y / 2;


        PointF point = new PointF();

        point.x = x;

        point.y = y;

        return point;

    }
}
