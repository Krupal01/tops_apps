package com.example.ui_control.dialog;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentAlertDialogBlankBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlertDialog_BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlertDialog_BlankFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AlertDialog_BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlertDialog_BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlertDialog_BlankFragment newInstance(String param1, String param2) {
        AlertDialog_BlankFragment fragment = new AlertDialog_BlankFragment();
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

    private FragmentAlertDialogBlankBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAlertDialogBlankBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.alert.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setMessage("hello World");
            builder.setTitle("HELLO WORLD");
            builder.setIcon(R.drawable.ic_launcher_background);
            builder.setNeutralButton("cancle", (dialog, which) -> {
                Toast toast = Toast.makeText(getContext(),"cancalled" , Toast.LENGTH_SHORT);
                toast.show();
            });
            builder.setNegativeButton("no", (dialog, which) -> {
                Toast toast = Toast.makeText(getContext(),"no" , Toast.LENGTH_SHORT);
                toast.show();
            });
            builder.setPositiveButton("yes", (dialog, which) -> {
                Toast toast = Toast.makeText(getContext(),"yes" , Toast.LENGTH_SHORT);
                toast.show();
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
}