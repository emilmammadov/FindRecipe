package com.gilas.findrecipe.Adapters;

import android.content.Context;
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

public class FavRecyclerAdapter extends RecyclerView.Adapter<FavRecyclerAdapter.MyViewHolder>{

    private Context context;
    ArrayList<Recipes> recipeList;

    public FavRecyclerAdapter(Context context, ArrayList<Recipes> recipeList) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fav_recycler_item, parent, false);
        return new FavRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Recipes recipe = recipeList.get(position);

        String personCount = recipe.getPersonCount()
                + " " + context.getResources().getString(R.string.person);

        double timeMin = (recipe.getCookTimeSec() + recipe.getPrepTimeSec()) / 60.0;
        String time = (int) timeMin + " " + context.getResources().getString(R.string.minute);


        holder.tvTitle.setText(recipeList.get(position).getTitle());
        holder.tvPersonCount.setText(personCount);
        holder.tvTime.setText(time);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle;
        ImageView imgFav;
        TextView tvPersonCount, tvTime;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imgFav = itemView.findViewById(R.id.imgFav);
            tvTitle = itemView.findViewById(R.id.tvTitleFav);
            tvPersonCount = itemView.findViewById(R.id.tvPersonCount);
            tvTime = itemView.findViewById(R.id.tvTime);

        }
    }
}
