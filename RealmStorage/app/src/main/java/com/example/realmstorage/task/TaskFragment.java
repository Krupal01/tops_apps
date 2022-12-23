package com.example.realmstorage.task;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.realmstorage.R;
import com.example.realmstorage.databinding.ActivityMainBinding;
import com.example.realmstorage.databinding.FragmentTaskBinding;

import java.util.ArrayList;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskFragment extends Fragment {



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskFragment newInstance(String param1, String param2) {
        TaskFragment fragment = new TaskFragment();
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

    private FragmentTaskBinding binding;
    private Realm realm;
    private TaskObject task = null;
    private RealmResults<TaskObject> taskList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTaskBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RealmConfiguration config=new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .name(getString(R.string.app_name)).build();

        realm=Realm.getInstance(config);


        binding.submit.setOnClickListener(v->{
            if(task==null) {
                insertUser();
            }else{
                updateUser(task.id);
            }
        });

        getAllUsers();

        binding.TaskList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                showAlertDialog(position);
            }
        });
    }

    private void showAlertDialog(int position) {
        new AlertDialog.Builder(getContext())
                .setTitle("Select Operation")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        task=taskList.get(position);
                        binding.TaskId.setText(String.valueOf(task.id));
                        binding.TaskName.setText(task.taskName);


                        binding.TaskId.setEnabled(false);
                    }
                })
                .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        task=taskList.get(position);
                        deleteUser(task.id);
                    }
                })
                .create().show();
    }

    private void getAllUsers() {
        taskList=realm.where(TaskObject.class).findAll();
        ArrayAdapter<TaskObject> adapter=
                new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,taskList);
        binding.TaskList.setAdapter(adapter);
    }

    private void insertUser() {
        TaskObject theUser=new TaskObject();
        theUser.id=Integer.parseInt(binding.TaskId.getText().toString());
        theUser.taskName=binding.TaskName.getText().toString();


        realm.executeTransaction(transactionRealm  -> {
            transactionRealm.insert(theUser);
            Toast.makeText(getContext(), "User Saved!!!", Toast.LENGTH_SHORT).show();
        });
        reset();
        getAllUsers();
    }

    private void updateUser(int _id){
        realm.executeTransaction(transactionRealm->{
            TaskObject theUser=transactionRealm.where(TaskObject.class).equalTo("id",_id).findFirst();
            theUser.taskName=binding.TaskName.getText().toString();
        });
        reset();
        getAllUsers();
    }

    private void deleteUser(int _id ){
        realm.executeTransaction(transactionRealm->{
            TaskObject theUser=transactionRealm.where(TaskObject.class).equalTo("id",_id).findFirst();
            theUser.deleteFromRealm();
        });
        reset();
        getAllUsers();
    }

    private void reset() {
        task=null;
        binding.TaskId.setText("");
        binding.TaskName.setText("");
        binding.TaskId.setEnabled(true);
    }
}