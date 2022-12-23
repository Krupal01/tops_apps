package com.example.careercoach.news_feed;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.NewsRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    ArrayList<News>newsArrayList;

    NewsAdapter(ArrayList<News>newsArrayList){
        this.newsArrayList=newsArrayList;
    }


    @NotNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        NewsRowItemBinding binding=NewsRowItemBinding.inflate(inflater,parent,false);
        NewsViewHolder viewHolder=new NewsViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull NewsViewHolder holder, int position) {

        News news=newsArrayList.get(position);
        holder.binding.setData(news);


    }

    @Override
    public int getItemCount() {
        return newsArrayList.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        private NewsRowItemBinding binding;
        public NewsViewHolder(@NonNull NewsRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

    }
}
