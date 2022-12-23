package com.example.storage_assigment.Preference.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storage_assigment.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.itemViewHolder> {

    String NAME = "itemName";
    String PRICE = "price";
    int count = 1;
    ArrayList<item> items1 = new ArrayList<>();
    ArrayList<item> item2 = new ArrayList<>();

    public interface setListner{
        void clickListner(item item);
    }

    public setListner listner;

    public itemAdapter(setListner listner) {
        this.listner = listner;
    }

    @NonNull
    @NotNull
    @Override
    public itemAdapter.itemViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.custom1,null);
        itemViewHolder holder = new itemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull itemAdapter.itemViewHolder holder, int position) {
        holder.name.setText(items1.get(position).getItem());
        holder.price.setText(items1.get(position).getPrice());
        holder.add.setOnClickListener(v -> {

            item2.add(items1.get(position));
            cartFragment fragment = new cartFragment();
            fragment.setSelected(item2);
        });
//        holder.add.setOnClickListener(v -> {
//            cartFragment fragment = new cartFragment();
//            fragment.selected.add(new item(items1.get(position).getItem() , items1.get(position).getPrice()));
//
//            count=+1;
//            String countString = String.valueOf(count);
//
//            String namemodifide = NAME+countString;
//            String pricemodifide = PRICE+countString;
//
//            SharedPreferences preferences = holder.context.getSharedPreferences("item", MODE_PRIVATE);
//            SharedPreferences.Editor editor = preferences.edit();
//            editor.putString(namemodifide,items1.get(position).getItem());
//            editor.putString(pricemodifide,items1.get(position).getPrice());
//
//        });

    }

    @Override
    public int getItemCount() {
        return items1.size();
    }

    public void setList(ArrayList<item> items) {
        this.items1 = items;
    }

    public class itemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView price;
        private Button add;
        private Context context;
        public itemViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtItemName);
            price = itemView.findViewById(R.id.txtPrice);
            add = itemView.findViewById(R.id.btnAdd);
           context = itemView.getContext();
        }
    }
}
