package com.example.careercoach.jobs;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.JobRowItemBinding;
import com.example.careercoach.different_careers.MyData;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class JobsAdapter extends RecyclerView.Adapter<JobsAdapter.JobsViewHolder> {

    ArrayList<JobData>jobDataArrayList;

    public void setJobDataArrayList(ArrayList<JobData> jobDataArrayList) {
        this.jobDataArrayList = jobDataArrayList;
    }

    @NotNull
    @Override
    public JobsViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        JobRowItemBinding binding=JobRowItemBinding.inflate(inflater,parent,false);
        JobsViewHolder viewHolder=new JobsViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull JobsViewHolder holder, int position) {
        JobData data1=jobDataArrayList.get(position);
        holder.binding.setData(data1);
    }

    @Override
    public int getItemCount() {
        return jobDataArrayList.size();
    }

    public class JobsViewHolder extends RecyclerView.ViewHolder {
        private JobRowItemBinding binding;
        public JobsViewHolder(@NonNull JobRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
