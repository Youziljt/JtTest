package com.example.engineeringmode.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.engineeringmode.R;

/**
 * @author ljt
 * Date: 3/30/21
 * Time: 9:57 AM
 * Description:
 */
public class CircleView extends View {
    //圆形控件半径
    private static final float RADIUS=70f;
    Paint paint;
    Point currentPoint;
    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        //初始画笔
        paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(context.getResources().getColor(R.color.colorPrimary));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //初始化状态
        if (currentPoint==null){
            canvas.drawCircle(RADIUS,RADIUS,RADIUS,paint);
            //初始坐标
            Point startPoint=new Point(RADIUS,RADIUS);
            //结束坐标
            Point endPoint=new Point(700,1000f);
            ValueAnimator valueAnimator = ValueAnimator.ofObject(new MyTypeValued(), startPoint, endPoint);
            //动画时长
            valueAnimator.setDuration(3000);
            //重复次数
            valueAnimator.setRepeatCount(0);
            //重复模式
            valueAnimator.setRepeatMode(ValueAnimator.RESTART);
            //延时时长
            valueAnimator.setStartDelay(1000);
            valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    //记录当前位置
                    currentPoint = (Point) animation.getAnimatedValue();
                    //重绘
                    invalidate();
                }
            });
            valueAnimator.start();
        }else{
            //重新绘制新的view，实现view的移动
            canvas.drawCircle(currentPoint.getX(),currentPoint.getY(),RADIUS,paint);
        }
    }
}
