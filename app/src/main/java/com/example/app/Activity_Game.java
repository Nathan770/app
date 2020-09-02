package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class Activity_Game extends AppCompatActivity {
    private Button game_BTN_solo;
    private Button game_BTN_auto;
    private Button game_BTN_ran;
    private ImageView game_IMG_backround;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        findViews();
        game_BTN_solo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goPlay = new Intent(Activity_Game.this , MainActivity.class);
                startActivity(goPlay);
            }
        });

        game_BTN_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goPlay = new Intent(Activity_Game.this , Random_game.class);
                startActivity(goPlay);
            }
        });

        game_BTN_ran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goPlay = new Intent(Activity_Game.this , Player_VS_Random.class);
                startActivity(goPlay);
            }
        });

    }

    private void findViews() {
        game_BTN_solo = findViewById(R.id.game_BTN_solo);
        game_BTN_auto = findViewById(R.id.game_BTN_auto);
        game_BTN_ran = findViewById(R.id.game_BTN_ran);

        game_IMG_backround = findViewById(R.id.game_IMG_backround);

        Glide
                .with(this)
                .load(R.drawable.img_backround_start)
                .centerCrop()
                .into(game_IMG_backround);

    }
}