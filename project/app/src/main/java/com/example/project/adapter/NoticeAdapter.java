package com.example.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.databinding.CustomNoticeBinding;
import com.example.project.items.Notice;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewHolder> {

    ArrayList<Notice> note = new ArrayList<>();

    public void setNote(ArrayList<Notice> note) {
        this.note = note;
    }

    @NonNull
    @Override
    public NoticeAdapter.NoticeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustomNoticeBinding binding = CustomNoticeBinding.inflate(inflater,parent,false);
        NoticeViewHolder holder = new NoticeViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeAdapter.NoticeViewHolder holder, int position) {
        holder.binding.noticeText.setText(note.get(position).getNoticeText());
        holder.binding.noticeTitle.setText(note.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return note.size();
    }


    public class NoticeViewHolder extends RecyclerView.ViewHolder {

        private CustomNoticeBinding binding;

        public NoticeViewHolder(@NonNull CustomNoticeBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
