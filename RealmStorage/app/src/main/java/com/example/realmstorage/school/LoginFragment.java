package com.example.realmstorage.school;

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

import com.example.realmstorage.R;
import com.example.realmstorage.databinding.FragmentLoginBinding;

import org.jetbrains.annotations.NotNull;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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

    private FragmentLoginBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        IsLogin();

        binding.login.setOnClickListener(v -> {

            String student=binding.Student.getText().toString();
            String password = binding.password1.getText().toString();

            SharedPreferences preferences = getContext().getSharedPreferences("id_password", Context.MODE_PRIVATE);
            String studentPreference = preferences.getString("name","name");
            String passwordPreference = preferences.getString("password","password");

            if (student.equals(studentPreference) && password.equals(passwordPreference)) {
                SharedPreferences preferences1 = getContext().getSharedPreferences("IsLogin", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences1.edit();
                editor.putBoolean("isLogin", true);
                editor.commit();
                Navigation.findNavController(getView()).navigate(R.id.action_loginFragment_to_homeFragment);
            }
        });


    }

    private void IsLogin() {

        SharedPreferences preferences = getContext().getSharedPreferences("IsLogin", MODE_PRIVATE);
        Boolean login = preferences.getBoolean("isLogin",false);
        if(login == true){
//                Navigation.findNavController(getView()).navigate(R.id.action_loginSchoolFragment_to_homeFragment2);
        }
    }
}