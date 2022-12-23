package com.example.ui_control.A6_cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;

import java.util.ArrayList;

public class shopAdapter extends RecyclerView.Adapter<shopAdapter.shopViewHolder> {

    ArrayList<item> items;

    public interface setclicklistener{
        void onclicklistner(item item);
    }

    setclicklistener listner;
    public shopAdapter(setclicklistener listner) {
        this.listner = listner;
    }


    public void setshopAdapter(ArrayList<item> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public shopAdapter.shopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.shop , parent ,false);
        shopViewHolder holder = new shopViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull shopAdapter.shopViewHolder holder, int position) {
        item item = items.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice());
        holder.src.setImageResource(item.getSrc());

       holder.itemView.setOnClickListener(v -> {
           listner.onclicklistner(item);
       });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class shopViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private ImageView src;
        public shopViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            src = itemView.findViewById(R.id.imageView2);
        }
    }
}
