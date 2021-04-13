package com.example.engineeringmode.widget

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

/**
 * @author ljt
 * Date: 3/29/21
 * Time: 1:37 PM
 * Description:
 */
internal class MyViewTwo : View {
    private val controlPoint = Point(200, 200)
    private var paint: Paint? = null

    //    private Path path;
    constructor(context: Context?) : super(context) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {//        init();
    }

    //    private void init() {
    //        paint = new Paint();
    //        paint.setStyle(Paint.Style.FILL_AND_STROKE);
    //        paint.setStyle(Paint.Style.FILL);
    //        paint.setStyle(Paint.Style.STROKE);
    //        paint.setColor(Color.BLACK);
    //        paint.setStrokeWidth(10);
    //        paint.setAntiAlias(true);
    //        path = new Path();
    //    }
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint = Paint()
        //        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint!!.style = Paint.Style.FILL
        //        paint.setStyle(Paint.Style.STROKE);
        paint!!.color = Color.BLACK
        paint!!.strokeWidth = 10f
        paint!!.isAntiAlias = true
        val path = Path()
        //        moveTo 不会进行绘制，只用于移动移动画笔。
        path.moveTo(0f, 200f)

//        path.quadTo(controlPoint.x, controlPoint.y, getWidth(), 100);
        path.quadTo(width / 10.toFloat(), 250f, width / 5.toFloat(), 200f)
        path.quadTo(width / 2.toFloat(), 60f, 4 * width / 5.toFloat(), 200f)
        path.quadTo(9 * width / 10.toFloat(), 250f, width.toFloat(), 200f)
        path.lineTo(width.toFloat(), 255f)
        path.lineTo(0f, 255f)
        path.lineTo(0f, 200f)

//        path.cubicTo(100,0,getWidth()-200,0,getWidth(),100);
//        path.cubicTo(200,0,getWidth()-100,0,getWidth(),100);
//        path.rCubicTo(100,0,getWidth()-200,0,getWidth(),100);
//        path.cubicTo(100,500,getWidth()-200,500,getWidth()-100,500);
        //绘制路径
        canvas.drawPath(path, paint!!)
        //绘制辅助点
//        canvas.drawPoint(controlPoint.x,controlPoint.y,paint);
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_MOVE -> {
            }
        }
        return true
    }
}