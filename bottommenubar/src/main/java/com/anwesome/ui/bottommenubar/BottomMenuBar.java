package com.anwesome.ui.bottommenubar;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anweshmishra on 13/04/17.
 */
public class BottomMenuBar {
    private Activity activity;
    private BottomActionButton bottomActionButton;
    private BottomMenuBarView bottomMenuBarView;
    private boolean isShown = false;
    private AnimationController animationController;
    private List<BottomMenuBarElement> bottomMenuBarElementList = new ArrayList<>();
    public BottomMenuBar(Activity activity) {
        this.activity = activity;
        bottomActionButton = new BottomActionButton(activity);
    }
    public void addOption(String option, BottomMenuBarElement.OnMenuClickListener onMenuClickListener) {
        if(!isShown) {
            bottomMenuBarElementList.add(new BottomMenuBarElement(option, onMenuClickListener));
        }
    }
    public void show() {
        if(!isShown) {
            bottomMenuBarView = new BottomMenuBarView(activity,bottomMenuBarElementList);
            DisplayManager displayManager = (DisplayManager)activity.getSystemService(Context.DISPLAY_SERVICE);
            Display display = displayManager.getDisplay(0);
            if(display!=null) {
                Point size = new Point();
                display.getRealSize(size);
                isShown = true;
                int w = size.x,h = size.y*9/10;
                activity.addContentView(bottomMenuBarView,new ViewGroup.LayoutParams(Math.min(w,h)/2,Math.max(w,h)/14*(bottomMenuBarElementList.size())));
                activity.addContentView(bottomActionButton,new ViewGroup.LayoutParams(w/8,w/8));
                bottomActionButton.setX(w-w/8);
                if(w>h) {
                    bottomActionButton.setY(4*h/5-w/16);
                    bottomMenuBarView.setY(4*h/5-h/20);
                }
                else {
                    bottomActionButton.setY(9 * h / 10 - w / 16);
                    bottomMenuBarView.setY(9*h/10-h/20);
                }
                bottomMenuBarView.setPivotX(bottomMenuBarView.getMeasuredWidth());
                bottomMenuBarView.setPivotY(bottomMenuBarView.getMeasuredHeight());
                bottomMenuBarView.setX(9*w/10-w/20);
                bottomMenuBarView.setScaleX(0);
                bottomMenuBarView.setScaleY(0);
                animationController = new AnimationController(bottomActionButton,bottomMenuBarView,w,h);
                bottomActionButton.setOnButtonClickListener(new BottomActionButton.OnButtonClickListener() {
                    @Override
                    public void onButtonClick() {
                        animationController.toggle();
                    }
                });
            }
        }
    }
}
