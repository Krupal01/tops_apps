package com.example.ui_control.Fragment;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentRadioColorBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link radioColor#newInstance} factory method to
 * create an instance of this fragment.
 */
public class radioColor extends Fragment implements RadioGroup.OnCheckedChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public radioColor() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment radioColor.
     */
    // TODO: Rename and change types and number of parameters
    public static radioColor newInstance(String param1, String param2) {
        radioColor fragment = new radioColor();
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

    private FragmentRadioColorBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRadioColorBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.colors.setOnCheckedChangeListener(this);

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.red:
                binding.RadioColor.setBackgroundColor(Color.RED);
                break;
            case R.id.green:
                binding.RadioColor.setBackgroundColor(Color.GREEN);
                break;
            case R.id.yellow:
                binding.RadioColor.setBackgroundColor(Color.YELLOW);
                break;
            case R.id.black:
                binding.RadioColor.setBackgroundColor(Color.BLACK);
                break;
            case R.id.reset:
                binding.RadioColor.setBackgroundColor(Color.WHITE);
                break;

        }

    }
}