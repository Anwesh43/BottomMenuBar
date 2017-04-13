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
                activity.addContentView(bottomMenuBarView,new ViewGroup.LayoutParams(w/2,h/20*(bottomMenuBarElementList.size())));
                activity.addContentView(bottomActionButton,new ViewGroup.LayoutParams(w/10,w/10));
                bottomActionButton.setX(9*w/10);
                bottomActionButton.setY(9*h/10);
                bottomMenuBarView.setX(9*w/10-w/20);
                bottomMenuBarView.setY(9*h/10-h/20);
                bottomMenuBarView.setScaleX(0);
                bottomMenuBarView.setScaleY(0);
            }
        }
    }
}
