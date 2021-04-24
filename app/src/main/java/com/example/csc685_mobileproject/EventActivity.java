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
import com.example.csc685_mobileproject.db.ShiftData;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
                RoleData newrole = new RoleData();
                newrole.id = UUID.randomUUID().toString();
                newrole.title = "New Role";
                newrole.description = "";
                newrole.eventid = "Annual Fundraiser";
                AppDatabase db = DatabaseHelper.getDB(view.getContext());
                db.roleDataDao().insertAll(newrole);
                Intent intent = new Intent(view.getContext(), EditRoleActivity.class);
                intent.putExtra(EditRoleActivity.EDIT_ROLE_INTENT_ID, newrole.id);
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
    @Override
    protected void onResume() {
        super.onResume();
        initData();
        mAdapter.notifyDataSetChanged();
    }

    private void initData() {
        AppDatabase db = DatabaseHelper.getDB(getApplicationContext());
        if (mDataset == null) {
            mDataset = new ArrayList<RoleData>();
        }
        mDataset.clear();
        mDataset.addAll(db.roleDataDao().getAll("Annual Fundraiser"));
    }
}