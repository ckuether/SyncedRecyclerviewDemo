package com.example.corey.syncedrecyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    SyncedRVContainer syncedRVContainer;
    RecyclerView topRV, bottomRV;
    RVAdapter topAdapter, bottomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init(){
        syncedRVContainer = (SyncedRVContainer) findViewById(R.id.syncedRVContainer);
        syncedRVContainer.init();
        topRV = (RecyclerView) findViewById(R.id.topRV);
        bottomRV = (RecyclerView) findViewById(R.id.bottomRV);

        ArrayList<Integer> rv_items_top = new ArrayList<>();
        ArrayList<Integer> rv_items_bottom = new ArrayList<>();
        for(int i = 0; i < 5; i++){
            rv_items_top.add(i+1);
            rv_items_bottom.add(5-i);
        }

        topAdapter = new RVAdapter(this, rv_items_top, true);
        bottomAdapter = new RVAdapter(this, rv_items_bottom, false);

        topRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        bottomRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        topRV.setAdapter(topAdapter);
        bottomRV.setAdapter(bottomAdapter);

    }
}
