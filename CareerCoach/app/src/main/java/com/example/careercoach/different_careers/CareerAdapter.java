package com.example.careercoach.different_careers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.CareerRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CareerAdapter extends RecyclerView.Adapter<CareerAdapter.CareerViewHolder> {
    ArrayList<CareerData>dataArrayList;

    public interface OnCareerClickListener{
        void onCareerClick(int position);
    }

    private OnCareerClickListener listener;


    public CareerAdapter(ArrayList<CareerData>dataArrayList,OnCareerClickListener listener){
        this.dataArrayList=dataArrayList;
        this.listener=listener;
    }
    @NotNull
    @Override
    public CareerViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        CareerRowItemBinding binding=CareerRowItemBinding.inflate(inflater,parent,false);
        CareerViewHolder viewHolder=new CareerViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CareerViewHolder holder, int position) {
        CareerData data=dataArrayList.get(position);
        holder.binding.setData(data);
        holder.itemView.setOnClickListener(v -> {
            listener.onCareerClick(position);
        });
        holder.binding.imageCareer.setImageResource(data.id);
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class CareerViewHolder extends RecyclerView.ViewHolder {
        private CareerRowItemBinding binding;
        public CareerViewHolder(@NonNull CareerRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
