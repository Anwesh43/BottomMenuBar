package com.anwesome.ui.bottommenubardemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.anwesome.ui.bottommenubar.BottomMenuBar;
import com.anwesome.ui.bottommenubar.BottomMenuBarElement;

public class MainActivity extends AppCompatActivity {
    private String options[] = {"Add","Delete","Order","Track"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomMenuBar bottomMenuBar = new BottomMenuBar(this);
        for(int i=0;i<options.length;i++) {
            final  String option = options[i];
            bottomMenuBar.addOption(option, new BottomMenuBarElement.OnMenuClickListener() {
                @Override
                public void onMenuClick() {
                    Toast.makeText(MainActivity.this, option , Toast.LENGTH_SHORT).show();
                }
            });
        }
        bottomMenuBar.show();
    }
}
