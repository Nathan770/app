package com.example.app;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.util.Random;


public class MainActivity extends AppCompatActivity{
    public static final String TAG = "nathan";
    private RelativeLayout main_LAY_all;
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
    private int counter = 0 , gameNum = 0;
    private SharedPreferences.Editor mySP;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location mLastKnownLocation;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        Log.d(TAG, "onCreate: mainActivity");
        findViews();
        gameNum = getIntent().getIntExtra("game", 0);

        if (gameNum == 3) {
            runingGame3();
        } else if (gameNum == 2) {
            runingGame2();
        } else if (gameNum == 1) {
            runingGame1();
        }
    }

    private void runingGame1() {
        boolean turn = true;
        while (checkContinue()) {
            if (turn) {
                randPlayerRight();
                turn = false;
            } else {
                randPlayerLeft();
                turn = true;
            }
            counter++;
        }
    }//random vs random

    private void runingGame2() {
        PlayerLeft();
    }//player vs random

    private void runingGame3() {
        PlayerLeft();
        PlayerRight();
    }//player vs player

    public void findViews() {

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

        main_LAY_all = findViewById(R.id.main_LAY_all);

        Glide.with(this).load(R.drawable.img_backround_start).into(new SimpleTarget<Drawable>() {
            @Override
            public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    main_LAY_all.setBackground(resource);
                }
            }
        });

        mySP = getSharedPreferences("MY_SP", MODE_PRIVATE).edit();

    }

    public void randPlayerLeft(){
        if (checkContinue()) {
            int randNumPlayer = new Random().nextInt(3);
            if (randNumPlayer == 0) {
                main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 10);
            } else if (randNumPlayer == 1) {
                main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 20);
            } else if (randNumPlayer == 2) {
                main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 30);
            }
            changePlayerLeft();
            changeColor();
        }else {
            stopApp();
            getDeviceLocation();
        }
    }//random play like regular player

    public void randPlayerRight(){
        if (checkContinue()) {
            int randNumPlayer = new Random().nextInt(3);
            if (randNumPlayer == 0) {
                main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 10);
            } else if (randNumPlayer == 1) {
                main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 20);
            } else if (randNumPlayer == 2) {
                main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 30);
            }
            changePlayerRight();
            changeColor();
        }else {
            stopApp();
            getDeviceLocation();
        }
    }//random play like regular player

    public void PlayerLeft() {
        main_BTN_1L.setOnClickListener(ButtomClickListenerLeft);
        main_BTN_2L.setOnClickListener(ButtomClickListenerLeft);
        main_BTN_3L.setOnClickListener(ButtomClickListenerLeft);
    }

    View.OnClickListener ButtomClickListenerLeft = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (main_BTN_1L.getId() == view.getId()) {
                main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 10);

            } else if (main_BTN_2L.getId() == view.getId()) {
                main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 20);

            } else if (main_BTN_3L.getId() == view.getId()) {
                main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 30);
            }
            changePlayerLeft();
            changeColor();
            counter++;
            if (!checkContinue()) {
                stopApp();
                getDeviceLocation();
            }
            if(gameNum == 2) {
                randPlayerRight();
            }
        }
    };

    public void changePlayerLeft() {

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

    }//change player

    public void PlayerRight() {
        main_BTN_1R.setOnClickListener(buttomClickListenerRight);
        main_BTN_2R.setOnClickListener(buttomClickListenerRight);
        main_BTN_3R.setOnClickListener(buttomClickListenerRight);
    }

    View.OnClickListener buttomClickListenerRight = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (main_BTN_1R.getId() == view.getId()) {
                main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 10);

            } else if (main_BTN_2R.getId() == view.getId()) {
                main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 20);

            } else if (main_BTN_3R.getId() == view.getId()) {
                main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 30);
            }
            changePlayerRight();
            changeColor();
            counter++;
            if (!checkContinue()) {
                stopApp();
                getDeviceLocation();
            }

        }
    };

    public void changePlayerRight() {

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

    }//change player

    public void changeColor() {

        if (main_LPB_leftbar.getProgress() <= 30) {
            main_LPB_leftbar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

        if (main_LPB_rightbar.getProgress() <= 30) {
            main_LPB_rightbar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

    } //Check if need change color to red

    public boolean checkContinue() {

        if (main_LPB_leftbar.getProgress() == 0) {
            Toast.makeText(this, "Liel sleep", Toast.LENGTH_LONG).show();
            return false;
        }

        if (main_LPB_rightbar.getProgress() == 0) {
            Toast.makeText(this, "Nathan sleep", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }//return false if the games is finish

    public void stopApp() {

        main_BTN_1R.setClickable(false);
        main_BTN_2R.setClickable(false);
        main_BTN_3R.setClickable(false);

        main_BTN_1L.setClickable(false);
        main_BTN_2L.setClickable(false);
        main_BTN_3L.setClickable(false);

    }// disable all the game

    public void getDeviceLocation() {
        Log.d("nathan", "getDeviceLocation: Getting users location");
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        if (getApplicationContext().checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
                @Override
                public void onComplete(@NonNull Task<Location> task) {
                    if (task.isSuccessful()) {
                        mLastKnownLocation = task.getResult();
                        if (mLastKnownLocation != null) {
                            Log.d("nathan", "onComplete: Result is not null");
                            Double lat = mLastKnownLocation.getLatitude();
                            Double lon = mLastKnownLocation.getLongitude();
                            sendTop10(lat, lon);
                        } else {
                            Log.d("nathan", "onComplete: Result is null, requesting location update");
                        }
                    }
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.d(TAG, "onFailure: " + e.getMessage());
                }
            });
        } else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            getDeviceLocation();
        }
    }//get location and send to top 10

    public void sendTop10(double lat, double lon) {
        Gson gson = new Gson();
        String json = gson.toJson(new ItemData(R.drawable.ic_fisrt, "" + ((counter / 2) + 1), "" + lat, "" + lon));
        if(gameNum == 3){
            mySP.putString("data", json);
            mySP.apply();
        }else if(gameNum == 2){
            mySP.putString("player", json);
            mySP.apply();
        }else if(gameNum == 1){
            mySP.putString("random", json);
            mySP.apply();
        }

    }

}
