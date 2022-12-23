package com.example.storageassignment.school;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storageassignment.R;
import com.example.storageassignment.databinding.Custom4Binding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryAdapter.galleryViewHolder> {

    List<GalleryEntity> galleryEntityList;

    public void setList(List<GalleryEntity> galleryEntityList) {
        this.galleryEntityList = galleryEntityList;
    }

    @NonNull
    @NotNull
    @Override
    public galleryViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Custom4Binding binding = Custom4Binding.inflate(inflater,parent,false);
        galleryViewHolder holder =new galleryViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull galleryViewHolder holder, int position) {
            GalleryEntity galleryEntity = galleryEntityList.get(position);
            holder.binding.setGallery(galleryEntity);
    }

    @Override
    public int getItemCount() {
        return galleryEntityList.size();
    }

    public class galleryViewHolder extends RecyclerView.ViewHolder {
        private Custom4Binding binding;
        public galleryViewHolder(@NonNull Custom4Binding binding) {
            super(binding.getRoot());
            this.binding= binding;
        }
    }
}
