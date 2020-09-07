package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

public class Activity_Start extends AppCompatActivity {
    private Button start_BTN_play;
    private Button start_BTN_top;
    private RelativeLayout start_LAY_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        findViews();

        start_BTN_play.setOnClickListener(onClickListener);
        start_BTN_top.setOnClickListener(onClickListener);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            if(start_BTN_play.getId() == view.getId()){
                intent = new Intent(Activity_Start.this,Activity_Game.class);
                startActivity(intent);
            }
            if (start_BTN_top.getId() == view.getId()){
                intent = new Intent(Activity_Start.this,Activity_Top10.class);
                startActivity(intent);
            }
        }
    };//send to other activity

    private void findViews() {
        start_BTN_play = findViewById(R.id.start_BTN_play);
        start_BTN_top = findViewById(R.id.start_BTN_top);
        start_LAY_all = findViewById(R.id.start_LAY_all);

        Glide.with(this).load(R.drawable.img_backround_start).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    start_LAY_all.setBackground(resource);
                }
            }
        });

    }
}