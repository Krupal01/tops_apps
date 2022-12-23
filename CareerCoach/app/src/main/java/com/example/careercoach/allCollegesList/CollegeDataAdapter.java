package com.example.careercoach.allCollegesList;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.AllCollegeRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class CollegeDataAdapter extends RecyclerView.Adapter<CollegeDataAdapter.CollegeDataViewHolder> implements Filterable {

    private ArrayList<CollegeData>dataArrayList,exampleList;

    public CollegeDataAdapter(ArrayList<CollegeData>dataArrayList){
        this.dataArrayList=dataArrayList;
        exampleList=new ArrayList<>(dataArrayList);
    }

    ///////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////////////////////////////////////////////////


    @NotNull
    @Override
    public CollegeDataViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        AllCollegeRowItemBinding binding=AllCollegeRowItemBinding.inflate(inflater,parent,false);
        CollegeDataViewHolder viewHolder=new CollegeDataViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull CollegeDataViewHolder holder, int position) {
        CollegeData data=dataArrayList.get(position);
        holder.binding.setData(data);


    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<CollegeData> filteredList=new ArrayList<>();
                if(constraint==null || constraint.length()==0){
                    filteredList.addAll(exampleList);

                }else {
                    String pattern=constraint.toString().toLowerCase().trim();
                    for (CollegeData data:exampleList){
                        if(data.getCollegeName().toLowerCase().contains(pattern)){
                            filteredList.add(data);
                        }
                    }
                }
                FilterResults results=new FilterResults();
                results.values=filteredList;
                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                dataArrayList.clear();
                dataArrayList.addAll((List)results.values);
                notifyDataSetChanged();
            }
        };
    }


    public class CollegeDataViewHolder extends RecyclerView.ViewHolder {
        private  AllCollegeRowItemBinding binding;
        public CollegeDataViewHolder(@NonNull  AllCollegeRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
