package com.example.careercoach.different_exams.exams;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.ExamRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ExamDataAdapter extends RecyclerView.Adapter<ExamDataAdapter.ExamDataViewHolder> {

    private ArrayList<ExamData>entranceData;
    private ArrayList<ExamData>competitiveData;

    private onExamDataClickListener listener;

    public void setListener(onExamDataClickListener listener) {
        this.listener = listener;
    }
    public interface onExamDataClickListener{
        void onClickListener(int position);
    }

    public void setEntranceData(ArrayList<ExamData> entranceData) {
        this.entranceData = entranceData;
    }
    public void setCompetitiveData(ArrayList<ExamData> competitiveData) {
        this.competitiveData = competitiveData;
    }

    @NotNull
    @Override
    public ExamDataViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        ExamRowItemBinding binding=ExamRowItemBinding.inflate(inflater,parent,false);
        ExamDataViewHolder viewHolder=new ExamDataViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ExamDataViewHolder holder, int position) {

        if(entranceData!=null){
            ExamData data1=entranceData.get(position);
            holder.binding.setData(data1);
        }else if(competitiveData!=null){
            ExamData data2=competitiveData.get(position);
            holder.binding.setData(data2);
        }
        holder.itemView.setOnClickListener(v -> {
            listener.onClickListener(position);
        });

    }

    @Override
    public int getItemCount() {
        if(entranceData!=null){
            return entranceData.size();
        }else if(competitiveData!=null){
            return competitiveData.size();
        }
        return 0;
    }

    public class ExamDataViewHolder extends RecyclerView.ViewHolder {
        private ExamRowItemBinding binding;
        public ExamDataViewHolder(@NonNull ExamRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
