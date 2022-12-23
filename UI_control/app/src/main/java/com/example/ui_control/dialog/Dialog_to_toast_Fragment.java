package com.example.ui_control.dialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ui_control.R;
import com.example.ui_control.databinding.FragmentDialogToToastBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Dialog_to_toast_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Dialog_to_toast_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Dialog_to_toast_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Dialog_to_toast_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Dialog_to_toast_Fragment newInstance(String param1, String param2) {
        Dialog_to_toast_Fragment fragment = new Dialog_to_toast_Fragment();
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

    private FragmentDialogToToastBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDialogToToastBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LayoutInflater inflater = getLayoutInflater();
        View view1 = inflater.inflate(R.layout.coustem_alert_dialog , null);
        EditText text = view1.findViewById(R.id.new_name);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("enter name");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(),text.getText().toString(),Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNeutralButton("cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setView(view1);
        AlertDialog dialog = builder.create();
        dialog.show();



    }
}