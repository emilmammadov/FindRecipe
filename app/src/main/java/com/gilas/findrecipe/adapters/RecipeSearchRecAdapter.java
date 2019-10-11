package com.gilas.findrecipe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.R;

import java.util.List;

public class RecipeSearchRecAdapter extends RecyclerView.Adapter<RecipeSearchRecAdapter.MyViewHolder>{

    List<String> listTitles;

    public RecipeSearchRecAdapter(List<String> listTitles) {
        this.listTitles = listTitles;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card_holder, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTag.setText(listTitles.get(position));
    }

    @Override
    public int getItemCount() {
        return listTitles.size();
    }




    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTag;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTag = itemView.findViewById(R.id.tvTag);

        }
    }
}
