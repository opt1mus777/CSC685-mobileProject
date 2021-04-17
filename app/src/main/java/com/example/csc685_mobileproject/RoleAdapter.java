package com.example.csc685_mobileproject;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.csc685_mobileproject.db.RoleData;
import com.google.android.material.snackbar.Snackbar;

public class RoleAdapter extends RecyclerView.Adapter<RoleAdapter.ViewHolder>{
    private static final String TAG = "RoleAdapter";

    private RoleData[] mDataSet;

    /**
     * Provide a reference to the type of views that you are using (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView description;

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), RoleActivity.class);
                    intent.putExtra(RoleActivity.ROLE_INTENT_EVENT, "Annual Fundraiser");
                    intent.putExtra(RoleActivity.ROLE_INTENT_ROLE, title.getText());
                    v.getContext().startActivity(intent);
                }
            });

            title = v.findViewById(R.id.rowTitle);
            description = v.findViewById(R.id.rowDescription);

        }

        public TextView getTitle() {
            return title;
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
    public RoleAdapter(RoleData[] dataSet) {
        mDataSet = dataSet;
    }

    @Override
    public RoleAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.row_event, viewGroup, false);

        return new RoleAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RoleAdapter.ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        //viewHolder.getTitle().setText(mDataSet[position]);
        viewHolder.getTitle().setText(mDataSet[position].title);
        viewHolder.getDescription().setText(mDataSet[position].description);
    }
    // END_INCLUDE(recyclerViewOnBindViewHolder)

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataSet.length;
    }
}
