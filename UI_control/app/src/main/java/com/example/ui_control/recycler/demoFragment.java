package com.example.ui_control.recycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ui_control.databinding.FragmentDemoBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link demoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class demoFragment extends Fragment implements demoAdapter.democlickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public demoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment demoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static demoFragment newInstance(String param1, String param2) {
        demoFragment fragment = new demoFragment();
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

    private FragmentDemoBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       binding = FragmentDemoBinding.inflate(inflater,container,false);
       return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recycler1.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<demo> demos = new ArrayList<>();
        demos.add(new demo("k","d","3"));
        demos.add(new demo("k","d","3"));
        demos.add(new demo("k","d","3"));

        demoAdapter adapter = new demoAdapter(this);
        adapter.setdemo(demos);

        binding.recycler1.setAdapter(adapter);

    }

    @Override
    public void clickListner(demo demo) {
        Toast toast= Toast.makeText(getContext() , demo.getName() , Toast.LENGTH_LONG);
        toast.show();
    }
}