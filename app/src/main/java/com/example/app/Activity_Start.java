package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Activity_Start extends AppCompatActivity {
    private Button start_BTN_play;
    private Button start_BTN_top;
    private ImageView start_IMG_backround;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        findViews();
        start_BTN_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goPlay = new Intent(Activity_Start.this , Activity_Game.class);
                startActivity(goPlay);
            }
        });

        start_BTN_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent topTen = new Intent(Activity_Start.this, Activity_Top10.class);
                startActivity(topTen);
            }
        });

    }

    private void findViews() {
        start_BTN_play = findViewById(R.id.start_BTN_play);
        start_BTN_top = findViewById(R.id.start_BTN_top);
        start_IMG_backround = findViewById(R.id.start_IMG_backround);

        Glide
                .with(this)
                .load(R.drawable.img_backround_start)
                .centerCrop()
                .into(start_IMG_backround);

    }
}