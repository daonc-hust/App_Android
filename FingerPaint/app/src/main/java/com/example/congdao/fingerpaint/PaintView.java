package com.example.congdao.fingerpaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class PaintView extends View {

    private static final int COLOR_DEFAULT = Color.BLACK;
    private static final float STROKE_WIDTH_DEFAULT = 16F;

    private Paint paint;
    private int color;
    private float strokeWidth;

    private Path path;
    private ArrayList<MyPath> myPaths;

    private float xTouch;
    private float yTouch;

    public PaintView(Context context) {
        super(context);
        SetUp();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        SetUp();
    }

    public PaintView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        SetUp();
    }

    private void SetUp() {
        color = COLOR_DEFAULT;
        strokeWidth = STROKE_WIDTH_DEFAULT;

        paint = new Paint();
        paint.setAntiAlias(true);  // khử răng cưa => đường vẽ mịn
        paint.setStyle(Paint.Style.STROKE); // kiểu đường viền
        paint.setStrokeCap(Paint.Cap.ROUND); // đầu bút vẽ hình tròn
        paint.setStrokeWidth(strokeWidth);
        //paint.setAlpha(); net mo

        myPaths = new ArrayList<>();
    }

    /* Sự kiện chạm tay vào màn hình */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN: // sự kiện ấn xuống màn hình
                path = new Path();
                path.moveTo(x, y);

                MyPath myPath = new MyPath();
                myPath.setPath(path);
                myPath.setColor(color);
                myPaths.add(myPath);

                xTouch = x;
                yTouch = y;

                invalidate(); //cập nhật sự thay đổi lên UI
                break;
            case MotionEvent.ACTION_MOVE: // sự kiện di tay
                float dX = Math.abs(x - xTouch);
                float dY = Math.abs(y - yTouch);

                if (dX >= 4F || dY >= 4F) {
                    path.quadTo(xTouch, yTouch, x, y);
                    xTouch = x;
                    yTouch = y;
                    invalidate();
                }

                break;
            case MotionEvent.ACTION_UP: //sự kiện nhấc tay ra
                path.lineTo(x, y);
                invalidate();
                break;
            default:
                break;
        }

        return true;
    }

    /* Vẽ các path lên màn hình */
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.drawColor(Color.WHITE);
        for (MyPath myPath : myPaths) {
            paint.setColor(myPath.getColor());
            canvas.drawPath(myPath.getPath(), paint); // Canvas là cái nền , dùng Paint để vẽ các Path lên Canvas
        }

        canvas.restore();
    }

    public void setColor(int color) {
        this.color = color;
        paint.setColor(color);
        invalidate();
    }
}
