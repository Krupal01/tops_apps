package com.example.ui_control.Fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentReverseBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Reverse#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Reverse extends Fragment implements TextWatcher {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Reverse() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Reverse.
     */
    // TODO: Rename and change types and number of parameters
    public static Reverse newInstance(String param1, String param2) {
        Reverse fragment = new Reverse();
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

    private FragmentReverseBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding=FragmentReverseBinding.inflate(inflater,container,false);
        getActivity().setTitle("reverse");
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.number.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
            int i = 0 , sum = 0 , j=0;
        try {
            String numberOne = binding.number.getText().toString();
            if(!numberOne.equals("")){
                i = Integer.parseInt(numberOne);
            }
            while(i>0){
                j=i%10;
                i/=10;
                sum = (sum*10)+j;
            }
            binding.reverseNumber.setText("Result : " + sum);
        }catch (Exception ex){

            binding.reverseNumber.setText("Result : " + sum);
        }


    }

    @Override
    public void afterTextChanged(Editable s) {

    }


}