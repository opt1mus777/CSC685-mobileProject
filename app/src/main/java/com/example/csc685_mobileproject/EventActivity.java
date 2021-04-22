package com.example.csc685_mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.csc685_mobileproject.db.AppDatabase;
import com.example.csc685_mobileproject.db.DatabaseHelper;
import com.example.csc685_mobileproject.db.RoleData;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class EventActivity extends AppCompatActivity {

    protected List<RoleData> mDataset;
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
                Intent intent = new Intent(view.getContext(), EditRoleActivity.class);
                intent.putExtra(EditRoleActivity.EDIT_ROLE_INTENT_ID, "Annual Fundraiser");
                view.getContext().startActivity(intent);
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
        AppDatabase db = DatabaseHelper.getDB(getApplicationContext());
        mDataset = db.roleDataDao().getAll("Annual Fundraiser");
    }
}