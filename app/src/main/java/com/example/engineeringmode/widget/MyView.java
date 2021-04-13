package com.example.engineeringmode.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.example.engineeringmode.R;

/**
 * @author ljt
 * Date: 3/29/21
 * Time: 9:00 AM
 * Description:
 */
public class MyView extends View {

    private Paint paint, triangle_paint;
    private Path path, triangle_path;

    private Context mContext;
    private float mHeight, mWidth;
    private float moveHeight, moveWidth, triangle_move_h, can_y;


    /**
     * 距离中心点的偏移量
     */
    private float the_offset = 30;

    private int height, width;

    /**
     * 箭头高度
     */
    private double H = 18;
    /**
     * 底边的一半
     */
    private double L = 13.5;

    /**
     * 箭头角度
     */
    private double angle = Math.atan(L / H);
    /**
     * 箭头的长度
     */
    private double arrowLength = Math.sqrt(L * L + H * H);
    private float startX = 200;
    private float startY = 200;
    private float endX = 500;
    private float endY = 500;


    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(mContext.getColor(R.color.orange));
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        path = new Path();

        triangle_paint = new Paint();
        triangle_paint.setStyle(Paint.Style.STROKE);
        triangle_paint.setColor(Color.BLACK);
        triangle_paint.setStrokeWidth(10);
        triangle_paint.setAntiAlias(true);
        triangle_path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mHeight = (float) h;
        mWidth = (float) w;
        height = getHeight();
        width = getWidth();
        can_y = height * 9 / 10;
        triangle_move_h = moveHeight + 150;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        path.reset();
        triangle_path.reset();

        initBottom();
        initTriangle();
        canvas.drawPath(path, paint);
//        canvas.drawPath(triangle_path, triangle_paint);
    }

    private void initTriangle() {
        triangle_path.moveTo(width / 2 - 50, triangle_move_h + 50);
        triangle_path.lineTo(width / 2, triangle_move_h);
        triangle_path.lineTo(width / 2 + 50, triangle_move_h + 50);
        invalidate();
    }

    private void initBottom() {
        path.moveTo(0, height);

//        path.quadTo(getWidth() / 2, can_y, getWidth(), height);

        path.cubicTo(getWidth() / 4, can_y, getWidth() * 3 / 4, can_y, getWidth(), height);


        path.cubicTo(getWidth() / 2 - 30, can_y, getWidth() / 2 + 30, can_y, getWidth(), height);

        path.lineTo(0, height);
//        invalidate();
    }


//    private void initTriangle() {
//        //箭头就是个三角形，我们已经有一个点了，根据箭头的角度和长度，确定另外2个点的位置
//        double[] point1 = rotateVec(endX - startX, endY - startY, angle, arrowLength);
//        double[] point2 = rotateVec(endX - startX, endY - startY, -angle, arrowLength);
//        double point1_x = endX - point1[0];
//        double point1_y = endY - point1[1];
//        double point2_x = endX - point2[0];
//        double point2_y = endY - point2[1];
//        int x3 = (int) point1_x;
//        int y3 = (int) point1_y;
//        int x4 = (int) point2_x;
//        int y4 = (int) point2_y;
//        // 画线
//        triangle_path.moveTo(endX, endY);
//        triangle_path.lineTo(x3, y3);
//        triangle_path.lineTo(x4, y4);
//        triangle_path.close();
//        invalidate();
//    }

    /**
     * @param diffX       X的差值
     * @param diffY       Y的差值
     * @param angle       箭头的角度（箭头三角形的线与直线的角度）
     * @param arrowLength 箭头的长度
     */

    public double[] rotateVec(float diffX, float diffY, double angle, double arrowLength) {
        double arr[] = new double[2];
        // 下面的是公式，得出的是以滑动出的线段末点为中心点旋转angle角度后,线段起点的坐标，这个旋转后的线段也就是“变长了的箭头的三角形的一条边”
        //推导见注释1
        double x = diffX * Math.cos(angle) - diffY * Math.sin(angle);
        double y = diffX * Math.sin(angle) + diffY * Math.cos(angle);
        double d = Math.sqrt(x * x + y * y);
        //根据相似三角形，得出真正的箭头三角形顶点坐标，这里见注释2
        x = x / d * arrowLength;
        y = y / d * arrowLength;
        arr[0] = x;
        arr[1] = y;
        return arr;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {

            case MotionEvent.ACTION_MOVE:
                moveWidth = event.getX();
                moveHeight = event.getY();
                if (moveHeight > height * 6 / 10) {
//                    triangle_move_h = moveHeight + 100;
                    can_y = moveHeight;
                    invalidate();
                } else {
//                    needShow();
                }
                break;
            case MotionEvent.ACTION_UP:
//                moveHeight = width * 9 / 10;
                can_y = height * 9 / 10;
//                triangle_move_h = moveHeight + 150;
                invalidate();
                break;
        }
        return true;
    }


    public boolean canShow() {
        if (moveHeight < height * 6 / 10) {
            return true;
        }
        return false;
    }

//    abstract void needShow();

}
