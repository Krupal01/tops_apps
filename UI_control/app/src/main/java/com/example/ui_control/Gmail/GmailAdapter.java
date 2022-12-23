package com.example.ui_control.Gmail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;

import java.util.ArrayList;

public class GmailAdapter extends RecyclerView.Adapter<GmailAdapter.GmailViewHolder> {


    public interface onClick{
        void setOnclick(Gmail gmail);
    }

   public onClick Listner;

    public GmailAdapter(onClick listner) {
        Listner = listner;
    }

    ArrayList<Gmail> gmail1 = new ArrayList<>();



    public void setGmailAdapter(ArrayList<Gmail> gmail) {
        this.gmail1 = gmail;
    }

    @NonNull
    @Override
    public GmailAdapter.GmailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.gmailcustom , parent , false);
        GmailViewHolder holder = new GmailViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GmailAdapter.GmailViewHolder holder, int position) {
        Gmail Gmail2 = gmail1.get(position);
        holder.name.setText(Gmail2.getTitle());
        holder.subject.setText(Gmail2.getSubject());

        holder.itemView.setOnClickListener(v -> {
            Listner.setOnclick(Gmail2);
        });


    }

    @Override
    public int getItemCount() {
        return gmail1.size();
    }

    public class GmailViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView subject;
        public GmailViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            subject = itemView.findViewById(R.id.subject);
        }
    }
}
