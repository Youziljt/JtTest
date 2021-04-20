package com.example.engineeringmode.widget.jt;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author ljt
 * Date: 3/31/21
 * Time: 10:32 AM
 * Description:  基础属性：介绍模块
 */
public class SloopView extends View {

    private Paint mPaint = new Paint();


    /**
     * new view调用。java代码调用才会调用。
     */
    public SloopView(Context context) {
        super(context);
    }

    /**
     * xml布局调用
     */
    public SloopView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaint();
    }

    private void initPaint() {
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10f);
    }

    //设置了自定义defStyleAttr属性后期介绍。
    public SloopView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * view的大小不仅由自身所决定，也会收到父控件的影响
     *
     * @param widthMeasureSpec  参数其实不是宽和高，而是由宽高和各自方向上对应的测量模式合成的值。
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //TODO 注意：
//        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
//        如果对View的宽高进行修改了，不要调用 super.onMeasure( widthMeasureSpec, heightMeasureSpec); 要调用 setMeasuredDimension( widthsize, heightsize); 这个函数

        /**
         * 总结参数中提到的测量模式：
         * UNSPECIFIED （unspecified）默认：任意大小
         * EXACTLY （exactly）父控件已经明确指定子控件大小
         * AT_MOST（at_most） 表示子view具体大小没有限制，但是存在上限，上限一般为父view大小。
         *
         */
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);


    }

    /**
     * TODO 注意：这个函数在视图大小发生改变时候调用：
     * 在测量玩view ，并使用setMeasuredDimension 函数之后view大小基本确定，为什么还要再次确定view大小呢？
     * 答案： 因为view大小并非自身决定，受父控件影响。最好使用onSizeChanged回调函数。
     *
     * @param w
     * @param h
     * @param oldw
     * @param oldh
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //只需要注意 w h 两个参数：
    }


    /**
     * 确定子view的位置，在自定义ViewGroup 中会用到，他调用的是子view的layout函数
     *
     * @param changed
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        //在自定义ViewGroup中，onLayout一般是循环取出子View，然后经过计算得出各个子View位置的坐标值，然后用以下函数设置子View位置。
//        child.layout(l, t, r, b);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        //绘制矩形---------------
        // 第一种 对角线两个点的坐标值
//        canvas.drawRect(100,100,800,400,mPaint);

// 第二种
//        Rect rect = new Rect(100,100,800,400);
//        canvas.drawRect(rect,mPaint);

// 第三种
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawRect(rectF,mPaint);
        //TODO 2，3区别：Rect是int整形，RectF是float单精度浮点型


        //绘制圆角矩形
        // 第一种
//        RectF rectF = new RectF(100,100,800,400);
//        canvas.drawRoundRect(rectF,30,30,mPaint);
        //TODO 椭圆的两个半径。


        //绘制圆弧
        // 第一种
//        public void drawArc(@NonNull RectF oval, float startAngle, float sweepAngle, boolean useCenter, @NonNull Paint paint){}

// 第二种
//        public void drawArc(float left, float top, float right, float bottom, float startAngle,
//        float sweepAngle, boolean useCenter, @NonNull Paint paint) {}
//        startAngle  // 开始角度
//        sweepAngle  // 扫过角度
//        useCenter   // 是否使用中心






    }
}
