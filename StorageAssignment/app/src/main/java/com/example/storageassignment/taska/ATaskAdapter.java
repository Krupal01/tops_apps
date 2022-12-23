package com.example.storageassignment.taska;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storageassignment.Database.taskDatabase;
import com.example.storageassignment.R;
import com.example.storageassignment.adapter.taskAdapter;
import com.example.storageassignment.databinding.Custom1Binding;
import com.example.storageassignment.databinding.Custom3Binding;
import com.example.storageassignment.entity.NoteEntity;
import com.example.storageassignment.entity.TaskEntity;

import org.jetbrains.annotations.NotNull;

import java.text.Format;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ATaskAdapter extends RecyclerView.Adapter<ATaskAdapter.AtaskViewHolder> {
    private taskDatabase db;
    private ATaskDao dao;
    private Context context;
    List<ATaskEntity> Atask;
    int dayDifference;

    public void setATask(taskDatabase db, ATaskDao dao, Context context) {
        this.db = db;
        this.dao = dao;
        this.context = context;
    }

    public void setATaskAdapter(List<ATaskEntity> atask) {
        this.Atask = atask;
    }

    @NonNull
    @NotNull
    @Override
    public ATaskAdapter.AtaskViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Custom3Binding binding = Custom3Binding.inflate(inflater,parent,false);
        AtaskViewHolder holder = new AtaskViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ATaskAdapter.AtaskViewHolder holder, int position) {
        ATaskEntity aTaskEntity = Atask.get(position);
        holder.binding.setTask(aTaskEntity);

       if (aTaskEntity.priority.equals("Completed")){
           holder.binding.Custom3.setBackgroundColor(Color.GRAY);
        }

        SimpleDateFormat myFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        try {
            Date date1 = myFormat.parse(aTaskEntity.date);
            Date date2 = myFormat.parse(cal.getTime().toString());
            long diff = date2.getTime() - date1.getTime();
            dayDifference = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (dayDifference < 0 && !aTaskEntity.priority.equals("Completed") ) {
            holder.binding.Custom3.setBackgroundColor(Color.BLUE);
        }else if(dayDifference ==0&& !aTaskEntity.priority.equals("Completed")){
            holder.binding.Custom3.setBackgroundColor(Color.RED);
        }else if(dayDifference ==1 || dayDifference == 2 && !aTaskEntity.priority.equals("Completed")){
            holder.binding.Custom3.setBackgroundColor(Color.BLUE);
        }else if(dayDifference >=3 && !aTaskEntity.priority.equals("Completed")){
            holder.binding.Custom3.setBackgroundColor(Color.GREEN);
        }
    }

    @Override
    public int getItemCount() {
        return Atask.size();
    }


    public class AtaskViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Custom3Binding binding;
        public AtaskViewHolder(@NonNull Custom3Binding binding) {
            super(binding.getRoot());
                this.binding = binding;
                binding.taskname1.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            PopupMenu menu = new PopupMenu(v.getContext(), v);
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    RecyclerView.ViewHolder holder = (RecyclerView.ViewHolder) v.getTag();
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.delet:
                            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                            builder.setMessage("are you sure ..");
                            builder.setPositiveButton("yes", (dialog, which) -> {
                                Atask.remove(Atask.get(getAdapterPosition()));
                                dao.TaskDelete(Atask.get(getAdapterPosition()));
                                notifyDataSetChanged();

                            });
                            builder.setNegativeButton("no", (dialog, which) -> {
                                Toast toast = Toast.makeText(v.getContext(),"delet unsucessful",Toast.LENGTH_SHORT);
                                toast.show();
                            });
                            builder.setNeutralButton("cancle", (dialog, which) -> {
                                Toast toast = Toast.makeText(v.getContext(),"delet cancle",Toast.LENGTH_SHORT);
                                toast.show();
                            });
                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                            break;

                        case R.id.update:

                            ATaskEntity aTaskEntity = Atask.get(getAdapterPosition());
                            Bundle arg = new Bundle();
                            arg.putParcelable("ATask",aTaskEntity);
                            Navigation.findNavController(v).navigate(R.id.action_ATaskListFragment_to_ANewTaskFragment,arg);
                            break;

                        case R.id.complete:
                            ATaskEntity aTaskEntity1 = Atask.get(getAdapterPosition());
                            aTaskEntity1.priority = "Completed";
                            dao.TaskUpdate(Atask.get(getAdapterPosition()));

                            notifyDataSetChanged();

                    }
                    return true;
                }
            });
            menu.inflate(R.menu.menu2);
            menu.show();

        }
    }
}
