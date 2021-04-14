package com.example.csc685_mobileproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.csc685_mobileproject.db.AppDatabase;
import com.example.csc685_mobileproject.db.DatabaseHelper;
import com.example.csc685_mobileproject.db.EventData;
import com.example.csc685_mobileproject.db.RoleData;
import com.example.csc685_mobileproject.db.ShiftData;

import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private String kitchenID;
    private String cleanupID;
    private String gateID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DatabaseHelper.resetDB(this);
    }

    public void gotoKitchen(View view) {
        Intent intent = new Intent(this, RoleActivity.class);
        intent.putExtra(RoleActivity.ROLE_INTENT_EVENT, "Annual Fundraiser");
        intent.putExtra(RoleActivity.ROLE_INTENT_ROLE, "Kitchen");
        startActivity(intent);
    }

    public void gotoCleanup(View view) {
        Intent intent = new Intent(this, RoleActivity.class);
        intent.putExtra(RoleActivity.ROLE_INTENT_EVENT, "Annual Fundraiser");
        intent.putExtra(RoleActivity.ROLE_INTENT_ROLE, "Cleanup");
        startActivity(intent);
    }

    public void gotoGate(View view) {
        Intent intent = new Intent(this, RoleActivity.class);
        intent.putExtra(RoleActivity.ROLE_INTENT_EVENT, "Annual Fundraiser");
        intent.putExtra(RoleActivity.ROLE_INTENT_ROLE, "Gate");
        startActivity(intent);
    }
}