package com.gilas.findrecipe.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gilas.findrecipe.R;

import java.util.ArrayList;

public class FlexRecyclerAdapter extends RecyclerView.Adapter<FlexRecyclerAdapter.MyViewHolder> {

    ArrayList<String> tags;



    private OnItemClickListener mOnItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position); // buradaki parametlere activity'deki textview'a ne göndermek istiyorsanız onları yazın. Atıyorum "String kalori, String yemekAdi" vs.
    }

    // constructor'ımıza listener koyalım)
    public FlexRecyclerAdapter(ArrayList<String> tags, OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
        this.tags = tags;
    }

    /*public FlexRecyclerAdapter(ArrayList<String> tags) {
        this.tags = tags;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.flexbox_item, parent, false);
        return new FlexRecyclerAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.tvTag.setText(tags.get(position));



        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("TAGTAGTAG", "onClick: "+ position );
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }







    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvTag;
        Button btnDelete;

        public MyViewHolder(@NonNull View itemView) {

            super(itemView);
            tvTag = itemView.findViewById(R.id.flexItemTextView);
            btnDelete = itemView.findViewById(R.id.flexBoxDeleteButton);

        }
    }
}
