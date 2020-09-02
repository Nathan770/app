package com.example.app;

import android.Manifest;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

public class Random_game extends AppCompatActivity {
    public static final String TAG = "nathan";
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
    private ImageView main_IMG_backround;
    public ArrayList<ItemData> itemDataList;
    private SharedPreferences.Editor mySP;
    private int counter =0;

    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location mLastKnownLocation;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        findViews();
        games();

    }

    private void games() {
        int randNumPlayer = 0;
        boolean turn = true ,nextHand = true ;
        while (nextHand){
            randNumPlayer = new Random().nextInt(3);
            if (turn) {
                player1();
                turn = false;
            }else {
                player2();
                turn = true;
            }
            counter++;
            checkContinue();
            nextHand = nextHandCheck();
        }
    }

    private boolean nextHandCheck() {
        if (main_LPB_leftbar.getProgress() == 0) {
            return false;
        }else if(main_LPB_rightbar.getProgress() == 0){
            return false;
        }
        return true;
    }

    private void player2() {
        int randNumPlayer = new Random().nextInt(3);
        if(randNumPlayer == 0){
            main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 10);
        }else if(randNumPlayer == 1){
            main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 20);
        }else if(randNumPlayer == 2){
            main_LPB_rightbar.setProgress(main_LPB_rightbar.getProgress() - 30);
        }
        changePlayerLeft();
    }

    private void player1() {
        int randNumPlayer = new Random().nextInt(3);
        if(randNumPlayer == 0){
            main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 10);
        }else if(randNumPlayer == 1){
            main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 20);
        }else if(randNumPlayer == 2){
            main_LPB_leftbar.setProgress(main_LPB_leftbar.getProgress() - 30);
        }
        changePlayerRight();
    }

    private void changeColor() {

        if (main_LPB_leftbar.getProgress() <= 30) {
            main_LPB_leftbar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

        if (main_LPB_rightbar.getProgress() <= 30) {
            main_LPB_rightbar.setProgressTintList(ColorStateList.valueOf(Color.RED));
        }

    }

    private void stopApp() {

        main_BTN_1R.setClickable(false);
        main_BTN_2R.setClickable(false);
        main_BTN_3R.setClickable(false);

        main_BTN_1L.setClickable(false);
        main_BTN_2L.setClickable(false);
        main_BTN_3L.setClickable(false);

    }

    private void getDeviceLocation() {
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
                            sendTop10(lat,lon);
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
        }else {
            requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 1);
            getDeviceLocation();
        }
    }

    private void sendTop10(Double lat,Double lon) {
        Gson gson = new Gson();
        String json = gson.toJson(new ItemData(R.drawable.ic_second,"Score : "+((counter/2)+1),""+lat,""+lon));
        mySP.putString("random",json);
        mySP.apply();
    }

    private void checkContinue() {

        changeColor();

        if (main_LPB_leftbar.getProgress() == 0) {
            Toast.makeText(this, "Liel sleep", Toast.LENGTH_LONG).show();
            stopApp();
            getDeviceLocation();
        }

        if (main_LPB_rightbar.getProgress() == 0) {
            Toast.makeText(this, "Nathan sleep", Toast.LENGTH_LONG).show();
            stopApp();
            getDeviceLocation();
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

    private void findViews(){

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
    main_IMG_backround = findViewById(R.id.main_IMG_backround);

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

        Glide
                .with(this)
                .load(R.drawable.img_backround_start)
                .centerCrop()
                .into(main_IMG_backround);


    mySP = getSharedPreferences("MY_SP", MODE_PRIVATE).edit();
 }


}
