package com.example.ui_control.Fragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.databinding.FragmentTimePickerBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TimePickerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TimePickerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TimePickerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TimePickerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TimePickerFragment newInstance(String param1, String param2) {
        TimePickerFragment fragment = new TimePickerFragment();
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

    private FragmentTimePickerBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTimePickerBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.btnStartTime.setOnClickListener(v -> {
            Calendar calendar=Calendar.getInstance();
            int hour=calendar.get(Calendar.HOUR);
            int minute=calendar.get(Calendar.MINUTE);
            TimePickerDialog.OnTimeSetListener listener=(view2, hour1, minute1)->{
                binding.btnStartTime.setText(hour1+":"+minute1);
                String startTime=hour1+":"+minute1;
            };
            TimePickerDialog dialog=new TimePickerDialog(getContext(),listener,hour,minute,true);
            dialog.show();
        });
        binding.btnEndTime.setOnClickListener(v -> {

            Calendar calendar1=Calendar.getInstance();
            int hour=calendar1.get(Calendar.HOUR);
            int minute=calendar1.get(Calendar.MINUTE);
            TimePickerDialog.OnTimeSetListener listener=(view2,hour1,minute1)->{
                binding.btnEndTime.setText(hour1+":"+minute1);
                String endTime=hour1+":"+minute1;
            };
            TimePickerDialog dialog=new TimePickerDialog(getContext(),listener,hour,minute,true);
            dialog.show();


        });

        binding.btnTotalTime.setOnClickListener(v -> {
            try {
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hh:mm a");

                String startTime = binding.btnStartTime.getText().toString();
                String endTime = binding.btnEndTime.getText().toString();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                Date time1 = simpleDateFormat.parse(startTime);
                Date time2 = simpleDateFormat.parse(endTime);

                long difference = time1.getTime() - time2.getTime();
                if (difference < 0) {
                    Date dateMax = simpleDateFormat.parse("24:00");
                    Date dateMin = simpleDateFormat.parse("00:00");
                    difference = (dateMax.getTime() - time1.getTime()) + (time2.getTime() - dateMin.getTime());
                }
                int days = (int) (difference / (1000 * 60 * 60 * 24));
                int hours = (int) ((difference - (1000 * 60 * 60 * 24 * days)) / (1000 * 60 * 60));
                int min = (int) (difference - (1000 * 60 * 60 * 24 * days) - (1000 * 60 * 60 * hours)) / (1000 * 60);
                binding.tvTotalTime.setText("Hours: " + hours + ", Mins: " + min);

                

            }catch(Exception e){
                binding.tvTotalTime.setText(e.toString());
            }



//
        });


    }
}