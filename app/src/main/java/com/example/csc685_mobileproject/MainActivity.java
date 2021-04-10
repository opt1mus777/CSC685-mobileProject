package com.example.csc685_mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    public static final String ROLE_INTENT_NAME = "com.example.csc685_mobileproject.ROLENAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void gotoJobs(View view) {
        Intent intent = new Intent(this, RoleActivity.class);
        intent.putExtra(ROLE_INTENT_NAME, "Kitchen");
        startActivity(intent);
    }
}