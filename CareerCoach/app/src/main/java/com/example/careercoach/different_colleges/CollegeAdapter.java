package com.example.careercoach.different_colleges;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.CollegeRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CollegeAdapter extends RecyclerView.Adapter<CollegeAdapter.CollegeViewHolder> {

    private CollegeClickListener listener;
    public interface CollegeClickListener{
        void onCollegeClick(int position);
    }


    public void setCollegeDataArrayList(ArrayList<CollegeData> collegeDataArrayList , CollegeClickListener listener) {
        this.collegeDataArrayList = collegeDataArrayList;
        this.listener=listener;
    }
    ArrayList<CollegeData>collegeDataArrayList;


    @NotNull
    @Override
    public CollegeViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        CollegeRowItemBinding binding=CollegeRowItemBinding.inflate(inflater,parent,false);
        CollegeViewHolder viewHolder=new CollegeViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CollegeViewHolder holder, int position) {

        CollegeData data=collegeDataArrayList.get(position);
        holder.binding.setData(data);
        holder.binding.imageView8.setImageResource(data.id);
        holder.itemView.setOnClickListener(v -> {
            listener.onCollegeClick(position);
        });

    }

    @Override
    public int getItemCount() {
        return collegeDataArrayList.size();
    }

    public class CollegeViewHolder extends RecyclerView.ViewHolder {
        private  CollegeRowItemBinding binding;
        public CollegeViewHolder(@NonNull CollegeRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
