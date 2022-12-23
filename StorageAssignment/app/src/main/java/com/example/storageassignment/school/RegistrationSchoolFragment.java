package com.example.storageassignment.school;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.storageassignment.R;
import com.example.storageassignment.databinding.FragmentRegistrationSchoolBinding;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegistrationSchoolFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegistrationSchoolFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RegistrationSchoolFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegistrationSchoolFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegistrationSchoolFragment newInstance(String param1, String param2) {
        RegistrationSchoolFragment fragment = new RegistrationSchoolFragment();
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

    private FragmentRegistrationSchoolBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentRegistrationSchoolBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.submit.setOnClickListener(v -> {
            if (binding.password.getText().toString().equals(binding.confirmPassword.getText().toString())){
                SharedPreferences preferences = getContext().getSharedPreferences("id_password", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("name",binding.studentName.getText().toString());
                editor.putString("password",binding.password.getText().toString());
                editor.commit();
                Toast.makeText(getContext(),"registration sucessfull" , Toast.LENGTH_SHORT).show();
                Navigation.findNavController(getView()).navigate(R.id.action_registrationSchoolFragment_to_loginSchoolFragment);

            }
            else {
                Toast.makeText(getContext(),"can't match both password" , Toast.LENGTH_SHORT).show();
            }
        });

    }
}