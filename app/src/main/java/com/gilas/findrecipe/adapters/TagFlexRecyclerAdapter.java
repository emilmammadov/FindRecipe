package com.gilas.findrecipe.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.data.Tag;
import com.gilas.findrecipe.databinding.TagFlexboxItemBinding;

import java.util.List;

public class TagFlexRecyclerAdapter extends RecyclerView.Adapter<TagFlexRecyclerAdapter.ViewHolder> {

    private List<Tag> tags;
    private TagFlexboxItemBinding binding;


    private OnDeleteListener mOnDeleteListener;

    public interface OnDeleteListener {
        // Buradaki parametlere activity'ye göndermek istediklerinizi yazın
        void onItemClick(int position);
    }

    // constructor'ımıza listener koyalım
    public TagFlexRecyclerAdapter(List<Tag> tags, OnDeleteListener onDeleteListener) {
        mOnDeleteListener = onDeleteListener;
        this.tags = tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = tags;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        binding = TagFlexboxItemBinding.inflate(inflater, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        //holder.tvTag.setText(tags.get(position).getName());


        binding.flexBoxDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnDeleteListener.onItemClick(position);
            }
        });

        Tag tag = tags.get(position);
        holder.bind(tag);
    }

    @Override
    public int getItemCount() {
        return tags != null ? tags.size() : 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TagFlexboxItemBinding binding;

        private ViewHolder(TagFlexboxItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(Tag tag) {
            binding.setTag(tag);
            binding.executePendingBindings();
        }
    }
}
