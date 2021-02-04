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
import com.example.foodorderapp.model.Allmenu;

import java.util.List;

public class AllMenuAdapter extends RecyclerView.Adapter<AllMenuAdapter.AllMenuViewHolder> {

    private List<Allmenu> mItems;
    private Context context;
    public AllMenuAdapter(Context context, List<Allmenu> items) {
        mItems = items;
        this.context=context;
    }

    @NonNull
    @Override
    public AllMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allmenu_recycler_items, parent, false);
        return new AllMenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllMenuViewHolder holder, int position) {
        holder.bind(holder,mItems.get(position));
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class AllMenuViewHolder extends RecyclerView.ViewHolder {

        private TextView allMenuName, allMenuNote, allMenuRating, allMenuTime, allMenuCharges, allMenuPrice;
        private ImageView allMenuImage;

        public AllMenuViewHolder(@NonNull View itemView) {
            super(itemView);
            allMenuName = itemView.findViewById(R.id.all_menu_name);
            allMenuNote = itemView.findViewById(R.id.all_menu_note);
            allMenuCharges = itemView.findViewById(R.id.all_menu_delivery_charge);
            allMenuRating = itemView.findViewById(R.id.all_menu_rating);
            allMenuTime = itemView.findViewById(R.id.all_menu_deliverytime);
            allMenuPrice = itemView.findViewById(R.id.all_menu_price);
            allMenuImage = itemView.findViewById(R.id.all_menu_image);

        }

        public void bind(AllMenuViewHolder holder,Allmenu item) {
            allMenuName.setText(item.getName());
            allMenuPrice.setText("₹ "+item.getPrice());
            allMenuTime.setText(item.getDeliveryTime());
            allMenuRating.setText(item.getRating());
            allMenuCharges.setText(item.getDeliveryCharges());
            allMenuNote.setText(item.getNote());

            Glide.with(context).load(item.getImageUrl()).into(holder.allMenuImage);
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
