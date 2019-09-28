package com.gilas.findrecipe.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.Database.Tags;
import com.gilas.findrecipe.R;

import java.util.ArrayList;

public class TagFlexRecyclerAdapter extends RecyclerView.Adapter<TagFlexRecyclerAdapter.MyViewHolder> {

    ArrayList<Tags> tags;


    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        // Buradaki parametlere activity'ye göndermek istediklerinizi yazın
        void onItemClick(int position);
    }

    // constructor'ımıza listener koyalım
    public TagFlexRecyclerAdapter(ArrayList<Tags> tags, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        this.tags = tags;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tag_flexbox_item, parent, false);
        return new TagFlexRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvTag.setText(tags.get(position).getName());


        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTag;
        Button btnDelete;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            tvTag = itemView.findViewById(R.id.flexItemTextView);
            btnDelete = itemView.findViewById(R.id.flexBoxDeleteButton);

        }
    }
}