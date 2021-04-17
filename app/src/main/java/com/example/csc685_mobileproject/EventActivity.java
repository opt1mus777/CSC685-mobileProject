package com.example.csc685_mobileproject;

import android.os.Bundle;

import com.example.csc685_mobileproject.db.RoleData;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;

public class EventActivity extends AppCompatActivity {

    protected RoleData mDataset[];
    protected RoleAdapter mAdapter;
    protected RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolBarLayout.setTitle(getTitle());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.event_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RoleAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    //update this to roles info
    private void initData() {
        mDataset = new RoleData[3];
        mDataset[0] = new RoleData();
        mDataset[0].title = "Kitchen";
        mDataset[0].description = "Cook food for the early shift. Make sure everything is ready for guests on time.";

        mDataset[1] = new RoleData();
        mDataset[1].title = "Gate";
        mDataset[1].description = "Cook food for the last part of dinner. Clean up after service is complete.";

        mDataset[2] = new RoleData();
        mDataset[2].title = "Cleanup";
        mDataset[2].description = "Serve the guests and make sure they have what they need. Bring orders to the cook.";

        }
}