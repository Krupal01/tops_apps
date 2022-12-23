package com.example.storageassignment.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storageassignment.entity.TaskEntity;
import com.example.storageassignment.databinding.Custom1Binding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class taskAdapter extends RecyclerView.Adapter<taskAdapter.taskViewHolder> {


    private List<TaskEntity> task1;

    public interface taskClickListner{
        void clickListner(TaskEntity task2);
    }

    public taskClickListner listner;

    public taskAdapter(taskClickListner listner) {
        this.listner = listner;
    }

    public void settaskList(List<TaskEntity> task) {
        this.task1 = task;
    }

    @NonNull
    @NotNull
    @Override
    public taskAdapter.taskViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Custom1Binding binding = Custom1Binding.inflate(inflater,parent,false);
        taskViewHolder holder = new taskViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull taskAdapter.taskViewHolder holder, int position) {
            TaskEntity newtask = task1.get(position);
            holder.binding.setTask(newtask);

            holder.itemView.setOnClickListener(v -> {
                listner.clickListner(newtask);
            });
    }

    @Override
    public int getItemCount() {
        return task1.size();
    }



    public class taskViewHolder extends RecyclerView.ViewHolder {
        private Custom1Binding binding;
        public taskViewHolder(@NonNull Custom1Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
