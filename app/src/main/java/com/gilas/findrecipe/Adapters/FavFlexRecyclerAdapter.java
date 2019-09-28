package com.gilas.findrecipe.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.Database.Recipes;
import com.gilas.findrecipe.R;

import java.util.ArrayList;

public class FavFlexRecyclerAdapter extends RecyclerView.Adapter<FavFlexRecyclerAdapter.MyViewHolder>{

    ArrayList<Recipes> recipeList;

    public FavFlexRecyclerAdapter(ArrayList<Recipes> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_flexbox_item, parent, false);
        return new FavFlexRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvTitle.setText(recipeList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView imgFav;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFav = itemView.findViewById(R.id.imgFav);
            tvTitle = itemView.findViewById(R.id.tvTitleFav);

        }
    }
}
