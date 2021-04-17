package com.example.csc685_mobileproject;

import android.content.Intent;
import android.os.Bundle;

import com.example.csc685_mobileproject.db.AppDatabase;
import com.example.csc685_mobileproject.db.DatabaseHelper;
import com.example.csc685_mobileproject.db.ShiftData;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import android.view.View;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class RoleActivity extends AppCompatActivity {

    public static final String ROLE_INTENT_EVENT  = "com.example.csc685_mobileproject.EVENTNAME";
    public static final String ROLE_INTENT_ROLE = "com.example.csc685_mobileproject.ROLENAME";

    protected List<ShiftData> mDataset;
    protected ShiftAdapter mAdapter;
    protected RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);

        // showing the back button in action bar
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Get the role from the intent...
        Intent intent = getIntent();
        String eventname = intent.getStringExtra(ROLE_INTENT_EVENT);
        String rolename = intent.getStringExtra(ROLE_INTENT_ROLE);
        toolBarLayout.setTitle(rolename);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        initData(eventname, rolename);
        mRecyclerView = (RecyclerView) findViewById(R.id.role_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShiftAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);

    }

    private void initData(String event, String role) {
        AppDatabase db = DatabaseHelper.getDB(getApplicationContext());
        mDataset = db.shiftDataDao().getAll(role);
    }
}