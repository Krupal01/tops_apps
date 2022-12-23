package com.example.mainactivity.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import java.net.CookieHandler;
import java.util.ArrayList;

public class pageAdapter extends Adapter<pageAdapter.pageViewHolder>{

    private  ArrayList<pages> page;



    public interface pagesClickListener{
        void onpageClick(pages item);
    }

    private pagesClickListener listener;

    // Click Event Step 3
    public pageAdapter(pagesClickListener listener){
        this.listener=listener;
    }

    public void setpage(ArrayList<pages> pages) {
        this.page = pages;
    }

    @NonNull
    @Override
    public pageAdapter.pageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(android.R.layout.simple_list_item_1,parent,false);
        pageViewHolder viewHolder = new pageViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull pageAdapter.pageViewHolder holder, int position) {

        pages item=page.get(position);
        holder.textView.setText(item.getTitle());
        holder.itemView.setOnClickListener(v->{
            listener.onpageClick(item);
        });
    }

    @Override
    public int getItemCount() {
        return page.size();
    }


    public class pageViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;
        public pageViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);


        }
    }
}
