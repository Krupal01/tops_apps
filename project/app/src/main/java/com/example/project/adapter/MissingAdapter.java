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

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class MissingAdapter extends RecyclerView.Adapter<MissingAdapter.MissingViewHolder> {

    ArrayList<Person> Missing = new ArrayList<>();

    public void setPerson(ArrayList<Person> missing) {
        this.Missing = missing;
    }

    @NonNull
    @Override
    public MissingAdapter.MissingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomWantedMissingBinding binding = CustomWantedMissingBinding.inflate(inflater,parent,false);
        MissingViewHolder viewHolder = new MissingViewHolder(binding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MissingAdapter.MissingViewHolder holder, int position) {
        holder.binding.setPerson(Missing.get(position));
        //url to bitmap image
        URL url = null;
        try {
            url = new URL(Missing.get(position).getPath());
            Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            holder.binding.ProfilePicture.setImageBitmap(bmp);
        } catch (Exception e) {
            Log.i("MissingImage",e.toString());
        }
    }

    @Override
    public int getItemCount() {
        return Missing.size();
    }


    public class MissingViewHolder extends RecyclerView.ViewHolder {
        private CustomWantedMissingBinding binding;
        public MissingViewHolder(@NonNull CustomWantedMissingBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }
    }
}
