package com.example.csc685_mobileproject;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.view.View;

public class RoleActivity extends AppCompatActivity {

    protected ShiftData mDataset[];
    protected ShiftAdapter mAdapter;
    protected RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        // Get the role from the intent...
        Intent intent = getIntent();
        String rolename = intent.getStringExtra(MainActivity.ROLE_INTENT_NAME);
        toolBarLayout.setTitle(rolename);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.role_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShiftAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);
    }


    private void initData() {
        mDataset = new ShiftData[4];
        mDataset[0] = new ShiftData();
        mDataset[0].title = "Line Cook";
        mDataset[0].time = "3:00 - 5:00";
        mDataset[0].description = "Cook food for the early shift. Make sure everything is ready for guests on time.";

        mDataset[1] = new ShiftData();
        mDataset[1].title = "Line Cook";
        mDataset[1].time = "5:00 - 7:00";
        mDataset[1].description = "Cook food for the last part of dinner. Clean up after service is complete.";

        mDataset[2] = new ShiftData();
        mDataset[2].title = "Server";
        mDataset[2].time = "4:00 - 6:00";
        mDataset[2].description = "Serve the guests and make sure they have what they need. Bring orders to the cook.";

        mDataset[3] = new ShiftData();
        mDataset[3].title = "Manager";
        mDataset[3].time = "3:00 - 7:00";
        mDataset[3].description = "Coordinate kitchen and serving activities. Make sure things run smoothly and solve any problems as they arise.";
    }
}