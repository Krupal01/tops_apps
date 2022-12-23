package com.example.careercoach.different_courses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.EntityRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class EntityAdapter extends RecyclerView.Adapter<EntityAdapter.EntityViewHolder> {
    ArrayList<DataEntity>ugArrayList;
    ArrayList<DataEntity>pgArrayList;
    ArrayList<DataEntity>diplomaArrayList;
    ArrayList<DataEntity>phdArrayList;
    ArrayList<DataEntity>engineeringArrayList;
    ArrayList<DataEntity>medicalArrayList;
    ArrayList<DataEntity>managementArrayList;
    ArrayList<DataEntity>lawArrayList;

    ///////////////////////////////////////////////////////////////////////////////////////
    private OnEntityClickListener listener;
    public void setListener(OnEntityClickListener listener) {
        this.listener = listener;
    }
    public interface OnEntityClickListener{
        void onClickListener(int position);
    }
/////////////////////////////////////////////////////////////////////////////////////

    public void setUgArrayList(ArrayList<DataEntity> ugArrayList) {
        this.ugArrayList = ugArrayList;
    }

    public void setPgArrayList(ArrayList<DataEntity> pgArrayList) {
        this.pgArrayList = pgArrayList;
    }

    public void setDiplomaArrayList(ArrayList<DataEntity> diplomaArrayList) {
        this.diplomaArrayList = diplomaArrayList;
    }

    public void setPhdArrayList(ArrayList<DataEntity> phdArrayList) {
        this.phdArrayList = phdArrayList;
    }

    public void setEngineeringArrayList(ArrayList<DataEntity> engineeringArrayList) {
        this.engineeringArrayList = engineeringArrayList;
    }

    public void setMedicalArrayList(ArrayList<DataEntity> medicalArrayList) {
        this.medicalArrayList = medicalArrayList;
    }

    public void setManagementArrayList(ArrayList<DataEntity> managementArrayList) {
        this.managementArrayList = managementArrayList;
    }

    public void setLawArrayList(ArrayList<DataEntity> lawArrayList) {
        this.lawArrayList = lawArrayList;
    }

    @NotNull
    @Override
    public EntityViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater=LayoutInflater.from(parent.getContext());
        EntityRowItemBinding binding=EntityRowItemBinding.inflate(layoutInflater,parent,false);
        EntityViewHolder viewHolder=new EntityViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull EntityViewHolder holder, int position) {
        if(ugArrayList!=null){
            DataEntity data1=ugArrayList.get(position);
            holder.binding.setData(data1);
        }else if(pgArrayList!=null){
            DataEntity data2=pgArrayList.get(position);
            holder.binding.setData(data2);
        }else if(diplomaArrayList!=null){
            DataEntity data3=diplomaArrayList.get(position);
            holder.binding.setData(data3);
        }else if(phdArrayList!=null){
            DataEntity data4=phdArrayList.get(position);
            holder.binding.setData(data4);
        }else if(engineeringArrayList!=null){
            DataEntity data5=engineeringArrayList.get(position);
            holder.binding.setData(data5);
        }else if(medicalArrayList!=null){
            DataEntity data6=medicalArrayList.get(position);
            holder.binding.setData(data6);
        }else if(managementArrayList!=null){
            DataEntity data7=managementArrayList.get(position);
            holder.binding.setData(data7);
        }else if(lawArrayList!=null){
            DataEntity data8=lawArrayList.get(position);
            holder.binding.setData(data8);
        }
        holder.itemView.setOnClickListener(v -> {
            listener.onClickListener(position);
        });

    }

    @Override
    public int getItemCount() {
        if(ugArrayList!=null){
            return ugArrayList.size();
        }else if(pgArrayList!=null){
            return pgArrayList.size();
        }else if(diplomaArrayList!=null){
            return diplomaArrayList.size();
        }else if(phdArrayList!=null){
            return phdArrayList.size();
        }else if(engineeringArrayList!=null){
            return engineeringArrayList.size();
        }else if(medicalArrayList!=null){
            return medicalArrayList.size();
        }else if(managementArrayList!=null){
            return managementArrayList.size();
        }else if(lawArrayList!=null){
            return lawArrayList.size();
        }
        return 0;
    }

    public class EntityViewHolder extends RecyclerView.ViewHolder {
        private EntityRowItemBinding binding;
        public EntityViewHolder(@NonNull EntityRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
