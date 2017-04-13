package com.anwesome.ui.bottommenubar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class BottomMenuBarElement {
    private String option;
    private float y,w,h,scale = 0,dir = 0;
    private OnMenuClickListener onMenuClickListener;
    public BottomMenuBarElement(String option,OnMenuClickListener onMenuClickListener) {
        this.onMenuClickListener = onMenuClickListener;
        this.option = option;
    }
    public void setDimension(float y,float w,float h) {
        this.y = y;
        this.w = w;
        this.h = h;
    }
    public void draw(Canvas canvas, Paint paint) {
        canvas.save();
        canvas.translate(w/2,h/2);
        paint.setColor(BottomMenuBarConstants.viewColor);
        canvas.drawRect(new RectF(-w/2,-h/2,w/2,h/2),paint);
        paint.setTextSize(h/3);
        canvas.save();
        int color = BottomMenuBarConstants.viewColor;
        paint.setColor(Color.argb(100,Color.red(color),Color.green(color),Color.blue(color)));
        canvas.scale(scale,scale);
        canvas.drawRect(new RectF(-w/2,-h/2,w/2,h/2),paint);
        canvas.restore();
        paint.setColor(Color.WHITE);
        String newOptionStr = adjustedString(paint);
        canvas.drawText(newOptionStr,-paint.measureText(newOptionStr)/2,paint.getTextSize()/2,paint);
        canvas.restore();
    }
    public String adjustedString(Paint paint) {
        String msg = "";
        for(int i=0;i<option.length();i++) {
            if(paint.measureText(msg+option.charAt(i))>3*w/4) {
                msg += "...";
            }
            else {
                msg += option.charAt(i);
            }
        }
        return msg;
    }
    public boolean handleTap(float x,float y) {
        boolean condition = x>=0 && x<=w && y>=this.y && y<=this.y+h && dir == 0;
        if(condition) {
            dir = 1;
        }
        return condition;
    }
    public void update() {
        scale+=dir*0.2f;
        if(scale>=1.4f) {
            dir = 0;
            scale = 0;
            if(onMenuClickListener!=null) {
                onMenuClickListener.onMenuClick();
            }
        }
    }
    public boolean stop() {
        return dir == 0;
    }
    public interface OnMenuClickListener {
        void onMenuClick();
    }
}
