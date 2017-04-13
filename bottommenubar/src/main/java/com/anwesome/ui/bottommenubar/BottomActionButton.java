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
    private float deg = 0,gap =0;
    private int time = 0;
    private boolean opened = false;
    private OnButtonClickListener onButtonClickListener;
    private AnimationQueue animationQueue = new AnimationQueue();
    public BottomActionButton(Context context) {
        super(context);
    }
    public void setOnButtonClickListener(OnButtonClickListener onButtonClickListener) {
        this.onButtonClickListener = onButtonClickListener;
    }
    public void onDraw(Canvas canvas) {
        final int w = canvas.getWidth(),h = canvas.getHeight();
        if(time == 0) {
            gap = w/4;
            animationQueue.addAnimationHandler(new AnimationHandler() {
                @Override
                public void onAnimate(int dir) {
                    gap-=dir*w/20;

                }

                @Override
                public boolean isDone() {
                    return (gap>=w/4 || gap<=0);
                }
            });
            animationQueue.addAnimationHandler(new AnimationHandler() {
                @Override
                public void onAnimate(int dir) {
                    deg+=dir*9;
                }

                @Override
                public boolean isDone() {
                    return (deg>=45 || deg<=0);
                }
            });
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
                canvas.translate(0,(i-1)*gap);
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
    public void opening() {
        if(!opened) {
            animationQueue.animate(1);
            postInvalidate();
        }
    }
    public void reset() {
        animationQueue.reset();
    }
    public void closing() {
        if(opened) {
            animationQueue.animate(1);
            postInvalidate();
        }
    }
    private interface AnimationHandler {
        void onAnimate(int dir);
        boolean isDone();
    }
    private class AnimationQueue {
        private int index = 0;
        private boolean done = false;
        private List<AnimationHandler> animationHandlers = new ArrayList<>();
        public void addAnimationHandler(AnimationHandler animationHandler) {
            this.animationHandlers.add(animationHandler);
        }
        public void reset() {
            done = false;
        }
        public void animate(int dir) {
            if(!done && (dir == -1 && index >= 0) || (dir == 1&& index<animationHandlers.size())) {
                AnimationHandler animationHandler = animationHandlers.get(index);
                animationHandler.onAnimate(dir);
                if(animationHandler.isDone()) {
                    index += dir;
                    if(index<0 && dir == -1) {
                        index = 0;
                        done = true;
                    }
                    if(index>=animationHandlers.size() && dir == 1) {
                        index = animationHandlers.size()-1;
                        done = true;
                    }
                }
            }
        }
    }
    public interface OnButtonClickListener {
        void onButtonClick();
    }
}
