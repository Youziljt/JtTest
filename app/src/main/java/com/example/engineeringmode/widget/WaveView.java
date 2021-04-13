package com.example.engineeringmode.widget;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

import com.example.engineeringmode.R;

/**
 * author:yoyo
 * date:2020/6/9
 */
public class WaveView extends View {

    // 画复杂图形所需要的path
    private Path mPath;
    private Paint mCenterLinePaint;
    private float mCenterRadius = 0;


    // 中间贝塞尔动画的起始点

    private float mStartX;
    private float mStartY;
    // 自定义布局的一半宽高
    private float mViewWidthHalf, mViewHeightHalf;
    private float height;

    private ValueAnimator mWaveAnimator;


    public WaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPath = new Path();
        mCenterLinePaint = new Paint();
        mCenterLinePaint.setAntiAlias(true);
        mCenterLinePaint.setColor(getResources().getColor(R.color.orange));
        mCenterLinePaint.setStyle(Paint.Style.FILL_AND_STROKE);


    }

    /**
     * 整个自定义view的宽高
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidthHalf = (int) (w * 0.5);
        mViewHeightHalf = (int) (h * 0.5);

        mStartX = (w - (mViewWidthHalf)) / 2;
        mStartY = mViewHeightHalf;

        height = h;


        mCenterRadius = mViewHeightHalf;
        startRingAnimation(2000);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        mPath.reset();
        // start point
        mPath.moveTo(mStartX, mViewHeightHalf);
        // 贝塞尔曲线
        mPath.rCubicTo(mViewWidthHalf / 4, 0, mViewWidthHalf / 4, Math.abs(mViewHeightHalf - mCenterRadius), mViewWidthHalf / 2, Math.abs(mViewHeightHalf - mCenterRadius));
        mPath.rCubicTo(mViewWidthHalf / 4, 0, mViewWidthHalf / 4, -Math.abs(mViewHeightHalf - mCenterRadius), mViewWidthHalf / 2, -Math.abs(mViewHeightHalf - mCenterRadius));

        // 两边的圆角扇形
        mPath.addArc(0, mViewHeightHalf, 200, mViewHeightHalf + 200, 180, 90);
        mPath.addArc(mViewWidthHalf * 2 - 200, mViewHeightHalf, mViewWidthHalf * 2, mViewHeightHalf + 200, 270, 90);


        // 图形边框
        mPath.lineTo(this.getMeasuredWidth() - 100, mViewHeightHalf);
        mPath.lineTo(this.getMeasuredWidth(), mViewHeightHalf + 100);
        mPath.lineTo(this.getMeasuredWidth(), this.getMeasuredHeight());

        mPath.lineTo(0, this.getMeasuredHeight());
        mPath.lineTo(0, mViewHeightHalf + 100);
        mPath.lineTo(100, mViewHeightHalf);
        mPath.lineTo(mStartX, mViewHeightHalf);
        mPath.lineTo(mStartX * 2 + mStartX, mViewHeightHalf);

        mPath.setFillType(Path.FillType.WINDING);
        //Close path
        mPath.close();
        canvas.drawPath(mPath, mCenterLinePaint);


    }

    public void changeWave(float change) {

        mViewHeightHalf = Math.abs(height / 2 - height / 2 * change);
        postInvalidate();
    }


    public void startRingAnimation(long duration) {
        if (mWaveAnimator != null && mWaveAnimator.isRunning()) {
            mWaveAnimator.cancel();
            mWaveAnimator = null;
        }
        mWaveAnimator = ValueAnimator.ofFloat(mViewHeightHalf, 0);
        mWaveAnimator.setDuration(duration);
        mWaveAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mWaveAnimator.setInterpolator(new LinearInterpolator());
        mWaveAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {

                mViewHeightHalf = (float) animation.getAnimatedValue();

                postInvalidate();
            }
        });
        mWaveAnimator.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
//                controlPoint.x = (int) event.getX();
//                mViewHeightHalf =  event.getY();
//                invalidate();
                break;
        }
        return true;
    }
}
