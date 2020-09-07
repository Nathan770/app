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

public class Activity_Game extends AppCompatActivity {
    private Button game_BTN_solo;
    private Button game_BTN_auto;
    private Button game_BTN_ran;
    private RelativeLayout game_LAY_all;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        findViews();

        game_BTN_solo.setOnClickListener(onClickListener);

        game_BTN_auto.setOnClickListener(onClickListener);

        game_BTN_ran.setOnClickListener(onClickListener);

    }

    private void findViews() {
        game_BTN_solo = findViewById(R.id.game_BTN_solo);
        game_BTN_auto = findViewById(R.id.game_BTN_auto);
        game_BTN_ran = findViewById(R.id.game_BTN_ran);

        game_LAY_all = findViewById(R.id.game_LAY_all);

        Glide.with(this).load(R.drawable.img_backround_start).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    game_LAY_all.setBackground(resource);
                }
            }
        });

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent;
            intent = new Intent(Activity_Game.this,MainActivity.class);
            if(game_BTN_auto.getId() == view.getId()){
                intent.putExtra("game",1);
                startActivity(intent);
            }
            if (game_BTN_ran.getId() == view.getId()){
                intent.putExtra("game",2);
                startActivity(intent);
            }
            if (game_BTN_solo.getId() == view.getId()){
                intent.putExtra("game",3);
                startActivity(intent);
            }
        }//send to other activity
    };
}