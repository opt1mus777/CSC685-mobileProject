package com.example.csc685_mobileproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.csc685_mobileproject.db.AppDatabase;
import com.example.csc685_mobileproject.db.DatabaseHelper;
import com.example.csc685_mobileproject.db.ShiftData;
import com.google.android.material.snackbar.Snackbar;

public class EditShiftActivity extends AppCompatActivity {

    public static final String EDIT_SHIFT_INTENT_ID  = "com.example.csc685_mobileproject.EDITSHIFTID";

    private EditText titleEdit;
    private EditText timeEdit;
    private EditText descriptionEdit;
    private Button deleteButton;
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_shift);

        Intent intent = getIntent();
        String shiftid = intent.getStringExtra(EDIT_SHIFT_INTENT_ID);

        AppDatabase db = DatabaseHelper.getDB(getApplicationContext());
        ShiftData data = db.shiftDataDao().get(shiftid);

        setTitle(String.format("Edit %s Shift", data.title));

        titleEdit = findViewById(R.id.shiftTitleEdit);
        timeEdit = findViewById(R.id.shiftTimeEdit);
        descriptionEdit = findViewById(R.id.shiftTextEdit);
        deleteButton = findViewById(R.id.shiftEditDelete);
        cancelButton = findViewById(R.id.shiftEditCancel);
        saveButton = findViewById(R.id.shfitEditSave);

        titleEdit.setText(data.title);
        timeEdit.setText(data.time);
        descriptionEdit.setText(data.description);

        deleteButton.setOnClickListener((View v) -> {
            try {
                db.shiftDataDao().delete(data);
                finish();
            } catch (SQLiteConstraintException ex) {
                Snackbar.make(v, "You can't delete a shift with signups!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        cancelButton.setOnClickListener((View v) -> {
            finish();
        });

        saveButton.setOnClickListener((View v) -> {
            data.title = titleEdit.getText().toString();
            data.time = timeEdit.getText().toString();
            data.description = descriptionEdit.getText().toString();
            db.shiftDataDao().update(data);
            finish();
        });

    }
}