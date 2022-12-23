package com.example.careercoach.different_courses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.CourseRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    ArrayList<CourseData>dataArrayList;

    private CourseClickListener listener;
    public interface CourseClickListener{
        void onCourseClick(int position);
    }

    CourseAdapter(ArrayList<CourseData> dataArrayList,CourseClickListener listener){
        this.dataArrayList=dataArrayList;
        this.listener=listener;
    }
    @NotNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        CourseRowItemBinding binding=CourseRowItemBinding.inflate(inflater,parent,false);
        CourseViewHolder viewHolder=new CourseViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CourseViewHolder holder, int position) {
        CourseData data=dataArrayList.get(position);
        holder.binding.setData(data);
        holder.binding.imageCourse.setImageResource(data.id);
        holder.itemView.setOnClickListener(v -> {
            listener.onCourseClick(position);
        });
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class CourseViewHolder extends RecyclerView.ViewHolder {
        private CourseRowItemBinding binding;
        public CourseViewHolder(@NonNull CourseRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
