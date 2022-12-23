package com.example.storageassignment.adapter;

import android.app.AlertDialog;
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

import com.example.storageassignment.R;
import com.example.storageassignment.databinding.Custom1Binding;
import com.example.storageassignment.databinding.Custom2Binding;
import com.example.storageassignment.entity.NoteEntity;
import com.example.storageassignment.entity.TaskEntity;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class noteAdapter extends RecyclerView.Adapter<noteAdapter.noteViewHolder> {

    private List<NoteEntity> note;

    public interface noteClickListner{
        void noteclickListner(NoteEntity note);
    }

    public noteAdapter.noteClickListner listner;

    public noteAdapter(noteAdapter.noteClickListner listner) {
        this.listner = listner;
    }
    public void setNote(List<NoteEntity> note) {
        this.note = note;
    }

    @NonNull
    @NotNull
    @Override
    public noteAdapter.noteViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        Custom2Binding binding = Custom2Binding.inflate(inflater,parent,false);
        noteViewHolder holder = new noteViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull noteAdapter.noteViewHolder holder, int position) {
        NoteEntity newNote = note.get(position);
        holder.binding.setNote(newNote);

        holder.itemView.setOnClickListener(v -> {
            listner.noteclickListner(newNote);
        });
    }

    @Override
    public int getItemCount() {
        return note.size();
    }

    public class noteViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Custom2Binding binding;
        public noteViewHolder(@NonNull Custom2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.noteDetail.setOnClickListener(this);
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
                                note.remove(note.get(getAdapterPosition()));
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

                            NoteEntity noteEntity = note.get(getAdapterPosition());
                            Bundle arg = new Bundle();
                            arg.putParcelable("note",noteEntity);
                            Navigation.findNavController(v).navigate(R.id.action_noteListFragment_to_newNoteFragment,arg);
                            break;

                    }
                    return true;
                }
            });
            menu.inflate(R.menu.menu2);
            menu.show();
        }
    }
}
