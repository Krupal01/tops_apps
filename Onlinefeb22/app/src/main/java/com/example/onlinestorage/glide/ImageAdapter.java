package com.example.onlinestorage.glide;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlinestorage.databinding.ImageRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    List<ImageData> dataList;

    public void setDataList(List<ImageData> dataList) {
        this.dataList = dataList;
    }

    @NotNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ImageRowItemBinding binding=ImageRowItemBinding.inflate(inflater,parent,false);
        ImageViewHolder viewHolder=new ImageViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ImageViewHolder holder, int position) {
        ImageData data=dataList.get(position);
        holder.binding.setData(data);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        private ImageRowItemBinding binding;
        public ImageViewHolder(@NonNull ImageRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}

