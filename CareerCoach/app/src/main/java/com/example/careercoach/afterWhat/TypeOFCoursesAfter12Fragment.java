package com.example.careercoach.afterWhat;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.careercoach.R;
import com.example.careercoach.databinding.FragmentTypeOFCoursesAfter12Binding;

import org.jetbrains.annotations.NotNull;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TypeOFCoursesAfter12Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TypeOFCoursesAfter12Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TypeOFCoursesAfter12Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TypeOFCoursesAfter12Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TypeOFCoursesAfter12Fragment newInstance(String param1, String param2) {
        TypeOFCoursesAfter12Fragment fragment = new TypeOFCoursesAfter12Fragment();
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
    private FragmentTypeOFCoursesAfter12Binding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("After 12th Courses");
        binding=FragmentTypeOFCoursesAfter12Binding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.imageView16.setOnClickListener(v -> {
            Navigation.findNavController(getView())
                    .navigate(R.id.action_typeOFCoursesAfter12Fragment_to_after12Science2);
        });
        binding.imageView17.setOnClickListener(v -> {
            Navigation.findNavController(getView())
                    .navigate(R.id.action_typeOFCoursesAfter12Fragment_to_after12Commerce2);
        });
        binding.imageView18.setOnClickListener(v -> {
            Navigation.findNavController(getView())
                    .navigate(R.id.action_typeOFCoursesAfter12Fragment_to_after12Arts);
        });
    }
}