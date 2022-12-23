package com.example.ui_control.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.databinding.FragmentEmployeeDetailSimpleAdapterBinding;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Employee_Detail_SimpleAdapter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Employee_Detail_SimpleAdapter extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String NAME = "name";
    private static final String POSITION = "position";


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Employee_Detail_SimpleAdapter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Employee_Detail_SimpleAdapter.
     */
    // TODO: Rename and change types and number of parameters
    public static Employee_Detail_SimpleAdapter newInstance(String param1, String param2) {
        Employee_Detail_SimpleAdapter fragment = new Employee_Detail_SimpleAdapter();
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

    private FragmentEmployeeDetailSimpleAdapterBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentEmployeeDetailSimpleAdapterBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<HashMap<String,String>> data = new ArrayList<>();

        HashMap<String,String> block= new HashMap<>();
        block.put(NAME ,"k");
        block.put(POSITION , "1");
        data.add(block);


        block= new HashMap<>();
        block.put(NAME ,"j");
        block.put(POSITION , "1");
        data.add(block);


        String from[] = {NAME,POSITION};
        int to[] = {android.R.id.text1, android.R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(getContext(),data, android.R.layout.simple_expandable_list_item_2,from,to);

        binding.list.setAdapter(adapter);
    }
}