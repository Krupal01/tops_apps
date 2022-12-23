package com.example.ui_control.dialog;

import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.ui_control.databinding.FragmentTwoDateBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TwoDateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TwoDateFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TwoDateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TwoDateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TwoDateFragment newInstance(String param1, String param2) {
        TwoDateFragment fragment = new TwoDateFragment();
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

    private FragmentTwoDateBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTwoDateBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    Calendar calendar1 , calendar2;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.date1.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                calendar1 = Calendar.getInstance();
                int year = calendar1.get(Calendar.YEAR);
                int month = calendar1.get(Calendar.MONTH);
                int day = calendar1.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date1 = dayOfMonth+"/"+month+"/"+year;
                        binding.date1.setText(date1);
                    }
                },year , month , day);
                datePickerDialog.show();
            }
        });



        binding.date2.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                calendar2 = Calendar.getInstance();
                int year2 = calendar2.get(Calendar.YEAR);
                int month2 = calendar2.get(Calendar.MONTH);
                int day2 = calendar2.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        String date2 = dayOfMonth+"/"+month+"/"+year;
                        binding.date2.setText(date2);
                    }
                },year2 , month2 , day2);
                datePickerDialog.show();
            }
        });



        binding.btnDays.setOnClickListener(v -> {
            try {
                String d1 = binding.date1.getText().toString ();
                String d2 = binding.date2.getText().toString ();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/mm/yyyy");

                Date date1 = simpleDateFormat.parse(d1);
                Date date2 = simpleDateFormat.parse(d2);

                if (date1.before(date2)) {
                    long difference = Math.abs(date2.getTime() - date1.getTime());
                    long difftDays = difference / (24 * 60 * 60 * 1000);
                    binding.days.setText(Long.toString(difftDays));
                }
                else if (date2.before(date1)){
                    long difference = Math.abs(date1.getTime() - date2.getTime());
                    long difftDays = difference / (24 * 60 * 60 * 1000);
                    binding.days.setText("days between these dates is "+Long.toString(difftDays));
                }

            }
            catch(Exception ex)
            {
               binding.days.setText(ex.toString());
            }

        });

    }
}