package com.example.project.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.databinding.CustomWantedMissingBinding;
import com.example.project.items.Person;

import java.net.URL;
import java.util.ArrayList;

public class WantedAdapter extends RecyclerView.Adapter<WantedAdapter.WantedViewHolder> {

    ArrayList<Person> Wanted = new ArrayList<>();

    public void setPerson(ArrayList<Person> wantedPerson) {
        this.Wanted = wantedPerson;
    }

    @NonNull
    @Override
    public WantedAdapter.WantedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomWantedMissingBinding binding = CustomWantedMissingBinding.inflate(inflater,parent,false);
        WantedViewHolder viewHolder = new WantedViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WantedAdapter.WantedViewHolder holder, int position) {
        holder.binding.setPerson(Wanted.get(position));
        URL url = null;
        try {
            url = new URL(Wanted.get(position).getPath());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.binding.ProfilePicture.setImageBitmap(bmp);
        } catch (Exception e) {
            Log.i("WantedImage",e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return Wanted.size();
    }

    public class WantedViewHolder extends RecyclerView.ViewHolder {
        private CustomWantedMissingBinding binding;
        public WantedViewHolder(@NonNull CustomWantedMissingBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
