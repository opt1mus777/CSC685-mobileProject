package com.example.csc685_mobileproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.csc685_mobileproject.db.ShiftData;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

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

        private TextView title;
        private TextView time;
        private TextView description;
        private Button signup;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            });

            title = v.findViewById(R.id.shiftTitle);
            time = v.findViewById(R.id.shiftTime);
            description = v.findViewById(R.id.shiftDescription);
            signup = v.findViewById(R.id.signupButton);
            signup.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Snackbar.make(v, "Thanks for volunteering!", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getTime() {
            return time;
        }

        public TextView getDescription() {
            return description;
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
        viewHolder.getTitle().setText(mDataSet.get(position).title);
        viewHolder.getTime().setText(mDataSet.get(position).time);
        viewHolder.getDescription().setText(mDataSet.get(position).description);
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}