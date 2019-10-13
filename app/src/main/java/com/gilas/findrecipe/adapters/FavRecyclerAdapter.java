package com.gilas.findrecipe.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.databinding.FavRecyclerItemBinding;

import java.util.List;

public class FavRecyclerAdapter extends RecyclerView.Adapter<FavRecyclerAdapter.ViewHolder> {

    private List<Recipe> recipeList;

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
    }

    @Override
    public int getItemCount() {
        return recipeList != null ? recipeList.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        private FavRecyclerItemBinding binding;

        private ViewHolder(FavRecyclerItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Recipe recipe) {
            binding.setFavItem(recipe);
            binding.executePendingBindings();
        }
    }
}
