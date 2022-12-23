package com.example.ui_control.Whatapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;

import java.util.ArrayList;

public class WpAdapter extends RecyclerView.Adapter<WpAdapter.WpViewHolder>{
    ArrayList<person> person1 = new ArrayList<>();

    public void setWpAdapter(ArrayList<person> person) {
        this.person1 = person;
    }


    @NonNull
    @Override
    public WpAdapter.WpViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.wp_layout , parent ,false);
        WpViewHolder holder = new WpViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull WpAdapter.WpViewHolder holder, int position) {
        person person = person1.get(position);
        holder.textView.setText(person.getName());
        holder.imageView.setImageResource(person.getSrc());
    }

    @Override
    public int getItemCount() {
        return person1.size();
    }

    public class WpViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        private ImageView imageView;
        public WpViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.name);
            imageView = itemView.findViewById(R.id.dp);
        }
    }
}
