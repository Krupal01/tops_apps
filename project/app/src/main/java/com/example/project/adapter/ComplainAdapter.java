package com.example.project.adapter;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.databinding.CustomComplainBinding;
import com.example.project.items.Complain;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class ComplainAdapter extends RecyclerView.Adapter<ComplainAdapter.ComplainViewHolder> {

    public interface OnClick{
        void OnClick(Complain complain);
    }

    private OnClick listner;

    public ComplainAdapter(OnClick listner) {
        this.listner = listner;
    }

    ArrayList<Complain> complain = new ArrayList<>();

    public void setAdapterArray(ArrayList<Complain> complain) {
        this.complain = complain;
    }
    @NonNull
    @Override
    public ComplainViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomComplainBinding  binding = CustomComplainBinding.inflate(inflater,parent,false);
        ComplainViewHolder holder = new ComplainViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ComplainAdapter.ComplainViewHolder holder, int position) {
        Complain complain1 = complain.get(position);
        holder.binding.setComplain(complain1);

        holder.itemView.setOnClickListener(v -> {
            listner.OnClick(complain1);
        });

    }

    @Override
    public int getItemCount() {
        return complain.size();
    }

    public class ComplainViewHolder extends RecyclerView.ViewHolder {
        private CustomComplainBinding binding;

        public ComplainViewHolder(@NonNull CustomComplainBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }


    }
}
