package com.example.careercoach.settings;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.SettingsRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder> {

    ArrayList<SettingsData> dataArrayList;
    private onSettingClickListener listener;

    public void setListener(onSettingClickListener listener) {
        this.listener = listener;
    }

    public void setDataArrayList(ArrayList<SettingsData> dataArrayList) {
        this.dataArrayList = dataArrayList;
    }

    public interface onSettingClickListener {
        void onSettingClick(int position);
    }


    @NotNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        SettingsRowItemBinding binding = SettingsRowItemBinding.inflate(inflater, parent, false);
        SettingsViewHolder viewHolder = new SettingsViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull SettingsViewHolder holder, int position) {
        SettingsData data = dataArrayList.get(position);
        holder.binding.setData(data);
        holder.itemView.setOnClickListener(v -> {
            listener.onSettingClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class SettingsViewHolder extends RecyclerView.ViewHolder {
        private SettingsRowItemBinding binding;

        public SettingsViewHolder(@NonNull SettingsRowItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
