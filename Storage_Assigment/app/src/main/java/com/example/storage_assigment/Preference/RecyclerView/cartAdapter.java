package com.example.storage_assigment.Preference.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storage_assigment.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class cartAdapter extends RecyclerView.Adapter<cartAdapter.cartViewHolder> {
    ArrayList<item> selected1 = new ArrayList<>();

    public void setcartAdapter(ArrayList<item> selected1) {
        this.selected1 = selected1;
    }

    @NonNull
    @NotNull
    @Override
    public cartAdapter.cartViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view =inflater.inflate(R.layout.custom1,null);
        cartViewHolder holder = new cartViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull cartAdapter.cartViewHolder holder, int position) {
        holder.name.setText(selected1.get(position).getItem());
        holder.price.setText(selected1.get(position).getPrice());
        holder.remove.setText("remove");

        holder.remove.setOnClickListener(v -> {
            selected1.remove(selected1.get(position));
            notifyDataSetChanged();

//            int count = selected1.indexOf(selected1.get(position))+1;
//
//            SharedPreferences preferences = holder.context.getSharedPreferences("item",Context.MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.remove("name"+count);
        });
    }

    @Override
    public int getItemCount() {
        return selected1.size();
    }

    public class cartViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private Button remove;
        private Context context;

        public cartViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtItemName);
            price = itemView.findViewById(R.id.txtPrice);
            remove = itemView.findViewById(R.id.btnAdd);
            context = itemView.getContext();

        }
    }
}
