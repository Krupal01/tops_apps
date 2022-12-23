package com.example.ui_control.inputFormEmployee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;

import java.util.ArrayList;

public class empAdapter extends RecyclerView.Adapter<empAdapter.empViewHolder> {

    ArrayList<emp> data = new ArrayList<>();

    public void setempAdapter(ArrayList<emp> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public empAdapter.empViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.textline_four , parent,false);
        empViewHolder holder = new empViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull empAdapter.empViewHolder holder, int position) {
        emp emp = data.get(position);
        holder.firstname.setText(emp.getFirstname());
        holder.lastname.setText(emp.getLastname());
        holder.mobile.setText(emp.getMobile());
        holder.email.setText(emp.getEmail());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class empViewHolder extends RecyclerView.ViewHolder {
        private TextView firstname, lastname, mobile, email;
        public empViewHolder(@NonNull View itemView) {
            super(itemView);
            firstname = itemView.findViewById(R.id.text1);
            lastname = itemView.findViewById(R.id.text2);
            mobile = itemView.findViewById(R.id.text3);
            email = itemView.findViewById(R.id.text4);

        }
    }
}
