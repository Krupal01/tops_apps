package com.example.ui_control.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.databinding.FragmentTableLayoutBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link tableLayoutFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class tableLayoutFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public tableLayoutFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment tableLayoutFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static tableLayoutFragment newInstance(String param1, String param2) {
        tableLayoutFragment fragment = new tableLayoutFragment();
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

    private FragmentTableLayoutBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTableLayoutBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TableLayout tableLayout = binding.tbLayout;

        LinearLayout.LayoutParams tableRowParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);

        /* create a table row */
        TableRow tableRow = new TableRow(getContext());
        tableRow.setLayoutParams(tableRowParams);

        /* create cell element - textview */
        TextView tv = new TextView(getContext());
        tv.setBackgroundColor(0xff12dd12);
        tv.setText("dynamic textview");

        /* create cell element - button */
        Button btn = new Button(getContext());
        btn.setText("dynamic btn");
        btn.setBackgroundColor(0xff12dd12);


        /* add views to the row */
        tableRow.addView(tv);
        tableRow.addView(btn);

        /* add the row to the table */
        tableLayout.addView(tableRow);


        TableRow tableRow1 = new TableRow(getContext());
        tableRow.setLayoutParams(tableRowParams);

        /* create cell element - textview */
        TextView tv1 = new TextView(getContext());
        tv.setBackgroundColor(0xff12dd12);
        tv.setText("dynamic textview");

        /* create cell element - button */
        Button btn1 = new Button(getContext());
        btn.setText("dynamic btn");
        btn.setBackgroundColor(0xff12dd12);


        /* add views to the row */
        tableRow1.addView(tv1);
        tableRow1.addView(btn1);

        /* add the row to the table */
        tableLayout.addView(tableRow1);


    }
}