package com.example.ui_control.employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;

import java.util.ArrayList;

public class employeeAdapter extends RecyclerView.Adapter<employeeAdapter.employeViewHolder> {

   private ArrayList<employee> detail;

   public interface clickListener{
       void clickListener(employee employee);
   }

   private clickListener listener;

    public employeeAdapter(clickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public employeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.costum_layout,parent,false);
        employeViewHolder viewHolder = new employeViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull employeViewHolder holder, int position) {
       employee employee = detail.get(position);
        holder.name.setText(employee.getName());
        holder.position.setText(employee.getPosition());
        holder.date.setText(employee.getDate());
        holder.imageView.setImageResource(employee.getSrc());

        holder.itemView.setOnClickListener(v -> {
            listener.clickListener(employee);
        });

    }

    @Override
    public int getItemCount() {
        return detail.size();
    }

    public void setdata(ArrayList<employee> data) {
        this.detail = data;
    }

    public class employeViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView position;
        private TextView date;
        private ImageView imageView;
        public employeViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            position = itemView.findViewById(R.id.position);
            date = itemView.findViewById(R.id.date);
            imageView = itemView.findViewById(R.id.imageView2);
        }
    }
}
