package com.example.ui_control.employee;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentEmployeeBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link employeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class employeeFragment extends Fragment implements employeeAdapter.clickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public employeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment employeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static employeeFragment newInstance(String param1, String param2) {
        employeeFragment fragment = new employeeFragment();
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

    private FragmentEmployeeBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEmployeeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<employee> data = new ArrayList<>();
        data.add(new employee("krupal", "21","521", R.drawable.ic_launcher_background));
        data.add(new employee("krupal", "21","521",R.drawable.ic_launcher_background));
        data.add(new employee("krupal", "21","521",R.drawable.ic_launcher_background));

        employeeAdapter adapter = new employeeAdapter(this);
        adapter.setdata(data);

        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void clickListener(employee employee) {
        Toast.makeText(getContext(), employee.getName(), Toast.LENGTH_SHORT).show();
    }
}