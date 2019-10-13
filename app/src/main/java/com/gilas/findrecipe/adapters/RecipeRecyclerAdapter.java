package com.gilas.findrecipe.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.data.Recipe;
import com.gilas.findrecipe.databinding.RecipeCardHolderBinding;

import java.util.List;


public class RecipeRecyclerAdapter extends RecyclerView.Adapter<RecipeRecyclerAdapter.ViewHolder> {

    private List<Recipe> recipes;

    public RecipeRecyclerAdapter(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RecipeCardHolderBinding binding = RecipeCardHolderBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(recipes.get(position));
    }

    @Override
    public int getItemCount() {
        return recipes != null ? recipes.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        RecipeCardHolderBinding binding;

        private ViewHolder(RecipeCardHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Recipe recipe) {
            binding.setRecipe(recipe);
            binding.executePendingBindings();
        }
    }

}
