package com.anwesome.ui.bottommenubar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class BottomMenuBarView extends View {
    private boolean isAnimated = false;
    private int time = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private List<BottomMenuBarElement> bottomMenuBarElements = new ArrayList<>();
    private ConcurrentLinkedQueue<BottomMenuBarElement> elements = new ConcurrentLinkedQueue<>();
    public BottomMenuBarView(Context context,List<BottomMenuBarElement> bottomMenuBarElements) {
        super(context);
        this.bottomMenuBarElements = bottomMenuBarElements;
    }
    public void onDraw(Canvas canvas) {

        for(BottomMenuBarElement bottomMenuBarElement:bottomMenuBarElements) {
            bottomMenuBarElement.draw(canvas,paint);
        }
        if(isAnimated) {
            for(BottomMenuBarElement element:elements) {
                element.update();
                if(element.stop()) {
                    elements.remove(element);
                    if(elements.size() == 0) {
                        isAnimated = false;
                    }
                }
            }
            try {
                Thread.sleep(50);
                invalidate();
            }
            catch (Exception ex) {

            }
        }

    }
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction() == MotionEvent.ACTION_DOWN) {
            for(BottomMenuBarElement bottomMenuBarElement:bottomMenuBarElements) {
                if(bottomMenuBarElement.handleTap(event.getX(),event.getY())) {
                    elements.add(bottomMenuBarElement);
                    if(!isAnimated) {
                        isAnimated = true;
                        postInvalidate();
                    }
                }
            }
        }
        return true;
    }
}
