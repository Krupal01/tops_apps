package com.example.ui_control.menu;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ui_control.R;

import java.util.ArrayList;

public class RecyclerAdater extends RecyclerView.Adapter<RecyclerAdater.RecyclerViewHolder> {


    public interface clickListner{
        void SetclickListner(View view);
    }

    clickListner listner;

    public RecyclerAdater(clickListner listner) {
        this.listner = listner;
    }

    ArrayList<String> items = new ArrayList<>();

    public void setRecyclerAdater(ArrayList<String> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(android.R.layout.simple_expandable_list_item_1,parent,false);
        RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textView.setText(items.get(position));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView textView;
        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
            textView.setOnClickListener(this);
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
                                items.remove(textView.getText().toString());
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

                        case R.id.edit:
                            AlertDialog.Builder builder1 = new AlertDialog.Builder(v.getContext());
                            LayoutInflater li = LayoutInflater.from(v.getContext());
                            View promptsView = li.inflate(R.layout.coustem_alert_dialog, null);
                            builder1.setView(promptsView);
                            final EditText newName = (EditText) promptsView.findViewById(R.id.new_name);
                            builder1.setPositiveButton("ok", (dialog, which) -> {
                                String new_name = newName.getText().toString();

                            });
                            builder1.setNeutralButton("cancle", (dialog, which) -> {
                                Toast toast = Toast.makeText(v.getContext(),"Edit cancle",Toast.LENGTH_SHORT);
                                toast.show();
                            });
                            AlertDialog alertDialog1 = builder1.create();
                            alertDialog1.show();
                            break;

                        case R.id.exit:
                            break;

                    }
                    return true;
                }
            });
            menu.inflate(R.menu.manu1);
            menu.show();

        }
    }
}
