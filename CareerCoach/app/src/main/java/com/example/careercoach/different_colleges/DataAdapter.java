package com.example.careercoach.different_colleges;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.DataRowItemBinding;



import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.DataViewHolder> {

    private ArrayList<Data> dataManagement;
    private ArrayList<Data> dataEngineering;
    private ArrayList<Data> dataMedical;
    private ArrayList<Data> dataArt;
    private ArrayList<Data> dataCommerce;
    private ArrayList<Data> dataLaw;
    private ArrayList<Data> dataArmy;
    private ArrayList<Data> dataMovie;

    /////////////////////////////////////////////////
    private onDataClickListener listener;
    public void setListener(onDataClickListener listener) {
        this.listener = listener;
    }
    public interface onDataClickListener{
        void onClickListener(int position);
    }
    //////////////////////////////////////////////////

    public void setDataManagement(ArrayList<Data> dataManagement) {
        this.dataManagement = dataManagement;
    }
    public void setDataEngineering(ArrayList<Data> dataEngineering) {
        this.dataEngineering = dataEngineering;
    }
    public void setDataMedical(ArrayList<Data> dataMedical) {
        this.dataMedical = dataMedical;
    }
    public void setDataArt(ArrayList<Data> dataArt) {
        this.dataArt = dataArt;
    }

    public void setDataCommerce(ArrayList<Data> dataCommerce) {
        this.dataCommerce = dataCommerce;
    }

    public void setDataLaw(ArrayList<Data> dataLaw) {
        this.dataLaw = dataLaw;
    }

    public void setDataArmy(ArrayList<Data> dataArmy) {
        this.dataArmy = dataArmy;
    }

    public void setDataMovie(ArrayList<Data> dataMovie) {
        this.dataMovie = dataMovie;
    }

    @NotNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        DataRowItemBinding binding=DataRowItemBinding.inflate(inflater,parent,false);
        DataViewHolder viewHolder=new DataViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DataViewHolder holder, int position) {

        if(dataManagement!=null){
            Data data=dataManagement.get(position);
            holder.binding.setData(data);
        }else if(dataEngineering!=null){
            Data data1=dataEngineering.get(position);
            holder.binding.setData(data1);
        }else if(dataMedical!=null){
            Data data3=dataMedical.get(position);
            holder.binding.setData(data3);
        }else if(dataArt!=null){
            Data data4=dataArt.get(position);
            holder.binding.setData(data4);
        }
        else if(dataCommerce!=null){
            Data data4=dataCommerce.get(position);
            holder.binding.setData(data4);
        }
        else if(dataLaw!=null){
            Data data4=dataLaw.get(position);
            holder.binding.setData(data4);
        }
        else if(dataArmy!=null){
            Data data4=dataArmy.get(position);
            holder.binding.setData(data4);
        }
        else if(dataMovie!=null){
            Data data4=dataMovie.get(position);
            holder.binding.setData(data4);
        }
        holder.itemView.setOnClickListener(v -> {
            listener.onClickListener(position);
        });
    }
    @Override
    public int getItemCount() {
        if(dataManagement!=null){
            return dataManagement.size();
        }else if(dataEngineering!=null){
            return dataEngineering.size();
        }else if (dataMedical!=null){
            return dataMedical.size();
        }else if(dataArt!=null){
            return dataArt.size();
        }
        else if(dataCommerce!=null){
            return dataCommerce.size();
        }
        else if(dataLaw!=null){
            return dataLaw.size();
        }
        else if(dataArmy!=null){
            return dataArmy.size();
        }else if(dataMovie!=null){
            return dataMovie.size();
        }

        return 0;
    }
    public class DataViewHolder extends RecyclerView.ViewHolder {
        private DataRowItemBinding binding;
        public DataViewHolder(@NonNull DataRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
