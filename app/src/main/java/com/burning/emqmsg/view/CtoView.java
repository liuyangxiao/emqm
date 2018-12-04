package com.burning.emqmsg.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by burning on 2018/11/2.
 * When I wrote this, only God and I understood what I was doing
 * Now, God only knows
 * -------------------------//┏┓　　　┏┓
 * -------------------------//┏┛┻━━━┛┻┓
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┃　　　━　　　┃
 * -------------------------//┃　┳┛　┗┳　┃
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┃　　　┻　　　┃
 * -------------------------//┃　　　　　　　┃
 * -------------------------//┗━┓　　　┏━┛
 * -------------------------//┃　　　┃  神兽保佑
 * -------------------------//┃　　　┃  代码无BUG！
 * -------------------------//┃　　　┗━━━┓
 * -------------------------//┃　　　　　　　┣┓
 * -------------------------//┃　　　　　　　┏┛
 * -------------------------//┗┓┓┏━┳┓┏┛
 * -------------------------// ┃┫┫　┃┫┫
 * -------------------------// ┗┻┛　┗┻┛
 */
public class CtoView extends View {
    public CtoView(Context context) {
        super(context);
        test();
    }

    public CtoView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        test();
    }

    public CtoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        test();
    }

    Paint paint;//画笔
    Paint paint2;//画笔2

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CtoView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        test();
    }

    public void test() {
        paint = new Paint();//初始化画笔
        paint.setColor(Color.RED);//设置画笔的颜色
        paint.setTextSize(20);//设置字体的大小
        paint.setAntiAlias(true);//打开抗锯齿
        paint.setStyle(Paint.Style.FILL);//画笔属性是实心圆
        paint.setStrokeWidth(8);//设置画笔粗细
        test2();
    }

    public void test2() {
        paint2 = new Paint();//初始化画笔
        paint2.setColor(Color.WHITE);//设置画笔的颜色
        paint2.setTextSize(40);//设置字体的大小
        paint2.setAntiAlias(true);//打开抗锯齿
        paint2.setStyle(Paint.Style.FILL);//画笔属性是实心圆
        paint2.setStrokeWidth(8);//设置画笔粗细


    }

    /**
     * 绘制 文本居中
     *
     * @param canvas
     */
    private void drawMyText(Canvas canvas, Point point) {
        canvas.drawCircle(point.x + getWidth() / 2, point.y + getHeight() / 2, getWidth() / 2, paint);
        Rect bounds = new Rect();
        paint2.getTextBounds(text, 0, text.length(), bounds);
        Paint.FontMetricsInt fontMetrics = paint2.getFontMetricsInt();
        int baseline = (getMeasuredHeight() - fontMetrics.bottom + fontMetrics.top) / 2 - fontMetrics.top;
        float textwidth = paint2.measureText(text);
        canvas.drawText(text, point.x + (getWidth() / 2 - textwidth / 2), point.y + baseline, paint2);
    }

    String text = "1";

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawMyCtext(canvas);
        drawMyText(canvas, movePoint);
        drawMyLine(canvas);
    }

    Point movePoint = new Point(100, 200);
    private void drawMyCtext(Canvas canvas) {
        drawMyText(canvas, new Point());
    }

    private void drawMyLine(Canvas canvas) {
/*        Path path2 = new Path();
        path2.moveTo();//设置Path的起点
        path2.quadTo(getWidth() + 100 / 2, getHeight() + 200 / 2, 100, 200 + getHeight() / 2); //设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(path2, paint);//画出贝塞尔曲线
        Path path3 = new Path();
        path3.moveTo(2 * getWidth(), getHeight() / 2);//设置Path的起点
        path3.quadTo(getWidth() + 100 / 2, getHeight() + 200 / 2, 2 * getWidth() + 100, 200 + getHeight() / 2); //设置贝塞尔曲线的控制点坐标和终点坐标
        canvas.drawPath(path3, paint);//画出贝塞尔曲线*/
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
