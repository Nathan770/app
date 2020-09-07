package com.example.app;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Activity_Top10 extends AppCompatActivity implements OnMapReadyCallback {
    private RecyclerView top10_RCV_all;
    private RecyclerView.Adapter top10_RCV_adapter;
    private RecyclerView.LayoutManager top10_RCV_manager;
    public ArrayList<ItemData> itemDataList;
    private MapView top10_MPV_map;
    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private FusedLocationProviderClient fusedLocationProviderClient;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top10);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        findViews(savedInstanceState);
        addTolist();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void findViews(Bundle savedInstanceState) {
        itemDataList = new ArrayList<>();

        itemDataList.add(new ItemData(R.drawable.ic_fisrt, "0", "long", "lat"));
        itemDataList.add(new ItemData(R.drawable.ic_second, "0", "long", "lat"));
        itemDataList.add(new ItemData(R.drawable.ic_third, "0", "long", "lat"));
        itemDataList.add(new ItemData(R.drawable.ic_four, "0", "long", "lat"));
        itemDataList.add(new ItemData(R.drawable.ic_five, "0", "long", "lat"));
        itemDataList.add(new ItemData(R.drawable.ic_six, "0", "long", "lat"));
        itemDataList.add(new ItemData(R.drawable.ic_seven, "0", "long", "lat"));
        itemDataList.add(new ItemData(R.drawable.ic_eight, "0", "long", "lat"));
        itemDataList.add(new ItemData(R.drawable.ic_nine, "0", "long", "lat"));
        itemDataList.add(new ItemData(R.drawable.ic_ten, "0", "49.267131199999994", "1.2629656999999999"));

        top10_RCV_all = findViewById(R.id.top10_RCV_winnerData);
        top10_RCV_all.setHasFixedSize(true);
        top10_RCV_manager = new LinearLayoutManager(this);
        top10_RCV_adapter = new RecycleAdapter(itemDataList);
        top10_RCV_all.setLayoutManager(top10_RCV_manager);
        top10_RCV_all.setAdapter(top10_RCV_adapter);


        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }

        top10_MPV_map = findViewById(R.id.top10_MPV_map);
        top10_MPV_map.onCreate(mapViewBundle);
        top10_MPV_map.getMapAsync(this);

    }

    private void addTolist() {
        Gson gson = new Gson();
        SharedPreferences pref = this.getSharedPreferences("MY_SP", MODE_PRIVATE);

        String dataString = pref.getString("data", "");
        if (!dataString.contentEquals("")) {
            ItemData itemMain = gson.fromJson(dataString, ItemData.class);
            checkToList(itemMain);
        }

        String dataRan = pref.getString("random", "");
        if (!dataRan.contentEquals("")) {
            ItemData itemRan = gson.fromJson(dataRan, ItemData.class);
            checkToList(itemRan);
        }

        String dataPlay = pref.getString("player", "");
        if (!dataPlay.contentEquals("")) {
            ItemData itemRan = gson.fromJson(dataPlay, ItemData.class);
            checkToList(itemRan);
        }


    }//add winner into a list

    private void checkToList(ItemData itemMain) {
        int index = 0;
        for (ItemData i : itemDataList){
            int loc = Integer.parseInt(i.getscore().toString());
            int var = Integer.parseInt(itemMain.getscore().toString());
            if(var < 10 && loc == 0){
                itemMain.setimagePosition(i.getimagePosition());
                itemDataList.add(index,itemMain);
                /*
                Collections.sort(itemDataList, new Comparator<ItemData>() {
                    @Override
                    public int compare(ItemData t0, ItemData t1) {
                        int loc = Integer.parseInt(t0.getscore().toString());
                        int var = Integer.parseInt(t1.getscore().toString());
                        return var - loc;
                    }
                });
                 */
                break;
            }
            index++;
        }

    }// sort list and check if is sorted

    @Override
    protected void onStart() {
        super.onStart();
        top10_MPV_map.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        top10_MPV_map.onStop();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        int count = 0;
        for (ItemData i : itemDataList){
            count++;
            if(!i.getscore().equals("0")){
                double la = Double.parseDouble(i.getxText().toString());
                double ln = Double.parseDouble(i.getyText().toString());
                googleMap.addMarker(new MarkerOptions().position(new LatLng(la, ln)).title("winner "+count));
            }
        }



    }// enter the point into a map

    @Override
    protected void onPause() {
        top10_MPV_map.onPause();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        top10_MPV_map.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        top10_MPV_map.onLowMemory();
    }
}