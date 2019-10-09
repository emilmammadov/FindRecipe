package com.gilas.findrecipe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.Entities.Recipe;
import com.gilas.findrecipe.R;

import java.util.ArrayList;

public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.MyViewHolder> {

    ArrayList<Recipe> recipes;

    public RecipeRecyclerAdapter(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_card_holder, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(recipes.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
        }
    }

}
