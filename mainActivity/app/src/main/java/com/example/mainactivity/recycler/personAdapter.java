package com.example.mainactivity.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mainactivity.R;

import java.util.ArrayList;

public class personAdapter extends RecyclerView.Adapter<personAdapter.personViewHolder> {

    private ArrayList<person> people;

    public void setPeople(ArrayList<person> people) {
        this.people = people;
    }

    @NonNull
    @Override
    public personAdapter.personViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.person_row_item , parent , false);
        personViewHolder viewHolder = new personViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull personAdapter.personViewHolder holder, int position) {
        person person = people.get(position);
        holder.firstname.setText("firstname : "+ person.getFirstname());
    }

    @Override
    public int getItemCount() {
        return people.size();
    }

    public class personViewHolder extends RecyclerView.ViewHolder {
        private TextView firstname;
        public personViewHolder(@NonNull View itemView){
            super(itemView);
            firstname = itemView.findViewById(R.id.tvFirstName);

        }
    }
}
