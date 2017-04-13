package com.anwesome.ui.bottommenubar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class BottomActionButton extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float deg = 0,gap =0,maxDeg = 45,maxGap = 0,gap_factor = 5;
    private int time = 0;
    private boolean opened = false;
    private OnButtonClickListener onButtonClickListener;
    public BottomActionButton(Context context) {
        super(context);
    }
    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }
    public void onDraw(Canvas canvas) {
        final int w = canvas.getWidth(),h = canvas.getHeight();
        if(time == 0) {
            gap = w/gap_factor;
            maxGap = gap;
        }
        paint.setColor(BottomMenuBarConstants.viewColor);
        canvas.drawCircle(w/2,h/2,w/2,paint);
        paint.setStrokeWidth(w/30);
        int rotDir[] = {1,-1};
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(w/10);
        for(int i=0;i<2;i++) {
            canvas.save();
            canvas.translate(w/2,h/2);
            canvas.rotate(deg*rotDir[i]);
            for(int j=0;j<3;j++) {
                canvas.save();
                canvas.translate(0,(j-1)*gap);
                canvas.drawLine(-w/4,0,w/4,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        time++;
    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN && onButtonClickListener!=null) {
            onButtonClickListener.onButtonClick();
        }
        return true;
    }
    public void changeShape(float animfactor) {
        if(animfactor<=0.5f) {
            gap = maxGap*((0.5f-animfactor)/0.5f);
        }
        else {
            deg = maxDeg*((animfactor-0.5f)/0.5f);
        }
        postInvalidate();
    }
    public interface OnButtonClickListener {
        void onButtonClick();
    }
}
