package com.example.csc685_mobileproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.csc685_mobileproject.db.AppDatabase;
import com.example.csc685_mobileproject.db.DatabaseHelper;
import com.example.csc685_mobileproject.db.ShiftData;
import com.example.csc685_mobileproject.db.SignupData;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;
import java.util.UUID;

/**
 * Provide views to RecyclerView with data from mDataSet.
 */
public class ShiftAdapter extends RecyclerView.Adapter<ShiftAdapter.ViewHolder> {
    private static final String TAG = "ShiftAdapter";

    private List<ShiftData> mDataSet;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected View view;
        protected TextView title;
        protected TextView time;
        protected TextView description;
        protected Button signup;
        protected TextView person;
        protected String shiftid;
        protected SignupData signupData;

        public boolean isEnabled() {
            return signup.getText().equals(view.getResources().getString(R.string.signup_do));
        }

        public void setEnabled(boolean en) {
            if (en) {
                signup.setText(view.getResources().getString(R.string.signup_do));
                signup.setBackgroundColor(0x4CAF50);
            }
            else {
                signup.setText(view.getResources().getString(R.string.signup_cancel));
                signup.setBackgroundColor(0xFFFF5722);
            }
        }

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), EditShiftActivity.class);
                    intent.putExtra(EditShiftActivity.EDIT_SHIFT_INTENT_ID, shiftid);
                    v.getContext().startActivity(intent);
                }
            });

            view = v;
            title = v.findViewById(R.id.shiftTitle);
            time = v.findViewById(R.id.shiftTime);
            description = v.findViewById(R.id.shiftDescription);
            signup = v.findViewById(R.id.signupButton);
            person = v.findViewById(R.id.signupPerson);
            signup.setOnClickListener((View vv) -> {
                if (isEnabled()) {
                    signupData = new SignupData();
                    signupData.id = UUID.randomUUID().toString();
                    signupData.name = "Your Name Here";
                    signupData.shiftid = shiftid;

                    AppDatabase db = DatabaseHelper.getDB(view.getContext());
                    db.signupDataDao().insertAll(signupData);

                    person.setText(signupData.name);
                    setEnabled(false);
                }
                else {
                    AppDatabase db = DatabaseHelper.getDB(view.getContext());
                    db.signupDataDao().delete(signupData);
                    signupData = null;
                    person.setText("");
                    setEnabled(true);
                }
            });

        }
    }

    /**
     * Initialize the dataset of the Adapter.
     *
     * @param dataSet String[] containing the data to populate views to be used by RecyclerView.
     */
    public ShiftAdapter(List<ShiftData> dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_role, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        //viewHolder.getTitle().setText(mDataSet[position]);
        ShiftData data = mDataSet.get(position);
        viewHolder.title.setText(data.title);
        viewHolder.time.setText(data.time);
        viewHolder.description.setText(data.description);
        viewHolder.shiftid = data.id;

        AppDatabase db = DatabaseHelper.getDB(viewHolder.view.getContext());
        List<SignupData> people = db.signupDataDao().getAll(data.id);
        viewHolder.setEnabled(true);
        for (SignupData person: people) {
            viewHolder.signupData = person;
            viewHolder.person.setText(person.name);
            viewHolder.setEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}