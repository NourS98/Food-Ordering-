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
import com.example.foodorderapp.model.Recommended;

import java.util.List;

public class RecommendAdapter extends RecyclerView.Adapter<RecommendAdapter.RecommendedViewHolder> {

    private List<Recommended> mItems;
    private Context context;

    public RecommendAdapter(Context context, List<Recommended> items) {
        mItems = items;
        this.context=context;
    }

    @NonNull
    @Override
    public RecommendedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recommended_recycler_items, parent, false);
        return new RecommendedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommendedViewHolder holder, int position) {
        holder.bind(holder ,mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class RecommendedViewHolder extends RecyclerView.ViewHolder {

       private ImageView recommendedImage;
       private TextView recommendedName, recommendedRating, recommendedDeliveryTime, recommendedCharges, recommendedPrice;


        public RecommendedViewHolder(@NonNull View itemView) {
            super(itemView);
            recommendedImage=itemView.findViewById(R.id.recommended_image);
            recommendedName=itemView.findViewById(R.id.recommended_name);
            recommendedRating=itemView.findViewById(R.id.recommended_rating);
            recommendedDeliveryTime=itemView.findViewById(R.id.recommended_delivery_time);
            recommendedCharges=itemView.findViewById(R.id.delivery_type);
            recommendedPrice=itemView.findViewById(R.id.recommended_price);
        }

        public void bind(RecommendedViewHolder holder , Recommended item) {
            recommendedName.setText(item.getName());
            recommendedRating.setText(item.getRating());
            recommendedDeliveryTime.setText(item.getDeliveryTime());
            recommendedCharges.setText(item.getDeliveryCharges());
            recommendedPrice.setText("$ "+item.getPrice());
            Glide.with(context).load(item.getImageUrl()).into(holder.recommendedImage);
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
        }

    }
}
