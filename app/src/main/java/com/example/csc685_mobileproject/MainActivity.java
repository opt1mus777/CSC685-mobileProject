package com.example.csc685_mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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