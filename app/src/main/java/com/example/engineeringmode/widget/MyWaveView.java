package com.example.engineeringmode.widget;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.Nullable;

/**
 * @author ljt
 * Date: 3/30/21
 * Time: 9:08 AM
 * Description: 贝塞尔曲线应用 WaveView
 */
public class MyWaveView extends View implements View.OnClickListener {

    private Path mPath;
    private Paint mPaintBezier;
    /**
     * 可动态修改值 支持手动更换
     */
    private int mWaveLength;
    private int mScreenHeight;
    private int mScreenWidth;
    private int mCenterY;
    /**
     * 预加载数量+1 绘制屏幕外一个贝塞尔曲线，形成波浪回滚
     */
    private int mWaveCount;

    private ValueAnimator mValueAnimator;
    private int count = 0;

    /**
     * 波浪流动X轴偏移量
     */
    private int mOffsetX;
    /**
     * 波浪流动y轴偏移量
     */
    private int mOffsetY;

    public MyWaveView(Context context) {
        super(context);
    }

    public MyWaveView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaintBezier.setColor(Color.LTGRAY);
        mPaintBezier.setStrokeWidth(8);
        mPaintBezier.setStyle(Paint.Style.FILL_AND_STROKE);
        mWaveLength = 800;
    }

    public MyWaveView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //TODO 同以下onSizeChanged的TODO 需要在重载第二个方法生成
//        mPaintBezier = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaintBezier.setColor(Color.LTGRAY);
//        mPaintBezier.setStrokeWidth(8);
//        mPaintBezier.setStyle(Paint.Style.FILL_AND_STROKE);
//        mWaveLength = 800;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mPath = new Path();
        setOnClickListener(this);

        mScreenHeight = h;
        mScreenWidth = w;
        mCenterY = h / 2;

        //此处多加1，是为了预先加载屏幕外的一个波浪，持续报廊移动时的连续性
        //TODO 除数不能为零，请务必检查代码是否有机会出现除数为零的情况
        /**
         * 返回最接近它的整数，若有两个返回接近的整数，则取最大的那个.(向右取整)
         * round方法：
         * static long round(double a)
         * 此方法返回的参数最接近的long.
         * static int round(float a)
         * 此方法返回的参数最接近的整数
         * for example ：
         * System.out.println(Math.round(12.4));  12
         * System.out.println(Math.round(12.5));   13
         * System.out.println(Math.round(12.9));   13
         * System.out.println(Math.round(-12.4));   -12
         * System.out.println(Math.round(-12.5));   -12
         * System.out.println(Math.round(-12.9));  -13
         */
        mWaveCount = (int) Math.round(mScreenWidth / mWaveLength + 1.5);

    }

    @Override
    public void onClick(View view) {
        //设置动画运动距离
        mValueAnimator = ValueAnimator.ofInt(0, mWaveLength);
        mValueAnimator.setDuration(1000);
        //设置播放数量无限循环
        mValueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        mValueAnimator.setInterpolator(new LinearInterpolator());
        mValueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //获取偏移量，绘制波浪曲线的X横坐标加上此偏移量，产生移动效果
                mOffsetX = (int) valueAnimator.getAnimatedValue();
                count++;

                invalidate();
            }
        });
        mValueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPath.reset();
        //类比内存优化 圆形波浪上升
        //判断波浪升起偏移量
//        if(mOffsetY<mCenterY+60){
//            mOffsetY += 10;
//        }else {
            //绘制满屏时，即升起的波浪沾满屏幕时，取消动画重复绘制
//            mValueAnimator.cancel();
//        }
//        Y坐标每次绘制时减去偏移量，即波浪升高
//        mPath.moveTo(-mWaveLength + mOffsetX, mCenterY-mOffsetY);

        mPath.moveTo(-mWaveLength + mOffsetX, mCenterY);

