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
import android.widget.RadioGroup;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentRadioBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link radio#newInstance} factory method to
 * create an instance of this fragment.
 */
public class radio extends Fragment implements TextWatcher, RadioGroup.OnCheckedChangeListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public radio() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment radio.
     */
    // TODO: Rename and change types and number of parameters
    public static radio newInstance(String param1, String param2) {
        radio fragment = new radio();
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

    private FragmentRadioBinding binding ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRadioBinding.inflate(inflater , container , false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.number1.addTextChangedListener(this);
        binding.number2.addTextChangedListener(this);
        binding.status.setOnCheckedChangeListener(this);


    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    int id = binding.status.getCheckedRadioButtonId();
    int no1 = 0, no2 = 0 , sum = 0;

    try{
        String no11 = binding.number1.getText().toString();
        if(!no11.equals("")){
            no1 = Integer.parseInt(no11);
        }

        String no22 = binding.number2.getText().toString();
        if(!no22.equals("")){
            no2 = Integer.parseInt(no22);
        }

        switch (id){
            case R.id.add:
                sum = no1 + no2;
                break;
                
            case R.id.sub:
                sum = no1 - no2;
                break;
                
            case R.id.mul:
                sum=no1*no2;
                break;
                
            case R.id.div:
                sum=no1/no2;
                break;

        }
        
        binding.ans.setText("result:" + sum);
    }
    catch (Exception ex){
        binding.ans.setText(ex.toString());
    }

    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }
}