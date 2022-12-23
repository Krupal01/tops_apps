package com.example.ui_control.main_Recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class pageAdapter extends RecyclerView.Adapter<pageAdapter.pageViewHolder> {

    private ArrayList<pages> page;

    public void filterList(ArrayList<pages> filteredlist) {

            page = filteredlist;
            notifyDataSetChanged();

    }

    public interface pagesClickListener {
        void onClickListener(pages pages);
    }

    private pagesClickListener listener;

    public pageAdapter (pagesClickListener listener){
        this.listener = listener;
    }

    public void setpage(ArrayList<pages> page){
        this.page = page;
    }


    @NonNull
    @Override
    public pageAdapter.pageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(android.R.layout.simple_expandable_list_item_1,parent,false);
        pageViewHolder pageViewHolder = new pageViewHolder(view);
        return pageViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull pageAdapter.pageViewHolder holder, int position) {
        pages pages = page.get(position);
        holder.textView.setText(pages.getTitle());

        holder.itemView.setOnClickListener(v -> {
            listener.onClickListener(pages);
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
