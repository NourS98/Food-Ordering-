package com.example.foodorderapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorderapp.model.Popular;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {

    private List<Popular> mItems;
    private Context context;

    public PopularAdapter(Context context, List<Popular> items) {
        mItems = items;
        this.context=context;
    }

    @NonNull
    @Override
    public PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_recycler_items, parent, false);
        return new PopularViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularViewHolder holder, int position) {
        holder.bind(holder ,mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class PopularViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.all_menu_image);
            textView=itemView.findViewById(R.id.all_menu_name);

        }

        public void bind(PopularViewHolder holder ,Popular item) {
            textView.setText(item.getName());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(context, FoodDetails.class);
                    i.putExtra("name", item.getName());
                    i.putExtra("price", item.getPrice());
                    i.putExtra("rating",item.getRating());
                    i.putExtra("image", item.getImageUrl());

                    context.startActivity(i);
                }
            });
            Glide.with(context).load(item.getImageUrl()).into(holder.imageView);
        }

    }
}
