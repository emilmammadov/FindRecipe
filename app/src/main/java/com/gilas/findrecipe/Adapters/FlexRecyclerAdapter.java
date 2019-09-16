package com.gilas.findrecipe.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.R;

import java.util.ArrayList;

public class FlexRecyclerAdapter extends RecyclerView.Adapter<FlexRecyclerAdapter.MyViewHolder> {

    ArrayList<String> tags;

    public FlexRecyclerAdapter(ArrayList<String> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_flex_box, parent, false);
        return new FlexRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTag.setText(tags.get(position));
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }







    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTag;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            tvTag = itemView.findViewById(R.id.flexItemTextView);
        }
    }
}
