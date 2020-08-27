package com.example.app;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.pm.ActivityInfo;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    private Button main_BTN_1L;
    private Button main_BTN_2L;
    private Button main_BTN_3L;
    private Button main_BTN_1R;
    private Button main_BTN_2R;
    private Button main_BTN_3R;
    private ProgressBar main_LPB_leftbar;
    private ProgressBar main_LPB_rightbar;
    private ImageView main_IMG_right;
    private ImageView main_IMG_left;
    
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        findViews();
        buttonAction();

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void buttonAction() {

        View.OnClickListener buttomClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttomClick(view);
            }
        };

        main_BTN_1L.setOnClickListener(buttomClickListener);
        main_BTN_2L.setOnClickListener(buttomClickListener);
        main_BTN_3L.setOnClickListener(buttomClickListener);

        main_BTN_1R.setOnClickListener(buttomClickListener);
        main_BTN_2R.setOnClickListener(buttomClickListener);
        main_BTN_3R.setOnClickListener(buttomClickListener);
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void buttomClick(View view) {

        if (main_BTN_1R.getId() == view.getId()) {
            main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 10);

        } else if (main_BTN_2R.getId() == view.getId()) {
            main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 20);

        } else if (main_BTN_3R.getId() == view.getId()) {
            main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 30);

        }

        if (main_BTN_1L.getId() == view.getId()) {
            main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 10);

        } else if (main_BTN_2L.getId() == view.getId()) {
            main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 20);

        } else if (main_BTN_3L.getId() == view.getId()) {
            main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 30);
        }

        if ((view.getId() == main_BTN_1L.getId()) || (view.getId() == main_BTN_2L.getId()) || (view.getId() == main_BTN_3L.getId())) {
            changePlayerLeft();
        } else {
            changePlayerRight();
        }

        checkContinue();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkContinue() {

        if (main_LPB_leftbar.getProgress() <= 30) {
            main_LPB_leftbar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

        if (main_LPB_rightbar.getProgress() <= 30) {
            main_LPB_rightbar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

        if (main_LPB_leftbar.getProgress() == 0) {
            Toast.makeText(this, "Liel sleep", Toast.LENGTH_LONG).show();
            stopApp();
        }

        if (main_LPB_rightbar.getProgress() == 0) {
            Toast.makeText(this, "Nathan sleep", Toast.LENGTH_LONG).show();
            stopApp();
        }
    }

    private void changePlayerRight() {

        main_BTN_1R.setClickable(false);
        main_BTN_2R.setClickable(false);
        main_BTN_3R.setClickable(false);

        main_BTN_1R.setBackgroundColor(Color.GRAY);
        main_BTN_2R.setBackgroundColor(Color.GRAY);
        main_BTN_3R.setBackgroundColor(Color.GRAY);

        main_BTN_1L.setClickable(true);
        main_BTN_2L.setClickable(true);
        main_BTN_3L.setClickable(true);

        main_BTN_1L.setBackgroundColor(Color.BLUE);
        main_BTN_2L.setBackgroundColor(Color.BLUE);
        main_BTN_3L.setBackgroundColor(Color.BLUE);

    }

    private void stopApp() {

        main_BTN_1R.setClickable(false);
        main_BTN_2R.setClickable(false);
        main_BTN_3R.setClickable(false);

        main_BTN_1L.setClickable(false);
        main_BTN_2L.setClickable(false);
        main_BTN_3L.setClickable(false);

    }

    private void changePlayerLeft() {

        main_BTN_1L.setClickable(false);
        main_BTN_2L.setClickable(false);
        main_BTN_3L.setClickable(false);

        main_BTN_1L.setBackgroundColor(Color.GRAY);
        main_BTN_2L.setBackgroundColor(Color.GRAY);
        main_BTN_3L.setBackgroundColor(Color.GRAY);

        main_BTN_1R.setClickable(true);
        main_BTN_2R.setClickable(true);
        main_BTN_3R.setClickable(true);

        main_BTN_1R.setBackgroundColor(Color.BLUE);
        main_BTN_2R.setBackgroundColor(Color.BLUE);
        main_BTN_3R.setBackgroundColor(Color.BLUE);

    }

    private void findViews() {

        main_BTN_1L = findViewById(R.id.main_BTN_1L);
        main_BTN_2L = findViewById(R.id.main_BTN_2L);
        main_BTN_3L = findViewById(R.id.main_BTN_3L);
        main_BTN_1R = findViewById(R.id.main_BTN_1R);
        main_BTN_2R = findViewById(R.id.main_BTN_2R);
        main_BTN_3R = findViewById(R.id.main_BTN_3R);

        main_LPB_leftbar = findViewById(R.id.main_LPB_leftbar);
        main_LPB_rightbar = findViewById(R.id.main_LPB_rightbar);

        main_IMG_left = findViewById(R.id.main_IMG_left);
        main_IMG_right = findViewById(R.id.main_IMG_right);

        main_LPB_rightbar.setProgress(100);
        main_LPB_leftbar.setProgress(100);

        Glide
                .with(this)
                .load(R.drawable.img_liel)
                .centerCrop()
                .into(main_IMG_left);

        Glide
                .with(this)
                .load(R.drawable.img_nathan)
                .centerCrop()
                .into(main_IMG_right);
    }

}
