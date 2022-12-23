package com.example.ui_control.A6_cart;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.cartViewHolder>{
    ArrayList<item> items;

    public void setcartAdapter(ArrayList<item> items) {
        this.items = items;
    }

    public interface cartonclicklister{
        void Onclicklistener(item item);
    }

    @NonNull
    @Override
    public cartAdapter.cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.cart , parent ,false);
        cartViewHolder holder = new cartViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull cartAdapter.cartViewHolder holder, int position) {
        item item = items.get(position);
        holder.name.setText(item.getName());
        holder.price.setText(item.getPrice());
        holder.src.setImageResource(item.getSrc());

        holder.add.setOnClickListener(v -> {
                holder.quntity.setText(Integer.toString(item.getQuntity()+1));
                item.setQuntity(item.getQuntity()+1);
                holder.totle.setText("totle:"+ Integer.parseInt(item.getPrice()) * item.getQuntity());
        });

        holder.remove.setOnClickListener(v -> {
            holder.quntity.setText(Integer.toString(item.getQuntity()-1));
            item.setQuntity(item.getQuntity()-1);
            holder.totle.setText("totle:"+ Integer.parseInt(item.getPrice()) * item.getQuntity());
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class cartViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private ImageView src;
        private Button add;
        private Button remove;
        private TextView quntity;
        private TextView totle;
        public cartViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
            src = itemView.findViewById(R.id.imageView2);
            add = itemView.findViewById(R.id.add);
            remove = itemView.findViewById(R.id.remove);
            quntity = itemView.findViewById(R.id.Quntity);
            totle = itemView.findViewById(R.id.totle);

        }
    }
}
