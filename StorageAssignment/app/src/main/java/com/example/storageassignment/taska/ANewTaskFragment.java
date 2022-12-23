package com.example.storageassignment.taska;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.example.storageassignment.Database.taskDatabase;
import com.example.storageassignment.R;
import com.example.storageassignment.databinding.FragmentANewTaskBinding;
import com.example.storageassignment.databinding.FragmentRegisterBinding;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ANewTaskFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ANewTaskFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ANewTaskFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ANewTaskFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ANewTaskFragment newInstance(String param1, String param2) {
        ANewTaskFragment fragment = new ANewTaskFragment();
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

    private FragmentANewTaskBinding binding;
    private ATaskEntity newtask;
    private taskDatabase db;
    private ATaskDao dao;
    private Calendar calendar1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentANewTaskBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.date.setOnClickListener(new View.OnClickListener() {
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
                        binding.date.setText(date1);
                    }
                },year , month , day);
                datePickerDialog.show();
            }
        });

        binding.time.setOnClickListener(v -> {
            Calendar calendar=Calendar.getInstance();
            int hour=calendar.get(Calendar.HOUR);
            int minute=calendar.get(Calendar.MINUTE);
            TimePickerDialog.OnTimeSetListener listener=(view2, hour1, minute1)->{
                binding.time.setText(hour1+":"+minute1);
            };
            TimePickerDialog dialog=new TimePickerDialog(getContext(),listener,hour,minute,true);
            dialog.show();
        });

        Bundle bundle = getArguments();
        if (bundle!=null){
            newtask = bundle.getParcelable("ATask");
        }

        if (newtask!=null){
            binding.taskName.setText(newtask.taskName);
            binding.discription.setText(newtask.taskDetail);
            binding.date.setText(newtask.date);
            binding.time.setText(newtask.timeOfTask);
            binding.priority.setText(newtask.priority);
        }

        db = taskDatabase.getInstance(getContext());
        dao = db.ATaskDao();

        binding.submit.setOnClickListener(v -> {
        if (newtask!=null){
            newtask.taskName = binding.taskName.getText().toString();
            newtask.taskDetail = binding.discription.getText().toString();
            newtask.date = binding.date.getText().toString();
            newtask.timeOfTask = binding.time.getText().toString();
            newtask.priority = binding.priority.getText().toString();
            dao.TaskUpdate(newtask);
        }else {
            newtask = new ATaskEntity();
            newtask.taskName = binding.taskName.getText().toString();
            newtask.taskDetail = binding.discription.getText().toString();
            newtask.date = binding.date.getText().toString();
            newtask.timeOfTask = binding.time.getText().toString();
            newtask.priority = binding.priority.getText().toString();
            dao.TaskInsert(newtask);
        }
        });
    }
}