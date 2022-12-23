package com.example.onlinestorage.railway;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestorage.R;
import com.example.onlinestorage.databinding.Custom1Binding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class RailAdapter extends RecyclerView.Adapter<RailAdapter.RailViewHolder> {

    private ArrayList<RailObject> Rail;

    public void setRail(ArrayList<RailObject> rail) {
        Rail = rail;
    }

    @NonNull
    @NotNull
    @Override
    public RailAdapter.RailViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Custom1Binding binding = Custom1Binding.inflate(inflater,parent,false);
        RailViewHolder viewHolder = new RailViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RailAdapter.RailViewHolder holder, int position) {
        RailObject object=Rail.get(position);
        holder.binding.setRail(object);
    }

    @Override
    public int getItemCount() {
        return Rail.size();
    }

    public class RailViewHolder extends RecyclerView.ViewHolder {
        Custom1Binding binding;
        public RailViewHolder(@NonNull @NotNull Custom1Binding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