//        每次循环绘制两个二阶贝塞尔曲线形成一个完整波形（含有一个上拱圆，一个下拱圆）
        for (int i = 0; i < mWaveCount; i++) {
            //此处的60是指波浪起伏的偏移量，自定义为60
           /*
            mPath.quadTo(-mWaveLength * 3 / 4 + i * mWaveLength + mOffsetX, mCenterY + 60, -mWaveLength / 2 + i * mWaveLength + mOffset, mCenterY);
            mPath.quadTo(-mWaveLength / 4 + i * mWaveLength + mOffsetX, mCenterY - 60, i * mWaveLength + mOffset, mCenterY);
            */

            //：相对位移
            mPath.rQuadTo(mWaveLength / 4, -60, mWaveLength / 2, 0);
            mPath.rQuadTo(mWaveLength / 4, +60, mWaveLength / 2, 0);

        }
        mPath.lineTo(mScreenWidth, mScreenHeight);
        mPath.lineTo(0, mScreenHeight);
        mPath.close();
        canvas.drawPath(mPath, mPaintBezier);
    }


    /**
     * **********引用下属性动画概念：***************
     *   ValueAnimator.ofInt() ：以整形初始值平稳过渡到整形结束值
     *     数据过渡的模式(加速？匀速？....) ---由插值器决定。onInt有默认的插值器IntEvaluator
     *     数据变化的具体数值(当前时刻数值的具体值)--有估值器决定
     *
     *   ValueAnimator.ofFloat：初始值和结束值为float类型，默认的估值器为FloatEvaluator
     *
     *   ValueAnimator.ofObject : 需要自行建立差值器，实现 TypeEvaluator 接口
     *
     *
     *
     *
     *
     *   ***********补间动画：*****************
     *   ScaleAnimation 有三种构造方法：
     *
     *  //new ScaleAnimation(0, 1.4f, 0, 1.4f);
     *  public ScaleAnimation(float fromX, float toX, float fromY, float toY) {
     *   }
     *   //仅介绍参数：pivotX,pivotY分别代表起始位置的x、y方向的坐标
     *  public ScaleAnimation(float fromX, float toX, float fromY, float toY,
     *             float pivotX, float pivotY) {
     *   }
     *   //pivotXType和pivotYType有2种模式，RELATIVE_TO_SELF（相对于自身）和RELATIVE_TO_PARENT（相对于父布局），如果设置这个，pivotx,pivotY的值就应该是0-1的浮点数，这里分别对应xml中的%（自身）和%p（父布局）
     *  public ScaleAnimation(float fromX, float toX, float fromY, float toY,
     *             int pivotXType, float pivotXValue, int pivotYType, float pivotYValue) {
     *   }
     *
     *  TranslateAnimation 位移动画 有两种构造，类似上面1，3
     *
     *  RotateAnimation 旋转动画
     *   //fromDegrees 为开始旋转的角度，正值代表顺时针方向度数，负值代码逆时针方向度数，toDegrees为结束时旋转角度，取值同fromDegrees
     *   public RotateAnimation(float fromDegrees, float toDegrees) {
     *   }
     *   //
     *   public RotateAnimation(float fromDegrees, float toDegrees, float pivotX, float pivotY) {
     *   }
     *   public RotateAnimation(float fromDegrees, float toDegrees, int pivotXType, float pivotXValue,
     *             int pivotYType, float pivotYValue) {
     *   }
     *
     *   AlphaAnimation 透明度动画 只有一种构造方法
     *
     *   AnimationSet 动画集合
     *    animationSet = new AnimationSet(true);
     *                 animationSet.addAnimation(rotateAnimation);
     *                 animationSet.addAnimation(translateAnimation);
     *                 animationSet.addAnimation(scaleAnimation);
     *                 animationSet.addAnimation(alphaAnimation);
     **/


    /**
     * invalidate和postInvalidate的区别及使用
     *
     * android中实现view的更新有两组方法，一组是invalidate，另一组是postInvalidate，其中前者是在UI线程自身中使用，而后者在非UI线程中使用。
     *
     *
     */



























}
