package com.example.csc685_mobileproject;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.csc685_mobileproject.db.AppDatabase;
import com.example.csc685_mobileproject.db.DatabaseHelper;
import com.example.csc685_mobileproject.db.RoleData;
import com.google.android.material.snackbar.Snackbar;

public class EditRoleActivity extends AppCompatActivity {

    //pass arguments from one activity to another
    public static final String EDIT_ROLE_INTENT_ID = "com.example.csc685_mobileproject.EDITROLEID";

    private EditText titleEdit;
    private EditText descriptionEdit;
    private Button deleteButton;
    private Button cancelButton;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_role);

        Intent intent = getIntent();
        String roleid = intent.getStringExtra(EDIT_ROLE_INTENT_ID);

        AppDatabase db = DatabaseHelper.getDB(getApplicationContext());
        RoleData data = db.roleDataDao().get(roleid);

        titleEdit = findViewById(R.id.roleTitleEdit);
        descriptionEdit = findViewById(R.id.roleTextEdit);
        deleteButton = findViewById(R.id.roleEditDelete);
        cancelButton = findViewById(R.id.roleEditCancel);
        saveButton = findViewById(R.id.roleEditSave);

        titleEdit.setText(data.title);
        descriptionEdit.setText(data.description);

        deleteButton.setOnClickListener((View v) -> {
            try {
                db.roleDataDao().delete(data);
                finish();
            } catch (SQLiteConstraintException ex) {
                Snackbar.make(v, "You can't delete a role with a shift!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        });

        cancelButton.setOnClickListener((View v) -> {
            finish();
        });

        saveButton.setOnClickListener((View v) -> {
            data.title = titleEdit.getText().toString();
            data.description = descriptionEdit.getText().toString();
            db.roleDataDao().update(data);
            finish();
        });

    }
}
