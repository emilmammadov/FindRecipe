package com.gilas.findrecipe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.R;
import com.gilas.findrecipe.data.Recipe;

import java.util.List;

public class RecipeSearchRecAdapter extends RecyclerView.Adapter<RecipeSearchRecAdapter.MyViewHolder> {

    private List<Recipe> listRecipe;

    public RecipeSearchRecAdapter(List<Recipe> listRecipe) {
        this.listRecipe = listRecipe;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_card_holder, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTag.setText(listRecipe.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return listRecipe.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTag;

        private MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTag = itemView.findViewById(R.id.tvTag);

        }
    }
}
