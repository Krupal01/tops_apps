package com.example.ui_control.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;

import java.util.ArrayList;


public class demoAdapter extends RecyclerView.Adapter<demoAdapter.demoViewHolder> {

    ArrayList<demo> demos = new ArrayList<>();

    public interface democlickListener {
        void clickListner(demo demo);
    }

    private democlickListener listener;

    public demoAdapter(democlickListener listener){
        this.listener = listener;
    }

    public void setDemos(ArrayList<demo> demos){
        this.demos = demos;
    }

    @NonNull
    @Override
    public demoAdapter.demoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.costum_layout , parent , false);
        demoViewHolder holder = new demoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull demoAdapter.demoViewHolder holder, int position) {
        demo demo = demos.get(position);
        holder.name.setText("name :"+demo.getName());
        holder.position.setText("position :"+demo.getPosi());
        holder.date.setText("date :"+demo.getNumber());

        holder.itemView.setOnClickListener(v -> {
            listener.clickListner(demo);
        });

    }

    @Override
    public int getItemCount() {
        return demos.size();
    }

    public void setdemo(ArrayList<demo> demos) {
        this.demos = demos;
    }

    public class demoViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView position;
        private TextView date;
        public demoViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            position = itemView.findViewById(R.id.position);
            date = itemView.findViewById(R.id.date);
        }
    }
}
