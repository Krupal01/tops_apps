package com.example.storageassignment.taska;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Query;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.storageassignment.Database.taskDatabase;
import com.example.storageassignment.R;
import com.example.storageassignment.databinding.FragmentATaskListBinding;
import com.example.storageassignment.entity.TaskEntity;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ATaskListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ATaskListFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ATaskListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ATaskListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ATaskListFragment newInstance(String param1, String param2) {
        ATaskListFragment fragment = new ATaskListFragment();
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

    private FragmentATaskListBinding binding;
    private taskDatabase db;
    private ATaskDao dao;
    private List<ATaskEntity> taskList;
    private ATaskAdapter aTaskAdapter;
    private List<ATaskEntity> filteredlist;
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        binding = FragmentATaskListBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.AtaskRecyclere.setLayoutManager(new LinearLayoutManager(getContext()));

        db = taskDatabase.getInstance(getContext());
        dao = db.ATaskDao();
        taskList = dao.getAllATask();
        context = getContext();
        aTaskAdapter = new ATaskAdapter();
        aTaskAdapter.setATaskAdapter(taskList);
        aTaskAdapter.setATask(db,dao,context);

        binding.AtaskRecyclere.setAdapter(aTaskAdapter);

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
                Navigation.findNavController(getView()).navigate(R.id.action_ATaskListFragment_to_ANewTaskFragment);
                break;

            case R.id.search:
                SearchView searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        Toast.makeText(getContext(),"hii",Toast.LENGTH_LONG).show();
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        taskList = dao.findUserWithName(newText);
                        aTaskAdapter.setATaskAdapter(taskList);
                        binding.AtaskRecyclere.setAdapter(aTaskAdapter);
                        return false;
                    }
                });

            case R.id.sortname:
                taskList = dao.SortByName();
                aTaskAdapter.setATaskAdapter(taskList);
                binding.AtaskRecyclere.setAdapter(aTaskAdapter);

            case R.id.sortdate:
                taskList = dao.SortByDate();
                aTaskAdapter.setATaskAdapter(taskList);
                binding.AtaskRecyclere.setAdapter(aTaskAdapter);



        }
        return super.onOptionsItemSelected(item);
    }

}