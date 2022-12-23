package com.example.ui_control.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.databinding.FragmentSimpleAdapterBinding;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link simple_Adapter#newInstance} factory method to
 * create an instance of this fragment.
 */
public class simple_Adapter extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String EMPLOYEE = "employee";
    private static final String AGE = "age";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public simple_Adapter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment simple_Adapter.
     */
    // TODO: Rename and change types and number of parameters
    public static simple_Adapter newInstance(String param1, String param2) {
        simple_Adapter fragment = new simple_Adapter();
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

    private FragmentSimpleAdapterBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentSimpleAdapterBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<HashMap<String,String>> data = new ArrayList<>();

        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put(EMPLOYEE,"krupal");
        hashMap.put(AGE,"21");
        data.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(EMPLOYEE,"jignesh");
        hashMap.put(AGE,"21");
        data.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(EMPLOYEE,"karan");
        hashMap.put(AGE,"22");
        data.add(hashMap);

        hashMap = new HashMap<>();
        hashMap.put(EMPLOYEE,"meet");
        hashMap.put(AGE,"21");
        data.add(hashMap);

        String[] from={EMPLOYEE,AGE};


        int[] to={android.R.id.text1, android.R.id.text2};

        SimpleAdapter adapter = new SimpleAdapter(getContext(),data,android.R.layout.simple_expandable_list_item_2,from,to);

        binding.detail.setAdapter(adapter);
    }
}