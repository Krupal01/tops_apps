package com.example.ui_control.ImageGrid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;

import java.util.ArrayList;

public class imageAdapter extends RecyclerView.Adapter<imageAdapter.myViewHolder> {

   ArrayList<Integer> src;

    public void setimageAdapter(ArrayList<Integer> src) {
        this.src = src;
    }


    @NonNull
    @Override
    public imageAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.imageview_custom , parent , false );
        myViewHolder holder = new myViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull imageAdapter.myViewHolder holder, int position) {
         holder.imageView.setImageResource(src.get(position));
         holder.textView.setText("image :"+position);
         holder.ratingBar.setRating(position);

         holder.imageView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(v.getContext(),Integer.toString(position),Toast.LENGTH_LONG).show();
             }
         });

    }

    @Override
    public int getItemCount() {
        return src.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;
        private RatingBar ratingBar;
        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            textView = itemView.findViewById(R.id.text);
            ratingBar = itemView.findViewById(R.id.ratingbar);        }
    }
}
