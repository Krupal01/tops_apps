package com.example.storage_assigment.Preference;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;

import com.example.storage_assigment.R;
import com.example.storage_assigment.databinding.FragmentChangeBackgroundBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChangeBackgroundFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChangeBackgroundFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChangeBackgroundFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChangeBackgroundFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChangeBackgroundFragment newInstance(String param1, String param2) {
        ChangeBackgroundFragment fragment = new ChangeBackgroundFragment();
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

    private FragmentChangeBackgroundBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChangeBackgroundBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ArrayList<String> color = new ArrayList<>();
        color.add("white");
        color.add("yellow");
        color.add("black");
        color.add("red");

        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, color);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        binding.ColorSpinner.setAdapter(adapter);

        binding.ColorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences sharedPreferences = getContext().getSharedPreferences("Color", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                switch (color.get(position)){
                    case "white":
                        editor.putInt("color",Color.WHITE);
                        break;
                    case "yellow":
                        editor.putInt("color",Color.YELLOW);
                        break;
                    case "black":
                        editor.putInt("color",Color.GRAY);
                        break;
                    case "red":
                        editor.putInt("color",Color.RED);
                        break;
                    default:editor.putInt("color",Color.WHITE);
                }
                editor.commit();
                binding.layout.setBackgroundColor(sharedPreferences.getInt("color",0));


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}