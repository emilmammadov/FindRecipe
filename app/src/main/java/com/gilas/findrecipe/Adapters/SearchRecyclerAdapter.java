package com.gilas.findrecipe.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.Database.Tags;
import com.gilas.findrecipe.R;

import java.util.ArrayList;

public class SearchRecyclerAdapter extends RecyclerView.Adapter<SearchRecyclerAdapter.MyViewHolder> {

    ArrayList<Tags> tags;
    //private final View.OnClickListener mOnClickListener = new View.OnClickListener(this);

    public SearchRecyclerAdapter(ArrayList<Tags> tags) {
        this.tags = tags;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card_holder, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTag.setText(tags.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTag;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTag = itemView.findViewById(R.id.tvTag);

        }
    }
}
