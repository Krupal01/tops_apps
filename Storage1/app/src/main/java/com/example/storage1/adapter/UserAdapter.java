package com.example.storage1.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storage1.databinding.CustumLayoutForListBinding;
import com.example.storage1.entity.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    private List<User> newUser;

    public void setnewUser(List<User> newUser) {
        this.newUser = newUser;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        CustumLayoutForListBinding binding = CustumLayoutForListBinding.inflate(inflater,parent,false);
        UserViewHolder holder = new UserViewHolder(binding);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        User theUser = newUser.get(position);
        holder.binding.setUser(theUser);
    }

    @Override
    public int getItemCount() {
        return newUser.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {
        private CustumLayoutForListBinding binding;
        public UserViewHolder(CustumLayoutForListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
