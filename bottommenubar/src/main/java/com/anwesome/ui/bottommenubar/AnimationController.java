package com.anwesome.ui.bottommenubar;

import android.animation.Animator;
import android.animation.ValueAnimator;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class AnimationController implements ValueAnimator.AnimatorUpdateListener,Animator.AnimatorListener {
    private BottomActionButton bottomActionButton;
    private BottomMenuBarView menuBarView;
    private int dir = -1;
    private float w,h;
    public AnimationController(BottomActionButton bottomActionButton,BottomMenuBarView menuBarView,float w,float h) {
        this.menuBarView = menuBarView;
        this.bottomActionButton = bottomActionButton;
        this.h = h;
        this.w = w;
    }
    public void onAnimationUpdate(ValueAnimator valueAnimator) {
        if(dir == 1) {
            bottomActionButton.changeShape(valueAnimator.getAnimatedFraction());
        }
        else if(dir == -1) {
            bottomActionButton.changeShape(1-valueAnimator.getAnimatedFraction());
        }
        menuBarView.setY((float)valueAnimator.getAnimatedValue());

        if(dir == 1) {
            menuBarView.setScaleX(valueAnimator.getAnimatedFraction());
            menuBarView.setScaleY(valueAnimator.getAnimatedFraction());
            menuBarView.setX(w - menuBarView.getMeasuredWidth()*valueAnimator.getAnimatedFraction());
        }
        else if(dir == -1) {
            menuBarView.setScaleX(1-valueAnimator.getAnimatedFraction());
            menuBarView.setScaleY(1-valueAnimator.getAnimatedFraction());
            menuBarView.setX(w - menuBarView.getMeasuredWidth()*(1-valueAnimator.getAnimatedFraction()));
        }

    }
    public void onAnimationEnd(Animator animator) {
    }
    public void onAnimationCancel(Animator animator) {

    }
    public void onAnimationStart(Animator animator) {

    }
    public void onAnimationRepeat(Animator animator) {

    }
    public void toggle() {
        float origY = 9*h/10-h/20;
        ValueAnimator valueAnimator = null;
        dir*=-1;
        if(dir == 1) {
            valueAnimator = ValueAnimator.ofFloat(origY,origY-menuBarView.getMeasuredHeight());

        }
        else if(dir == -1) {
            valueAnimator = ValueAnimator.ofFloat(origY-menuBarView.getMeasuredHeight(),origY);
        }
        valueAnimator.setDuration(500);
        valueAnimator.addUpdateListener(this);
        valueAnimator.addListener(this);
        valueAnimator.start();
    }
}
