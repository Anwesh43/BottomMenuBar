package com.anwesome.ui.bottommenubar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class BottomActionButton extends View {
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private float deg = 0,gap =0;
    private int time = 0;
    public BottomActionButton(Context context) {
        super(context);
    }
    public void onDraw(Canvas canvas) {
        int w = canvas.getWidth(),h = canvas.getHeight();
        if(time == 0) {
            gap = w/3;
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
                canvas.translate(0,(1-i)*gap);
                canvas.drawLine(-w/3,0,w/3,0,paint);
                canvas.restore();
            }
            canvas.restore();
        }
        time++;
    }
}
