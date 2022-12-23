package com.example.careercoach.different_careers;

import android.content.Context;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.careercoach.databinding.MyDataRowItemBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyDataViewHolder> {
    ArrayList<MyData>financeData;
    ArrayList<MyData>technologyData;
    ArrayList<MyData>marketingData;
    ArrayList<MyData>humanResourceData;
    ArrayList<MyData>accountingData;
    ArrayList<MyData>ngoData;
    ArrayList<MyData>healthcareData;

    private TextToSpeech tts;
    private Context context;


    ////////////////////////////////////////////////////////////////  listener
    public onMyDataClickListener listener;
    public void setListener(onMyDataClickListener listener) {
        this.listener = listener;
    }
    public interface onMyDataClickListener{
        void onDataClickListener(int position);
    }
    ///////////////////////////////////////////////////////////////////


    public void setFinanceData(ArrayList<MyData> financeData) {
        this.financeData = financeData;
    }

    public void setTechnologyData(ArrayList<MyData> technologyData) {
        this.technologyData = technologyData;
    }

    public void setMarketingData(ArrayList<MyData> marketingData) {
        this.marketingData = marketingData;
    }

    public void setHumanResourceData(ArrayList<MyData> humanResourceData) {
        this.humanResourceData = humanResourceData;
    }

    public void setAccountingData(ArrayList<MyData> accountingData) {
        this.accountingData = accountingData;
    }

    public void setNgoData(ArrayList<MyData> ngoData) {
        this.ngoData = ngoData;
    }

    public void setHealthcareData(ArrayList<MyData> healthcareData) {
        this.healthcareData = healthcareData;
    }

    @NotNull
    @Override
    public MyDataViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        MyDataRowItemBinding binding=MyDataRowItemBinding.inflate(inflater,parent,false);
        MyDataViewHolder viewHolder=new MyDataViewHolder(binding);
        context=parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull MyDataViewHolder holder, int position) {



//        holder.binding.imageViewSpeakPlay.setOnClickListener(v -> {
//            if(accountingData!=null){
//                speak(accountingData,position);
//            }else if(technologyData!=null){
//                speak(technologyData,position);
//            }else if(financeData!=null){
//                speak(financeData,position);
//            }else if(marketingData!=null){
//                speak(marketingData,position);
//            }else if(humanResourceData!=null){
//                speak(humanResourceData,position);
//            }else if(ngoData!=null){
//                speak(ngoData,position);
//            }else if(healthcareData!=null){
//                speak(healthcareData,position);
//            }
//
//        });

        if(financeData!=null){
            MyData data1=financeData.get(position);
            holder.binding.setData(data1);
        }else if(technologyData!=null){
            MyData data2=technologyData.get(position);
            holder.binding.setData(data2);
        }else if(marketingData!=null){
            MyData data2=marketingData.get(position);
            holder.binding.setData(data2);
        }else if(humanResourceData!=null){
            MyData data2=humanResourceData.get(position);
            holder.binding.setData(data2);
        }else if(accountingData!=null){
            MyData data2=accountingData.get(position);
            holder.binding.setData(data2);
        }else if(ngoData!=null){
            MyData data2=ngoData.get(position);
            holder.binding.setData(data2);
        }else if(healthcareData!=null){
            MyData data2=healthcareData.get(position);
            holder.binding.setData(data2);
        }
        //////////////////////////////
        holder.itemView.setOnClickListener(v -> {
            listener.onDataClickListener(position);
        });
        /////////////////////////////
    }
    @Override
    public int getItemCount() {
        if(financeData!=null){
            return financeData.size();
        }else if(technologyData!=null){
            return technologyData.size();
        }else if(marketingData!=null){
            return marketingData.size();
        }else if(humanResourceData!=null){
            return humanResourceData.size();
        }else if(accountingData!=null){
            return accountingData.size();
        }else if(ngoData!=null){
            return ngoData.size();
        }else if(healthcareData!=null){
            return healthcareData.size();
        }
        return 0;
    }

    public class MyDataViewHolder extends RecyclerView.ViewHolder {
        private MyDataRowItemBinding binding;
        public MyDataViewHolder(@NonNull MyDataRowItemBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

    }
}
