package com.example.storageassignment.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.storageassignment.Dao.AssetDao;
import com.example.storageassignment.Database.taskDatabase;
import com.example.storageassignment.R;
import com.example.storageassignment.databinding.FragmentAssertDatabaseBinding;
import com.example.storageassignment.entity.first;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AssertDatabaseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssertDatabaseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AssertDatabaseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AssertDatabaseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AssertDatabaseFragment newInstance(String param1, String param2) {
        AssertDatabaseFragment fragment = new AssertDatabaseFragment();
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

    private FragmentAssertDatabaseBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentAssertDatabaseBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskDatabase taskDatabase = Room.databaseBuilder(getContext() , taskDatabase.class , "fromAssets")
                                                 .createFromAsset("database.db")
                                                 .build();

        AssetDao assetDao = taskDatabase.assetDao();

        List<first> item = assetDao.getalldata();
        ArrayAdapter<first> arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        binding.ListView.setAdapter(arrayAdapter);
    }
}