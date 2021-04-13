package com.example.engineeringmode.widget.jt;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * @author ljt
 * Date: 3/29/21
 * Time: 9:00 AM
 * Description:
 */
public class MyViewThree extends View {

    private Point controlPoint = new Point(getWidth() / 2, 0);
    private Paint paint;
    private Path path;
    private float height;

    private float mViewWidthHalf, mViewHeightHalf;
    private float reduce_one = 0;
    private float reduce_two = 0;
    private float reduce_three = 0;

    private ValueAnimator mAlarmAnimator;
    private int mAlpha ;


    public MyViewThree(Context context) {
        super(context);
    }

    public MyViewThree(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyViewThree(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        path = new Path();
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidthHalf = (int) (w);
        mViewHeightHalf = (int) (h);

        startRingAnimation(500);

    }

    private void startRingAnimation(long duration) {
        if (mAlarmAnimator != null && mAlarmAnimator.isRunning()) {
            mAlarmAnimator.cancel();
            mAlarmAnimator = null;
        }
        mAlarmAnimator = ValueAnimator.ofInt(1, 150);
        mAlarmAnimator.setDuration(duration);
        mAlarmAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mAlarmAnimator.setInterpolator(new LinearInterpolator());
        mAlarmAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mAlpha = (int) animation.getAnimatedValue();
                changeMyView(mAlpha);
//                postInvalidate();
            }
        });
        mAlarmAnimator.start();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();

        path.moveTo(0, 200 - reduce_one);
        path.quadTo(getWidth() / 10, 250 - reduce_three, getWidth() / 5, 200 - reduce_one);
        path.quadTo(getWidth() / 2, 50 - reduce_two, (4 * getWidth()) / 5, 200 - reduce_one);
        path.quadTo((9 * getWidth()) / 10, 250 - reduce_three, getWidth(), 200 - reduce_one);

        path.lineTo(getWidth(), 255);

        path.lineTo(0, 255);
        path.lineTo(0, 200 - reduce_one);
        canvas.drawPath(path, paint);

    }

    public void changeMyView(int change) {
        reduce_one = Math.abs(change / 2);
        reduce_two = Math.abs(change / 8);
        reduce_three = Math.abs(change / 2);
        mViewHeightHalf = change;
        postInvalidate();
    }






}
