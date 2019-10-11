package com.gilas.findrecipe.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.databinding.FavRecyclerItemBinding;

import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter<FavRecyclerAdapter.ViewHolder> {

    List<Recipe> recipeList;

    public FavRecyclerAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        FavRecyclerItemBinding binding = FavRecyclerItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Recipe recipe = recipeList.get(position);
        holder.bind(recipe);

        /*String personCount = recipe.getPersonCount()
                + " " + context.getResources().getString(R.string.person);

        double timeMin = (recipe.getCookTimeSec() + recipe.getPrepTimeSec()) / 60.0;
        String time = (int) timeMin + " " + context.getResources().getString(R.string.minute);


        holder.tvTitle.setText(recipeList.get(position).getTitle());
        holder.tvPersonCount.setText(personCount);
        holder.tvTime.setText(time);*/
    }

    @Override
    public int getItemCount() {
        return recipeList != null ? recipeList.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private FavRecyclerItemBinding binding;

        public ViewHolder(FavRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(Recipe recipe) {
            binding.setFavItem(recipe);
            binding.executePendingBindings();
        }
    }
}
