package com.example.onlinestorage.xml;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestorage.databinding.Custom3Binding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private ArrayList<NewsDataFormat> List;

    public void setList(ArrayList<NewsDataFormat> list) {
        List = list;
    }

    @NonNull
    @NotNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Custom3Binding binding = Custom3Binding.inflate(inflater,parent,false);
        NewsViewHolder holder = new NewsViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewsViewHolder holder, int position) {
        holder.binding.setNews(List.get(position));
    }

    @Override
    public int getItemCount() {
        return List.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private Custom3Binding binding;
        public NewsViewHolder(@NonNull @NotNull Custom3Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
