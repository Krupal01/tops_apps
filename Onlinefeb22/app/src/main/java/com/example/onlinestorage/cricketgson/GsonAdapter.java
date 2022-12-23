package com.example.onlinestorage.cricketgson;

import android.view.LayoutInflater;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.onlinestorage.databinding.Custom2Binding;



import com.example.onlinestorage.recycler_gson.UserRecycler;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class GsonAdapter extends RecyclerView.Adapter<GsonAdapter.GsonViewHolder> {

    private ArrayList<UserRecycler> User;

    public void setUser(ArrayList<UserRecycler> User) {
        this.User = User;
    }

    @NonNull
    @NotNull
    @Override
    public GsonViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Custom2Binding binding = Custom2Binding.inflate(inflater,parent,false);
        GsonAdapter.GsonViewHolder viewHolder = new GsonAdapter.GsonViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull GsonViewHolder holder, int position) {
        UserRecycler object=User.get(position);
        holder.binding.setUser(object);
    }

    @Override
    public int getItemCount() {
        return User.size();
    }

    public class GsonViewHolder extends RecyclerView.ViewHolder {
        Custom2Binding binding;
        public GsonViewHolder(@NonNull @NotNull Custom2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
