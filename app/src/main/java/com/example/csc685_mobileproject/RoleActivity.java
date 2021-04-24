package com.example.csc685_mobileproject;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;

import com.example.csc685_mobileproject.db.AppDatabase;
import com.example.csc685_mobileproject.db.DatabaseHelper;
import com.example.csc685_mobileproject.db.RoleData;
import com.example.csc685_mobileproject.db.ShiftData;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class RoleActivity extends AppCompatActivity {

    public static final String ROLE_INTENT_EVENT  = "com.example.csc685_mobileproject.EVENTNAME";
    public static final String ROLE_INTENT_ROLE = "com.example.csc685_mobileproject.ROLENAME";

    protected List<ShiftData> mDataset;
    protected ShiftAdapter mAdapter;
    protected RecyclerView mRecyclerView;

    private String eventName;
    private String roleID;

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
        eventName = intent.getStringExtra(ROLE_INTENT_EVENT);
        roleID = intent.getStringExtra(ROLE_INTENT_ROLE);
        AppDatabase db = DatabaseHelper.getDB(this);
        RoleData role = db.roleDataDao().get(roleID);
        toolBarLayout.setTitle(role.title);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ShiftData newshift = new ShiftData();
                newshift.id = UUID.randomUUID().toString();
                newshift.title = "New Shift";
                newshift.time = "";
                newshift.description = "";
                newshift.roleid = roleID;
                AppDatabase db = DatabaseHelper.getDB(view.getContext());
                db.shiftDataDao().insertAll(newshift);
                Intent intent = new Intent(view.getContext(), EditShiftActivity.class);
                intent.putExtra(EditShiftActivity.EDIT_SHIFT_INTENT_ID, newshift.id);
                view.getContext().startActivity(intent);
            }
        });

        initData();
        mRecyclerView = (RecyclerView) findViewById(R.id.role_recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new ShiftAdapter(mDataset);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_role, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AppDatabase db = DatabaseHelper.getDB(getApplicationContext());
        switch (item.getItemId()) {
            case R.id.action_delete:
                RoleData role = db.roleDataDao().get(roleID);
                try{
                    db.roleDataDao().delete(role);
                    finish();
                }catch(SQLiteConstraintException ex) {
                Snackbar.make(findViewById(R.id.toolbar), "You can't delete a shift with signups!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

                return true;

            case R.id.action_edit:
                // create intent to fill in role editor
                Intent intent = new Intent(mRecyclerView.getContext(), EditRoleActivity.class);
                // tell role editor which id to edit
                intent.putExtra(EditRoleActivity.EDIT_ROLE_INTENT_ID, roleID);
                mRecyclerView.getContext().startActivity(intent);


                // : Launch the role editor activity.
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        initData();
        mAdapter.notifyDataSetChanged();
    }

    private void initData() {
        AppDatabase db = DatabaseHelper.getDB(getApplicationContext());
        if (mDataset == null) {
            mDataset = new ArrayList<ShiftData>();
        }
        mDataset.clear();
        mDataset.addAll(db.shiftDataDao().getAll(roleID));
    }
}