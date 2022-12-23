package com.example.storageassignment.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storageassignment.Dao.taskDao;
import com.example.storageassignment.Database.taskDatabase;
import com.example.storageassignment.databinding.FragmentNewTaskBinding;
import com.example.storageassignment.databinding.FragmentNoteListBinding;
import com.example.storageassignment.entity.TaskEntity;
import com.example.storageassignment.R;
import com.example.storageassignment.adapter.taskAdapter;
import com.example.storageassignment.databinding.FragmentTaskListBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskListFragment extends Fragment implements taskAdapter.taskClickListner {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskListFragment newInstance(String param1, String param2) {
        TaskListFragment fragment = new TaskListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    private FragmentTaskListBinding binding;
    private FragmentNewTaskBinding binding1;
    private List<TaskEntity> taskList;
    private taskDao dao;
    private taskDatabase db;
    private taskAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTaskListBinding.inflate(inflater,container,false);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext()));

        db = taskDatabase.getInstance(getContext());
        dao = db.taskDao();

        taskList = dao.getAlltask();
        adapter = new taskAdapter(this);
        adapter.settaskList(taskList);

        binding.recycler.setAdapter(adapter);

        ItemTouchHelper helper=new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position=viewHolder.getAdapterPosition();
                if(direction==ItemTouchHelper.LEFT){
                    // Delete operation
                    showConfirmDialog(position);
                }else{
                    // Edit operation
                    TaskEntity taskEntity = taskList.get(position);
                    Bundle arg = new Bundle();
                    arg.putParcelable("task",taskEntity);
                    Navigation.findNavController(getView()).navigate(R.id.action_taskListFragment_to_newTaskFragment,arg);

                }
            }

            @Override
            public void onChildDraw(@NonNull Canvas c, @NonNull RecyclerView recyclerView,
                                    @NonNull RecyclerView.ViewHolder viewHolder, float dX, float dY,
                                    int actionState, boolean isCurrentlyActive) {
                Paint paint=new Paint();
                View itemView=viewHolder.itemView;
                Bitmap bitmap;
                int height = itemView.getHeight();
                int width=height/3;

                if(actionState==ItemTouchHelper.ACTION_STATE_SWIPE){

                    if(dX>0){
                        // Edit icon with green color
                        paint.setColor(Color.GREEN);
                        c.drawRect(itemView.getLeft(),itemView.getTop(),dX,itemView.getBottom(),paint);
                        bitmap= BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_edit);
                        c.drawBitmap(bitmap,width, itemView.getTop()+width, paint);

                    }else if(dX<0){
                        // Delete icon with red color
                        paint.setColor(Color.RED);
                        c.drawRect(itemView.getRight()+dX,itemView.getTop(),itemView.getRight(),itemView.getBottom(), paint);
                        bitmap= BitmapFactory.decodeResource(getResources(), android.R.drawable.ic_menu_delete);
                        c.drawBitmap(bitmap,itemView.getRight()-(width*2), itemView.getTop()+width, paint);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);

            }
        });
        helper.attachToRecyclerView(binding.recycler);

//        Toast.makeText(getContext(),"hii",Toast.LENGTH_SHORT).show();
//        Date currentTime = Calendar.getInstance().getTime();
//        while (Calendar.getInstance().get(Calendar.MINUTE)/2==0){
//            int i = 1;
//            while (i<= taskList.size()){
//                if (currentTime.toString() == currentTime.toString() && taskList.get(i).status != "completed"){
//                    new AlertDialog.Builder(getContext())
//                            .setTitle("Alert")
//                            .setMessage("your task ")
//                            .create()
//                            .show();
//                }
//                Toast.makeText(getContext(),"hii",Toast.LENGTH_SHORT).show();
//                i++;
//            }
//        }
    }

    private void showConfirmDialog(int position) {
        new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.app_name))
                .setMessage("Are you sure want to Delete?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TaskEntity task = taskList.get(position);
                        dao.delete(task);

                        taskList = dao.getAlltask();
                        adapter.settaskList(taskList);
                        adapter.notifyDataSetChanged();

                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.notifyDataSetChanged();
                    }
                })
                .create().show();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull @NotNull Menu menu, @NonNull @NotNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu1,menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull @NotNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.newUser:
                Navigation.findNavController(getView()).navigate(R.id.action_taskListFragment_to_newTaskFragment);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void clickListner(TaskEntity task2) {
        task2.status = "completed";
        dao.update(task2);
        Toast.makeText(getContext(),"complete",Toast.LENGTH_SHORT).show();
        adapter.notifyDataSetChanged();
    }
}