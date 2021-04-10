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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

        initData(rolename);
        mRecyclerView = (RecyclerView) findViewById(R.id.role_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShiftAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

    }


    private void initData(String role) {
        try {
            final JSONObject obj = new JSONObject(getString(R.string.dummy_data_json));
            final JSONArray shifts = obj.getJSONObject("events")
                        .getJSONObject("Annual Fundraiser")
                        .getJSONObject(role)
                        .getJSONArray("shifts");

            mDataset = new ShiftData[shifts.length()];

            for (int i=0; i < shifts.length(); i++) {
                mDataset[i] = new ShiftData();
                JSONObject data = shifts.getJSONObject(i);
                mDataset[i].title = data.getString("title");
                mDataset[i].time = data.getString("time");
                mDataset[i].description = data.getString("description");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}