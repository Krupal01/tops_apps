package com.example.careercoach.afterWhat;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.AfterRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class AfterDataAdapter extends RecyclerView.Adapter<AfterDataAdapter.AfterDataViewHolder> {

    private ArrayList<AfterData>itiData;
    private ArrayList<AfterData>scaData;
    private ArrayList<AfterData>scienceData;
    private ArrayList<AfterData>commerceData;
    private ArrayList<AfterData>artsData;

    private onAfterDataClickListener listener;
    public void setListener(onAfterDataClickListener listener) {
        this.listener = listener;
    }
    public interface onAfterDataClickListener{
        void onClickListener(int position);
    }


    public void setItiData(ArrayList<AfterData> itiData) {
        this.itiData = itiData;
    }

    public void setScaData(ArrayList<AfterData> scaData) {
        this.scaData = scaData;
    }

    public void setScienceData(ArrayList<AfterData> scienceData) {
        this.scienceData = scienceData;
    }

    public void setCommerceData(ArrayList<AfterData> commerceData) {
        this.commerceData = commerceData;
    }

    public void setArtsData(ArrayList<AfterData> artsData) {
        this.artsData = artsData;
    }

    @NotNull
    @Override
    public AfterDataViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        AfterRowItemBinding binding=AfterRowItemBinding.inflate(inflater,parent,false);
        AfterDataViewHolder viewHolder=new AfterDataViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull AfterDataViewHolder holder, int position) {
        if(itiData!=null){
            AfterData data1=itiData.get(position);
            holder.binding.setData(data1);
        }else if(scaData!=null){
            AfterData data2=scaData.get(position);
            holder.binding.setData(data2);
        }else if(scienceData!=null){
            AfterData data2=scienceData.get(position);
            holder.binding.setData(data2);
        }else if(commerceData!=null){
            AfterData data2=commerceData.get(position);
            holder.binding.setData(data2);
        }else if(artsData!=null){
            AfterData data2=artsData.get(position);
            holder.binding.setData(data2);
        }
        holder.itemView.setOnClickListener(v -> {
            listener.onClickListener(position);
        });
    }
    @Override
    public int getItemCount() {
        if(itiData!=null){
            return itiData.size();
        }else if(scaData!=null){
            return scaData.size();
        }else if(scienceData!=null){
            return scienceData.size();
        }else if(commerceData!=null){
            return commerceData.size();
        }else if(artsData!=null){
            return artsData.size();
        }
        return 0;
    }

    public class AfterDataViewHolder extends RecyclerView.ViewHolder {
        private AfterRowItemBinding binding;
        public AfterDataViewHolder(@NonNull AfterRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
